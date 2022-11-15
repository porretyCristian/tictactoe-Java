package logic.players;

import logic.functionalities.*;

public class CPU extends Player implements actionsPlayer {

    public CPU(String name, TableBuilder.VerifieTheWinner.PlayerShape figure) {
        super(name, figure);
    }
}