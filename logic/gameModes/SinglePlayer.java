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

public class SinglePlayer implements StartGame{
    protected static Dictionary<ArrayList<String>, Integer> historial = new Hashtable<>();
    private ArrayList<Integer> bestRecordTimeOnSingle = new ArrayList<>();
    @Override
    public void startGame(Player player) throws Exception {
        Scanner input = new Scanner(System.in);
        CPU cpu = new CPU(Shapes.X.figure);
        String[] figuresPlayers = {player.figure.toString(), cpu.figure.toString()};
        actionsPlayer[] playerMovements = {player, cpu};
        boolean isWinner = false;
        do {
            SearchTheLowest.showTheBestTime(bestRecordTimeOnSingle, player, isWinner);
            Quotes.choseSizeMessage();
            TableBuilder tableBuilder = new TableBuilder(Quotes.rowsSizeMessage(input), Quotes.columnSizeMessage(input));
            String[][] table = tableBuilder.table();
            Random random = new Random();
            Quotes.flippingTheCoin();
            int turno = random.nextInt(2);
            Quotes.chosingTheTurn(figuresPlayers[turno]);
            Instant startGame = Instant.now();
            do {
                TablePrinter.tablePrinter(table, tableBuilder.getRows(), tableBuilder.getColumns());
                int row, column;
                if(turno == 0){
                    Quotes.chosePlaceMessage();
                    row = Quotes.rowMoveMessage(input, tableBuilder) - 1;
                    column = Quotes.columnMoveMessage(input, tableBuilder) - 1;
                    if(validateOcupedPlace.isOcuped(table[row][column], Shapes.shapesList())){
                        Quotes.placeOcupedMessage();
                        continue;
                    }
                    playerMovements[turno].moveFigure(row, column, table);
                }else{
                    row = random.nextInt(tableBuilder.getRows());
                    column = random.nextInt(tableBuilder.getColumns());
                    if(validateOcupedPlace.isOcuped(table[row][column], Shapes.shapesList())){
                        continue;
                    }
                    Quotes.waitTheOponent();
                    playerMovements[turno].moveFigure(row, column, table);
                }
                turno = (turno == 1) ? 0 : 1;
            }while(ValidateOcuped.isAvaliableYet(table)
                    && !(TableBuilder.VerifieTheWinner.verifieWinner(table, player.figure)
                    || TableBuilder.VerifieTheWinner.verifieWinner(table, cpu.figure)));
            TablePrinter.tablePrinter(table, tableBuilder.getRows(), tableBuilder.getColumns());
            isWinner = (turno == 1) && TableBuilder.VerifieTheWinner.verifieWinner(table, player.figure);
            Quotes.winnerQuote(figuresPlayers[(turno == 1) ? 0 : 1],
                                (TableBuilder.VerifieTheWinner.verifieWinner(table, player.figure)
                                || TableBuilder.VerifieTheWinner.verifieWinner(table, cpu.figure)));
            player.setCoins((turno == 1) ? player.getCoins()+20 : player.getCoins());
            Instant endGame = Instant.now();
            SinglePlayer.setHistorial(player.figure, player.getName(),
                    (int) Duration.between(startGame, endGame).toMillis()/1000);
            bestRecordTimeOnSingle.add((int) Duration.between(startGame, endGame).toMillis()/1000);
        }while(!(JOptionPane.showInputDialog("Desea continuar? X: no, another key: yes")
                                                                        .equalsIgnoreCase("X")));
    }
    public static void setHistorial(String figure, String name, int time) {
        ArrayList<String> nameAndFigureList = new ArrayList<>();
        nameAndFigureList.add(figure);
        nameAndFigureList.add(name);
        historial.put(nameAndFigureList, time);
    }
}
