package logic;

import functionalities.*;

import java.time.LocalTime;

public class Player implements actionsPlayer{
    protected String name;
    private LocalTime bestRecordTime;
    protected TableBuilder.VerifieTheWinner.PlayerShape figure;
    private int coins;
    public Player(String name, TableBuilder.VerifieTheWinner.PlayerShape figure){
        setName(name);
        setFigure(figure);
    }

    public int getCoins() {
        return coins;
    }

    public String getFigure() {
        return figure.toString();
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

    public void setFigure(TableBuilder.VerifieTheWinner.PlayerShape figure) {
        this.figure = figure;
    }

    @Override
    public void moveFigure(int row, int column, String[][] table) {
        table[row][column] = this.getFigure();
    }
}