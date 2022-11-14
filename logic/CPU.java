package logic;

import functionalities.TableBuilder;
import functionalities.actionsPlayer;

public class CPU extends Player implements actionsPlayer {

    public CPU(String name, TableBuilder.VerifieTheWinner.PlayerShape figure) {
        super(name, figure);
    }
}