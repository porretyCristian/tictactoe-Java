package logic;

import logic.functionalities.*;

public class SecondPlayer extends Player implements actionsPlayer{

    public SecondPlayer(String name, TableBuilder.VerifieTheWinner.PlayerShape figure) {
        super(name, figure);
    }
}