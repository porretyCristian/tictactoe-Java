package logic.validations;

import logic.features.Shapes;
import logic.players.Player;

import java.util.ArrayList;

public class CoinsValidation {

    public static boolean enoughMoney(Shapes[] constants, String firgure, Player player){
        ArrayList<String> figuresAvaliables = new ArrayList<>();
        for (Shapes constant:constants) {
            if(constant.price <= player.getCoins()){
                figuresAvaliables.add(constant.figure);
            }
        }
        return figuresAvaliables.contains(firgure);
    }
}
