import logic.functionalities.TableBuilder;
import logic.gameModes.SinglePlayer;
import logic.players.Player;
import logic.features.Shapes;
import logic.functionalities.*;
import javax.swing.*;

public class Main {
    public static void main(String args[]) throws Exception {

        Player player = new Player("federico");
        player.setFigure(ShowTheShapesAvaliables.shapesAvaliables(player));
        SinglePlayer.startSinglePLayer(player);
    }
}
