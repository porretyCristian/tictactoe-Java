package logic.validations;

public class ValidateOcuped {
    public static boolean isAvaliableYet(String[][] board){
        var isAvaliable = false;
        for (String[] row:board) {
            for(String element:row){
                if(element.equals("*")) {
                    isAvaliable = true;
                    break;
                }
            }
            if(isAvaliable == true){ break; }
        }
        return isAvaliable;
    }
}
