package logic.functionalities;
import logic.features.Stuffs;
import logic.players.Player;
import javax.swing.*;

public class ShowTheShapesAvaliables {
    public static String shapesAvaliables(Player player){
        System.out.print("figuras disponibles:");
        for(Stuffs.Shapes shape:Stuffs.Shapes.values()){
            if(player.getCoins() >= shape.price){
                System.out.print("  " + shape.figure);
            }
        }
        String figure = JOptionPane.showInputDialog("Chose your shape:");
        for(Stuffs.Shapes shape:Stuffs.Shapes.values()){

        }
        return "";
    }
}
