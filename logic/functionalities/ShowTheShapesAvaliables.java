package logic.functionalities;
import logic.features.Shapes;
import logic.players.Player;
import logic.validations.CoinsValidation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;

public class ShowTheShapesAvaliables {
    public static String shapesAvaliables(Player player) throws Exception {
        System.out.print("figuras disponibles:");
        for(Shapes shape:Shapes.values()){
            if(player.getCoins() >= shape.price){
                System.out.print("  " + shape.figure);
            }
        }
        String figure = JOptionPane.showInputDialog("Chose your shape:").toUpperCase();
        if(!(CoinsValidation.enoughMoney(Shapes.values(), figure, player))){
            throw new Exception("esa figura no esta entre las disponibles");
        }
        return Shapes.shapesList()[Arrays.asList(Shapes.shapesList()).indexOf(figure)];
    }
}
