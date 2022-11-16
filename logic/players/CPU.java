package logic.players;

import logic.features.Stuffs;
import logic.functionalities.*;

public class CPU extends Player implements actionsPlayer {

    public CPU(String name, Stuffs.OriginalShape figure) {
        super(name, figure);
    }
}