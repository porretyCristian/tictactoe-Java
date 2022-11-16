package logic.functionalities;
import logic.features.Shapes;
import logic.players.Player;
import javax.swing.*;
import java.util.Arrays;

public class ShowTheShapesAvaliables {
    public static String shapesAvaliables(Player player) throws Exception {
        System.out.print("figuras disponibles:");
        for(Shapes shape:Shapes.values()){
            if(player.getCoins() >= shape.price){
                System.out.print("  " + shape.figure);
            }
        }
        String figure = JOptionPane.showInputDialog("Chose your shape:").toUpperCase();
        if(!(Arrays.asList(Shapes.shapesList()).contains(figure))){
            throw new Exception("esa figura no esta entre las disponibles");
        }
        return Shapes.shapesList()[Arrays.asList(Shapes.shapesList()).indexOf(figure)];
    }
}
