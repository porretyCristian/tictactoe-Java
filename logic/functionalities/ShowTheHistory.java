package logic.functionalities;

import java.util.Map;

public class ShowTheHistory {
    public static void showHistory(Map<Map<String, Map<String, String>>, Integer> historyDicc){
        if(historyDicc.isEmpty()){
            System.out.println("esta vacio el historial.");
        }else{
            System.out.println("(ganador)Nombre,   " + "figura.   " + "  vs  " + "(perdedor)Nombre,    " + "figura,     " + "tiempo que duro la partida");
            for(Map.Entry<Map<String, Map<String, String>>, Integer> entry: historyDicc.entrySet()){
                for (Map.Entry<String, Map<String, String>> innerEntry:entry.getKey().entrySet()) {
                    for (Map.Entry<String, String> MostInnerEntry:innerEntry.getValue().entrySet()) {
                        System.out.print(MostInnerEntry.getKey() + ",       " + MostInnerEntry.getValue() + ",         ");
                    }
                }
                System.out.println(entry.getValue());
            }
        }
    }
}
