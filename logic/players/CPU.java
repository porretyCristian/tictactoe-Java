package logic.players;


public class CPU extends Player {
    public String figure;

    public CPU(String name, String figure) {
        setFigure(figure);
        setName(name);
    }

    public void setName(String name){ this.name = name; }
    public void setFigure(String figure) {
        this.figure = figure;
    }

    @Override
    public void moveFigure(int row, int column, String[][] table) {
        table[row][column] = figure.toString();
    }
}