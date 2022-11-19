package logic.functionalities;

import logic.features.Shapes;
import logic.players.MainPlayer;
import logic.validations.CoinsValidation;

import javax.swing.*;

public class DealingShapesMultiPlayer extends DealingShapes {

    public void shapesAvaliables(MainPlayer mainPlayer){
        System.out.print("figuras disponibles:");
        for(Shapes shape:Shapes.values()){
            if(mainPlayer.getCoins() >= shape.price && !(mainPlayer.figure.equalsIgnoreCase(shape.figure))){
                System.out.print("  " + shape.figure);
            }
        }
        System.out.println();
    }

    public String choseShape(MainPlayer mainPlayer) throws Exception{
        String figure = JOptionPane.showInputDialog("Chose your shape:").toUpperCase();
        while (figure.equalsIgnoreCase(mainPlayer.figure)){
            figure = JOptionPane.showInputDialog("esa figura ya la tiene el usuario, escoge otra");;
        }
        if(!(CoinsValidation.enoughMoney(Shapes.values(), figure, mainPlayer))){
            throw new Exception("esa figura no esta entre las disponibles");
        }
        return figure;
    }
}
