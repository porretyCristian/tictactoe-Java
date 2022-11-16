package logic.players;

import logic.functionalities.*;
import logic.features.Stuffs;
import java.time.LocalTime;

public class Player implements actionsPlayer{
    protected String name;
    private LocalTime bestRecordTime;
    public Stuffs.OriginalShape figure;
    private int coins;
    public Player(String name, Stuffs.OriginalShape figure){
        setName(name);
        setFigure(figure);
    }

    public int getCoins() {
        return coins;
    }

    public void setBestRecordTime(LocalTime bestRecordTime) {
        this.bestRecordTime = bestRecordTime;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setFigure(Stuffs.OriginalShape figure) {
        this.figure = figure;
    }

    @Override
    public void moveFigure(int row, int column, String[][] table) {
        table[row][column] = figure.toString();
    }
}