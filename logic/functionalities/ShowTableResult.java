package logic.functionalities;

import java.util.ArrayList;
import java.util.Stack;

public class ShowTableResult {
    public static void showTheGroup(Stack<ArrayList<String>> tableStack){
        for(int position = 1; position <= 4; position++){
            ArrayList<String> playerInfo = tableStack.pop();
            System.out.println(position + "  " + playerInfo.get(0) + "   " + playerInfo.get(1));
        }
    }
}
