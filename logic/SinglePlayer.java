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
    public static void startSinglePLayer(Player player){
        Scanner input = new Scanner(System.in);
        CPU cpu = new CPU("CPU", TableBuilder.VerifieTheWinner.PlayerShape.X);
        String figuresPlayers[] = {player.getFigure(), cpu.getFigure()};
        actionsPlayer playerMovements[] = {player, cpu};
        Quotes.choseSizeMessage();
        TableBuilder tableBuilder = new TableBuilder(Quotes.rowsMessage(input), Quotes.columnMessage(input));
        String table[][] = tableBuilder.table();
        int turno = 0;
        do{
            tableBuilder.tablePrinter(table);
            if(turno == 0){
                Quotes.chosePlaceMessage();
                playerMovements[turno].moveFigure(Quotes.rowsMessage(input), Quotes.columnMessage(input), table);
                turno = 1;
            }else{
                Random random = new Random();
                playerMovements[turno].moveFigure(random.nextInt(table.length), random.nextInt(table[0].length), table);
                turno = 0;
            }
        }while(TableBuilder.VerifieTheWinner.verifieWinner(table, player.figure)
                || TableBuilder.VerifieTheWinner.verifieWinner(table, cpu.figure));
    }
}
