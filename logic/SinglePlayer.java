package logic;
import logic.visuals.*;
import logic.functionalities.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SinglePlayer {
    protected ArrayList<String> historial = new ArrayList<>();
    private LocalTime bestRecordTimeOnSingle;
    public static void startSinglePLayer(Player player) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        CPU cpu = new CPU("CPU", TableBuilder.VerifieTheWinner.PlayerShape.X);
        String figuresPlayers[] = {player.getFigure(), cpu.getFigure()};
        actionsPlayer playerMovements[] = {player, cpu};
        Quotes.choseSizeMessage();
        TableBuilder tableBuilder = new TableBuilder(Quotes.rowsMessage(input), Quotes.columnMessage(input));
        String table[][] = tableBuilder.table();
        //mejorar con lanzar la moneda
        Random random = new Random();
        System.out.println("lanzando la moneda...");
        Thread.sleep(3000);
        int turno = random.nextInt(2);
        do{
            turno = (turno == 1) ? 0 : 1;
            System.out.println("le toca empezar a... " + figuresPlayers[turno]);
            Thread.sleep(3000);
            tableBuilder.tablePrinter(table);
            if(turno == 0){
                Quotes.chosePlaceMessage();
                playerMovements[turno].moveFigure(Quotes.rowsMessage(input) - 1, Quotes.columnMessage(input) - 1, table);
            }else{
                playerMovements[turno].moveFigure(random.nextInt(table.length), random.nextInt(table[0].length), table);
            }
        }while(!(TableBuilder.VerifieTheWinner.verifieWinner(table, player.figure)
                || TableBuilder.VerifieTheWinner.verifieWinner(table, cpu.figure)));
        System.out.print("\033[H\033[2J");
        System.out.flush();
        tableBuilder.tablePrinter(table);
        Quotes.winnerQuote(figuresPlayers[turno]);
        player.setCoins((turno == 0) ? player.getCoins()+20 : player.getCoins()+0);
    }
}
