package logic.validations;

import java.util.Arrays;

public class validateOcupedPlace {

    //terminar la validacion, si un lugar esta ocupado no dejar que se introduzcan valores ahi
    public static boolean isOcuped(String figure, String[] figuresAvaliables){
        return Arrays.asList(figuresAvaliables).contains(figure);
    }
}
