package logic.functionalities;

import logic.players.MainPlayer;

import java.util.ArrayList;
import java.util.Collections;

public class SearchTheLowest {
    public static void showTheBestTime(ArrayList<Integer> timesList, MainPlayer mainPlayer, boolean isWinner) throws InterruptedException {
        if(timesList.isEmpty()){
            System.out.println( mainPlayer.getName()+ " empieza a jugar para que te superes a ti mismo en tiempo!!!");
        }else if (isWinner == true){
            Collections.sort(timesList);
            System.out.println( mainPlayer.getName()+ " tu mejor tiempo: " + timesList.get(0));
        }
        Thread.sleep(2000);
    }
}
