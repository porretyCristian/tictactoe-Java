package logic.gameModes;
import logic.features.Shapes;
import logic.players.*;
import logic.validations.ValidateOcuped;
import logic.visuals.*;
import logic.functionalities.*;
import logic.validations.validateOcupedPlace;
import javax.swing.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class SinglePlayer implements StartGame, AsignFigure {
    protected static Map<Map<String, Map<String, String>>, Integer> historial = new Hashtable<Map<String, Map<String, String>>, Integer>();
    private ArrayList<Integer> bestRecordTimeOnSingle = new ArrayList<>();

    @Override
    public String asignAnFigure(MainPlayer player){
        String figureCpu = "X";
        Random random = new Random();
        while (figureCpu.equalsIgnoreCase(player.figure)){
            figureCpu = Shapes.shapesList()[random.nextInt(Shapes.shapesList().length)];
        }
        return figureCpu;
    }
    
    @Override
    public void goToGame(MainPlayer mainPlayer) throws Exception {
        if(Quotes.choseTheAction().equalsIgnoreCase("s")){ startGame(mainPlayer); }
        else{ ShowTheHistory.showHistory(historial); }
    }

    @Override
    public void startGame(MainPlayer mainPlayer) throws Exception {
        Scanner input = new Scanner(System.in);
        CPU cpu = new CPU("CPU", asignAnFigure(mainPlayer));
        Player[] players = {mainPlayer, cpu};
        boolean isWinner = false;
        do {
            SearchTheLowest.showTheBestTime(bestRecordTimeOnSingle, mainPlayer, isWinner);
            Quotes.choseSizeMessage();
            TableBuilder tableBuilder = new TableBuilder(Quotes.rowsSizeMessage(input), Quotes.columnSizeMessage(input));
            String[][] table = tableBuilder.table();
            Random random = new Random();
            Quotes.flippingTheCoin();
            int turno = random.nextInt(2);
            Quotes.chosingTheTurn(players[turno].figure);
            Instant startGame = Instant.now();
            do {
                TablePrinter.tablePrinter(table, tableBuilder.getRows(), tableBuilder.getColumns());
                int row, column;
                if(turno == 0){
                    Quotes.chosePlaceMessage(players[turno]);
                    row = Quotes.rowMoveMessage(input, tableBuilder) - 1;
                    column = Quotes.columnMoveMessage(input, tableBuilder) - 1;
                    if(validateOcupedPlace.isOcuped(table[row][column], Shapes.shapesList())){
                        Quotes.placeOcupedMessage();
                        continue;
                    }
                    players[turno].moveFigure(row, column, table);
                }else{
                    row = random.nextInt(tableBuilder.getRows());
                    column = random.nextInt(tableBuilder.getColumns());
                    if(validateOcupedPlace.isOcuped(table[row][column], Shapes.shapesList())){
                        continue;
                    }
                    Quotes.waitTheOponent();
                    players[turno].moveFigure(row, column, table);
                }
                turno = ChangeTheTurn.changeTurn(turno);
            }while(ValidateOcuped.isAvaliableYet(table)
                    && !(TableBuilder.VerifieTheWinner.verifieWinner(table, mainPlayer.figure)
                    || TableBuilder.VerifieTheWinner.verifieWinner(table, cpu.figure)));
            TablePrinter.tablePrinter(table, tableBuilder.getRows(), tableBuilder.getColumns());
            isWinner = (turno == 1) && TableBuilder.VerifieTheWinner.verifieWinner(table, mainPlayer.figure);
            Instant endGame = Instant.now();
            Quotes.winnerQuote(players[ChangeTheTurn.changeTurn(turno)].figure,
                                (TableBuilder.VerifieTheWinner.verifieWinner(table, mainPlayer.figure)
                                || TableBuilder.VerifieTheWinner.verifieWinner(table, cpu.figure)));
            SinglePlayer.setHistorial(players[ChangeTheTurn.changeTurn(turno)],
                                        players[ChangeTheTurn.changeTurn(turno)],
                                    (int) Duration.between(startGame, endGame).toMillis()/1000);
            mainPlayer.setCoins((ChangeTheTurn.changeTurn(turno) == 0) ? mainPlayer.getCoins()+20 : mainPlayer.getCoins());
            bestRecordTimeOnSingle.add((int) Duration.between(startGame, endGame).toMillis()/1000);
        }while(!(JOptionPane.showInputDialog("Desea continuar? X: no, another key: yes")
                                                                        .equalsIgnoreCase("X")));
        RunTheGame.run(mainPlayer);
    }
    public static void setHistorial(Player winnerPlayer, Player loserPlayer, int time) {
        Map<String, String> winner = new HashMap<>(); winner.put(winnerPlayer.getName(), winnerPlayer.figure);
        Map<String, String> loser = new HashMap<>(); loser.put(loserPlayer.getName(), loserPlayer.figure);
        Map<String, Map<String, String>> players = new HashMap<>();
        players.put("winner", winner); players.put("loser", loser);
        historial.put(players, time);
    }
}