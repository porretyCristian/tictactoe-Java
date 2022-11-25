package logic.functionalities;

import logic.players.Player;

public class Frecuency {
    public static int frecuencyOf(String elementToCount, String[] elementsArrays){
        int countElement = 0;
        for(String element:elementsArrays){
            if(elementToCount.equalsIgnoreCase(element)){
                countElement++;
            }
        }
        return countElement;
    }
}
