package logic.validations;

import java.util.Arrays;

public class validateOcupedPlace {
    public static boolean isOcuped(String figureInPlace, String[] figuresAvaliables){
        return Arrays.asList(figuresAvaliables).contains(figureInPlace);
    }
}
