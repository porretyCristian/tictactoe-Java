import logic.gameModes.Multiplayer;
import logic.gameModes.StartGame;
import logic.players.*;
import logic.functionalities.*;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]) throws Exception {
        MainPlayer mainPlayer = new MainPlayer(JOptionPane.showInputDialog("cual es su nombre: "));
        DealingShapes dealer = new DealingShapes();
        dealer.shapesAvaliables(mainPlayer);
        mainPlayer.setFigure(dealer.choseShape(mainPlayer));
        ArrayList<StartGame> gamesMode = new ArrayList<>();
        gamesMode.add(new Multiplayer());
        gamesMode.get(0).goToGame(mainPlayer);
    }
}
