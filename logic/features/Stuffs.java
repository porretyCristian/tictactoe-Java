package logic.features;

import logic.functionalities.*;

import java.util.Arrays;

public class Stuffs {

    public enum OriginalShape {
        O, X;
        public String[] constantToString() {
            String[] stringConstantList = new String[2];
            for (OriginalShape constant:OriginalShape.values()){
                stringConstantList[Arrays.asList(OriginalShape.values()).indexOf(constant)] = constant.toString();
            }
            return stringConstantList;
        }
    }

    public enum NewShapes{
        SIGNOMENOS("<", 40),
        SIGNODOLLAR("$", 60),
        HASTERISCO("#", 80),
        INTERROGACION("?", 100);

        public final String newFigure;
        public final int price;
        NewShapes(String figure, int price){
            newFigure = figure;
            this.price = price;
        }
    }
}
