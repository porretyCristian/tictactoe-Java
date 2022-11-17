import logic.gameModes.SinglePlayer;
import logic.gameModes.StartGame;
import logic.players.Player;
import logic.functionalities.*;

import java.util.ArrayList;

public class Main {
    public static void main(String args[]) throws Exception {
        Player player = new Player("federico");
        player.setFigure(ShowTheShapesAvaliables.shapesAvaliables(player));
        ArrayList<StartGame> gamesMode = new ArrayList<>();
        gamesMode.add(new SinglePlayer());
        gamesMode.get(0).startGame(player);
    }
}
