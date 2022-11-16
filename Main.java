import logic.gameModes.SinglePlayer;
import logic.players.Player;
import logic.functionalities.*;

public class Main {
    public static void main(String args[]) throws Exception {
        Player player = new Player("federico");
        player.setFigure(ShowTheShapesAvaliables.shapesAvaliables(player));
        SinglePlayer.startSinglePLayer(player);
    }
}
