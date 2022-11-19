import logic.gameModes.Multiplayer;
import logic.gameModes.StartGame;
import logic.players.*;
import logic.functionalities.*;

import java.util.ArrayList;

public class Main {
    public static void main(String args[]) throws Exception {
        MainPlayer mainPlayer = new MainPlayer("federico");
        mainPlayer.setFigure(new ShapesAvaliablesMainUser().shapesAvaliables(mainPlayer));
        ArrayList<StartGame> gamesMode = new ArrayList<>();
        gamesMode.add(new Multiplayer());
        gamesMode.get(0).goToGame(mainPlayer);
    }
}
