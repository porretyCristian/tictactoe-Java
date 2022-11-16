package logic.players;

import logic.features.Stuffs;
import logic.functionalities.*;

public class CPU implements actionsPlayer {
    public final String name = "CPU";
    public Stuffs.OriginalShape figure;

    public CPU(Stuffs.OriginalShape figure) {
        setFigure(figure);
    }

    public void setFigure(Stuffs.OriginalShape figure) {
        this.figure = figure;
    }

    @Override
    public void moveFigure(int row, int column, String[][] table) {
        table[row][column] = figure.toString();
    }
}