import logic.*;
import logic.functionalities.TableBuilder;

import javax.swing.*;

public class Main {
    public static void main(String args[]) throws InterruptedException {
        TableBuilder
                .VerifieTheWinner
                .PlayerShape figure = TableBuilder
                                        .VerifieTheWinner
                                        .PlayerShape.valueOf(JOptionPane.showInputDialog("Chose your shape:"));
        Player player = new Player("federico", figure);
        SinglePlayer.startSinglePLayer(player);
    }
}
