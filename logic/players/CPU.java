package logic.players;


public class CPU extends Player {
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