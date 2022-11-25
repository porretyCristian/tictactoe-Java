package logic.players;


public class CPU extends Player {
    public CPU(String name, String figure) {
        setFigure(figure);
        setName(name);
    }

    @Override
    public void moveFigure(int row, int column, String[][] table) {
        table[row][column] = figure.toString();
    }
}