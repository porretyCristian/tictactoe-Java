package logic.functionalities;
import logic.features.Stuffs;
import logic.players.Player;
public class ShowTheShapesAvaliables {
    public static void shapesAvaliables(Player player){
        System.out.print("figuras disponibles: " + Stuffs.OriginalShape.X.toString()
                         + "  " + Stuffs.OriginalShape.O.toString());
        for(Stuffs.NewShapes specialShape:Stuffs.NewShapes.values()){
            if(player.getCoins() >= specialShape.price){
                System.out.print("  " + specialShape.newFigure);
            }
        }
        System.out.println();
    }
}
