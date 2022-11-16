package logic.players;

import logic.features.Shapes;
import logic.functionalities.*;

public class CPU implements actionsPlayer {
    public final String name = "CPU";
    public String figure;

    public CPU(String figure) {
        setFigure(figure);
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    @Override
    public void moveFigure(int row, int column, String[][] table) {
        table[row][column] = figure.toString();
    }
}