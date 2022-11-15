package logic.gameModes;
import logic.players.*;
import logic.visuals.*;
import logic.functionalities.*;
import logic.validations.validateSize;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SinglePlayer {
    protected ArrayList<String> historial = new ArrayList<>();
    private LocalTime bestRecordTimeOnSingle;
    public static void startSinglePLayer(Player player) throws Exception {
        Scanner input = new Scanner(System.in);
        CPU cpu = new CPU("CPU", TableBuilder.VerifieTheWinner.PlayerShape.X);
        String[] figuresPlayers = {player.figure.toString(), cpu.figure.toString()};
        actionsPlayer[] playerMovements = {player, cpu};
        Quotes.choseSizeMessage();
        TableBuilder tableBuilder = new TableBuilder(Quotes.rowsSizeMessage(input), Quotes.columnSizeMessage(input));
        String[][] table = tableBuilder.table();
        Random random = new Random();
        Quotes.flippingTheCoin();
        int turno = random.nextInt(2);
        do {
            turno = (turno == 1) ? 0 : 1;
            TablePrinter.tablePrinter(table, tableBuilder.getRows(), tableBuilder.getColumns());
            Quotes.chosingTheTurn(figuresPlayers[turno]);
            if(turno == 0){
                Quotes.chosePlaceMessage();
                playerMovements[turno].moveFigure(Quotes.rowMoveMessage(input, tableBuilder) - 1, Quotes.columnMoveMessage(input, tableBuilder) - 1, table);
            }else{
                Quotes.waitTheOponent();
                playerMovements[turno].moveFigure(random.nextInt(tableBuilder.getRows()),
                                                  random.nextInt(tableBuilder.getColumns()), table);
            }
        }while(!(TableBuilder.VerifieTheWinner.verifieWinner(table, player.figure)
                || TableBuilder.VerifieTheWinner.verifieWinner(table, cpu.figure)));
        TablePrinter.tablePrinter(table, tableBuilder.getRows(), tableBuilder.getColumns());
        Quotes.winnerQuote(figuresPlayers[turno]);
        player.setCoins((turno == 0) ? player.getCoins()+20 : player.getCoins());
    }
}
