package logic.functionalities;

import logic.players.Player;

import java.util.ArrayList;
import java.util.Collections;

public class SearchTheLowest {
    public static void showTheBestTime(ArrayList<Integer> timesList, Player player, boolean isWinner) throws InterruptedException {
        if(timesList.isEmpty()){
            System.out.println( player.getName()+ " empieza a jugar para que te superes a ti mismo en tiempo!!!");
        }else if (isWinner == true){
            Collections.sort(timesList);
            System.out.println( player.getName()+ " tu mejor tiempo: " + timesList.get(0));
        }
        Thread.sleep(2000);
    }
}
