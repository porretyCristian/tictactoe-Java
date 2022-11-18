import logic.gameModes.SinglePlayer;
import logic.gameModes.StartGame;
import logic.players.MainPlayer;
import logic.functionalities.*;

import java.util.ArrayList;

public class Main {
    public static void main(String args[]) throws Exception {
        MainPlayer mainPlayer = new MainPlayer("federico");
        mainPlayer.setFigure(ShowTheShapesAvaliables.shapesAvaliables(mainPlayer));
        ArrayList<StartGame> gamesMode = new ArrayList<>();
        gamesMode.add(new SinglePlayer());
        gamesMode.get(0).goToGame(mainPlayer);
    }
}
