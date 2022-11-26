package logic.gameModes;

import logic.features.Shapes;
import logic.functionalities.*;
import logic.players.CPU;
import logic.players.MainPlayer;
import logic.players.Player;
import logic.validations.ValidateOcuped;
import logic.validations.validateOcupedPlace;
import logic.visuals.Quotes;
import java.util.*;

public class MiniCup {
    public final Player[][] semiFinal = new Player[2][2];
    public final Player[] finalGame = new Player[2];
    public final Player[] thirdPlace = new Player[2];
    public Stack<ArrayList<String>> tablePositions = new Stack<>();
    Scanner input = new Scanner(System.in);

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
        Quotes.choseSizeMessage();
        TableBuilder tableBuilder = new TableBuilder(Quotes.rowsSizeMessage(input), Quotes.columnSizeMessage(input));
        String[][] table = tableBuilder.table();
        Random random = new Random();
        Quotes.flippingTheCoin();
        int turno = random.nextInt(2);
        Quotes.chosingTheTurn(match[turno].figure);
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
        int indexCpuWinner = random.nextInt(2);
        int indexCpuLoser = (indexCpuWinner == 1) ? 0 : 1;
        if (fase.equalsIgnoreCase("semi")) {
            if (ChangeTheTurn.changeTurn(turno) == 0) {
                finalGame[0] = mainPlayer;
                finalGame[1] = semiFinal[1][indexCpuWinner];
                thirdPlace[0] = semiFinal[1][indexCpuLoser];
                thirdPlace[1] = semiFinal[0][1];
                goToGame(mainPlayer, "final");
            } else {
                finalGame[0] = semiFinal[0][1];
                finalGame[1] = semiFinal[1][indexCpuWinner];
                thirdPlace[0] = mainPlayer;
                thirdPlace[1] = semiFinal[1][indexCpuLoser];
                goToGame(mainPlayer, "third");
            }
        } else {
            if (fase.equalsIgnoreCase("final")) {
                tablePositions.push(addToTable(thirdPlace[indexCpuLoser].getName(), thirdPlace[indexCpuLoser].figure));
                tablePositions.push(addToTable(thirdPlace[indexCpuWinner].getName(), thirdPlace[indexCpuWinner].figure));
                tablePositions.push(addToTable(finalGame[turno].getName(), finalGame[turno].figure));
                tablePositions.push(addToTable(finalGame[ChangeTheTurn.changeTurn(turno)].getName(),
                        finalGame[ChangeTheTurn.changeTurn(turno)].figure));
                Quotes.championQuote(finalGame[ChangeTheTurn.changeTurn(turno)].figure);
            } else if (fase.equalsIgnoreCase("third")) {
                tablePositions.push(addToTable(thirdPlace[turno].getName(), thirdPlace[turno].figure));
                tablePositions.push(addToTable(thirdPlace[ChangeTheTurn.changeTurn(turno)].getName(),
                        thirdPlace[ChangeTheTurn.changeTurn(turno)].figure));
                tablePositions.push(addToTable(finalGame[indexCpuLoser].getName(), finalGame[indexCpuLoser].figure));
                tablePositions.push(addToTable(finalGame[indexCpuWinner].getName(),
                        finalGame[indexCpuWinner].figure));
                Quotes.thirdQuote(thirdPlace[ChangeTheTurn.changeTurn(turno)].figure);
            }
            ShowTableResult.showTheGroup(tablePositions);
        }
        RunTheGame.run(mainPlayer);
    }

    public ArrayList<String> addToTable(String name, String figure){
        ArrayList<String> playerInfo = new ArrayList<>();
        playerInfo.add(name);playerInfo.add(figure);
        return playerInfo;
    }



    private void setMatch(MainPlayer mainPlayer){
            semiFinal[0][0]=mainPlayer;semiFinal[0][1]=new CPU("CPU-1","$");
            semiFinal[1][0]=new CPU("CPU-2","#");semiFinal[1][1]=new CPU("CPU-3","?");
    }
}
