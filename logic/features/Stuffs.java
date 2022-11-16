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
        SIGNOMENOS("<"),
        SIGNODOLLAR("$"),
        HASTERISCO("#"),
        INTERROGACION("?");

        private String newFigure;
        NewShapes(String figure){
            newFigure = figure;
        }

        public String getFigures(){ return newFigure; }
    }
}
