package logic.gameModes;
import logic.features.Shapes;
import logic.players.*;
import logic.visuals.*;
import logic.functionalities.*;
import logic.validations.validateOcupedPlace;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.*;

public class SinglePlayer {
    protected static Dictionary<ArrayList<String>, Integer> historial = new Hashtable<>();
    private LocalTime bestRecordTimeOnSingle;
    public static void startSinglePLayer(Player player) throws Exception {
        Scanner input = new Scanner(System.in);
        CPU cpu = new CPU(Shapes.X.figure);
        String[] figuresPlayers = {player.figure.toString(), cpu.figure.toString()};
        actionsPlayer[] playerMovements = {player, cpu};
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
        }while(!(TableBuilder.VerifieTheWinner.verifieWinner(table, player.figure)
                || TableBuilder.VerifieTheWinner.verifieWinner(table, cpu.figure)));
        TablePrinter.tablePrinter(table, tableBuilder.getRows(), tableBuilder.getColumns());
        Quotes.winnerQuote(figuresPlayers[(turno == 1) ? 0 : 1]);
        player.setCoins((turno == 1) ? player.getCoins()+20 : player.getCoins());
        Instant endGame = Instant.now();
        SinglePlayer.setHistorial(player.figure, player.getName(),
                            (int) Duration.between(startGame, endGame).toMillis()/1000);
    }

    public static void setHistorial(String figure, String name, int time) {
        ArrayList<String> nameAndFigureList = new ArrayList<>();
        nameAndFigureList.add(figure);
        nameAndFigureList.add(name);
        historial.put(nameAndFigureList, time);
    }
}
