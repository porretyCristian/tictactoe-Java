package logic.functionalities;

import logic.features.Shapes;
import logic.players.MainPlayer;
import logic.validations.CoinsValidation;

import javax.swing.*;
import java.util.Arrays;

public class DealingShapes {
    public void shapesAvaliables(MainPlayer mainPlayer){
        System.out.print("figuras disponibles:");
        for(Shapes shape:Shapes.values()){
            if(mainPlayer.getCoins() >= shape.price){
                System.out.print("  " + shape.figure);
            }
        }
        System.out.println();
    }
    public String choseShape(MainPlayer mainPlayer) throws Exception{
        String figure = JOptionPane.showInputDialog("Chose your shape:").toUpperCase();
        if(!(CoinsValidation.enoughMoney(Shapes.values(), figure, mainPlayer))){
            throw new Exception("esa figura no esta entre las disponibles");
        }
        return Shapes.shapesList()[Arrays.asList(Shapes.shapesList()).indexOf(figure)];
    }
}
