import logic.players.*;
import logic.functionalities.*;
import javax.swing.*;

public class Main {
    public static void main(String args[]) throws Exception {
        MainPlayer mainPlayer = new MainPlayer(JOptionPane.showInputDialog("cual es su nombre: "));
        DealingShapes dealer = new DealingShapes();
        dealer.shapesAvaliables(mainPlayer);
        mainPlayer.setFigure(dealer.choseShape(mainPlayer));
        RunTheGame.run(mainPlayer);
    }
}
