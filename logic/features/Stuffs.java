package logic.features;

import logic.functionalities.*;

public class Stuffs {
    public enum NewFigures{
        SIGNOMENOS("<"),
        SIGNODOLLAR("$"),
        HASTERISCO("#"),
        INTERROGACION("?");

        private String newFigure;
        NewFigures(String figure){
            newFigure = figure;
        }

        public String getFigures(){ return newFigure; }
    }



}
