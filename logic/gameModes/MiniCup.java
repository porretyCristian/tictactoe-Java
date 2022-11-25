package logic.gameModes;

import logic.features.Shapes;
import logic.functionalities.*;
import logic.players.CPU;
import logic.players.MainPlayer;
import logic.players.Player;
import logic.validations.ValidateOcuped;
import logic.validations.validateOcupedPlace;
import logic.visuals.Quotes;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MiniCup {
    //las fases estaran en 3 arrays, una matriz con las semifinales, una de tercer lugar y otra de final
    public final Player[][] semiFinal = new Player[2][2];
    public final Player[] finalGame = new Player[2];
    public final Player[] thirdPlace = new Player[2];
    public final ArrayList<Integer> records = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    boolean isInThePodium = false;

    public void asignAnFigure(MainPlayer player) throws Exception {
        for (int i = 0; i < semiFinal.length; i++) {
            int matchIndex = (i == 0) ? 1 : 0;
            while (Frecuency.frecuencyOf(semiFinal[i][matchIndex].figure, ShowFiguresPlayers.figures(semiFinal)) > 1) {
                Random random = new Random();
                semiFinal[i][matchIndex].setFigure(Shapes.shapesList()[random.nextInt(Shapes.shapesList().length)]);
            }
        }
    }

    public void goToGame(MainPlayer mainPlayer, String fase) throws Exception {
        if (fase.equalsIgnoreCase("semi")) {
            setMatch(mainPlayer);
            Quotes.showTheMatches(semiFinal);
            startGame(mainPlayer, semiFinal[0], fase);
        } else if (fase.equalsIgnoreCase("third")) {
            Quotes.showTheMatches(thirdPlace);
            startGame(mainPlayer, thirdPlace, fase);
        } else if (fase.equalsIgnoreCase("final")) {
            Quotes.showTheMatches(finalGame);
            startGame(mainPlayer, finalGame, fase);
        }
    }

    public void startGame(MainPlayer mainPlayer, Player[] match, String fase) throws InterruptedException, Exception {
        System.out.println(fase);
        SearchTheLowest.showTheBestTime(records, mainPlayer, isInThePodium);
        Quotes.choseSizeMessage();
        TableBuilder tableBuilder = new TableBuilder(Quotes.rowsSizeMessage(input), Quotes.columnSizeMessage(input));
        String[][] table = tableBuilder.table();
        Random random = new Random();
        Quotes.flippingTheCoin();
        int turno = random.nextInt(2);
        Quotes.chosingTheTurn(match[turno].figure);
        Instant startGame = Instant.now();
        do {
            TablePrinter.tablePrinter(table, tableBuilder.getRows(), tableBuilder.getColumns());
            int row, column;
            if (turno == 0) {
                Quotes.chosePlaceMessage(match[turno]);
                row = Quotes.rowMoveMessage(input, tableBuilder) - 1;
                column = Quotes.columnMoveMessage(input, tableBuilder) - 1;
                if (validateOcupedPlace.isOcuped(table[row][column], Shapes.shapesList())) {
                    Quotes.placeOcupedMessage();
                    continue;
                }
                match[turno].moveFigure(row, column, table);
            } else {
                row = random.nextInt(tableBuilder.getRows());
                column = random.nextInt(tableBuilder.getColumns());
                if (validateOcupedPlace.isOcuped(table[row][column], Shapes.shapesList())) {
                    continue;
                }
                Quotes.waitTheOponent();
                match[turno].moveFigure(row, column, table);
            }
            turno = ChangeTheTurn.changeTurn(turno);
        } while (ValidateOcuped.isAvaliableYet(table)
                && !(TableBuilder.VerifieTheWinner.verifieWinner(table, match[ChangeTheTurn.changeTurn(turno)].figure)));
        TablePrinter.tablePrinter(table, tableBuilder.getRows(), tableBuilder.getColumns());
        if (fase.equalsIgnoreCase("semi")) {
            int indexCpuWinner = random.nextInt(2);
            if (ChangeTheTurn.changeTurn(turno) == 0) {
                finalGame[0] = mainPlayer;
                finalGame[1] = semiFinal[1][indexCpuWinner];
                goToGame(mainPlayer, "final");
            } else {
                thirdPlace[0] = mainPlayer;
                thirdPlace[1] = semiFinal[1][(indexCpuWinner == 1) ? 0 : 1];
                goToGame(mainPlayer, "third");
            }
        } else if (fase.equalsIgnoreCase("final")) {
            Quotes.championQuote(finalGame[ChangeTheTurn.changeTurn(turno)].figure);
        } else if (fase.equalsIgnoreCase("third")) {
            Quotes.thirdQuote(finalGame[ChangeTheTurn.changeTurn(turno)].figure);
        }
        isInThePodium = ChangeTheTurn.changeTurn(turno) == 0;
        Instant endGame = Instant.now();
        records.add((int) Duration.between(startGame, endGame).toMillis() / 1000);
    }


    private void setMatch(MainPlayer mainPlayer){
            semiFinal[0][0]=mainPlayer;semiFinal[0][1]=new CPU("CPU-1","$");
            semiFinal[1][0]=new CPU("CPU-2","#");semiFinal[1][1]=new CPU("CPU-3","?");
    }
}
