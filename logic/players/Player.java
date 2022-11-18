package logic.players;

public abstract class Player {
    protected String name;
    public String figure;
    public abstract void moveFigure(int row, int column, String[][] table);
    public String getName() { return name; }
}
