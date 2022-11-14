package logic;

import logic.functionalities.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class SinglePlayer {
    protected ArrayList<String> historial = new ArrayList<>();
    private LocalTime bestRecordTimeOnSingle;
    public static void startSinglePLayer(Player player){
        Scanner input = new Scanner(System.in);
        CPU cpu = new CPU("CPU", TableBuilder.VerifieTheWinner.PlayerShape.X);
        String figuresPlayers[] = {player.getFigure(), cpu.getFigure()};
        actionsPlayer playerMovements[] = {player, cpu};
        System.out.println("de cuanto quieres las medidas de la tabla");
    }
}
