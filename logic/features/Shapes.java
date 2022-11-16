package logic.features;

import java.util.Arrays;

public enum Shapes {
    O("O", 0),
    X("X", 0),
    SIGNOMENOS("<", 40),
    SIGNODOLLAR("$", 60),
    HASTERISCO("#", 80),
    INTERROGACION("?", 100);
    public final String figure;
    public final int price;
    Shapes(String figure, int price){
        this.figure = figure;
        this.price = price;
    }
    public static String[] shapesList() {
        String[] stringConstantList = new String[6];
        for (Shapes constant: Shapes.values()){
            stringConstantList[Arrays.asList(Shapes.values()).indexOf(constant)] = constant.figure;
        }
        return stringConstantList;
    }
}
