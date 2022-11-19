package logic.validations;

import logic.features.Shapes;
import logic.players.MainPlayer;

import java.util.ArrayList;

public class CoinsValidation {

    public static boolean enoughMoney(Shapes[] constants, String firgure, MainPlayer mainPlayer){
        ArrayList<String> figuresAvaliables = new ArrayList<>();
        for (Shapes constant:constants) {
            if(constant.price <= mainPlayer.getCoins()){
                figuresAvaliables.add(constant.figure);
            }
        }
        return figuresAvaliables.contains(firgure.toUpperCase());
    }
}
