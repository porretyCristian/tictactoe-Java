package logic.gameModes;
import logic.features.Stuffs;
import logic.players.*;
import logic.visuals.*;
import logic.functionalities.*;
import logic.validations.validateOcupedPlace;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SinglePlayer {
    protected ArrayList<String> historial = new ArrayList<>();
    private LocalTime bestRecordTimeOnSingle;
    public static void startSinglePLayer(Player player) throws Exception {
        Scanner input = new Scanner(System.in);
        CPU cpu = new CPU(Stuffs.Shapes.X);
        String[] figuresPlayers = {player.figure.toString(), cpu.figure.toString()};
        actionsPlayer[] playerMovements = {player, cpu};
        Quotes.choseSizeMessage();
        TableBuilder tableBuilder = new TableBuilder(Quotes.rowsSizeMessage(input), Quotes.columnSizeMessage(input));
        String[][] table = tableBuilder.table();
        Random random = new Random();
        Quotes.flippingTheCoin();
        int turno = random.nextInt(2);
        Quotes.chosingTheTurn(figuresPlayers[turno]);
        do {
            TablePrinter.tablePrinter(table, tableBuilder.getRows(), tableBuilder.getColumns());
            int row, column;
            if(turno == 0){
                Quotes.chosePlaceMessage();
                row = Quotes.rowMoveMessage(input, tableBuilder) - 1;
                column = Quotes.columnMoveMessage(input, tableBuilder) - 1;
                if(validateOcupedPlace.isOcuped(table[row][column], player.figure.constantToString())){
                    Quotes.placeOcupedMessage();
                    continue;
                }
                playerMovements[turno].moveFigure(row, column, table);
            }else{
                row = random.nextInt(tableBuilder.getRows());
                column = random.nextInt(tableBuilder.getColumns());
                if(validateOcupedPlace.isOcuped(table[row][column], cpu.figure.constantToString())){
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
    }
}
