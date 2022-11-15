package logic.functionalities;

public class TablePrinter {
    public static void tablePrinter(String[][] table, int rows, int columns){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                System.out.print(table[i][j] + ("   "));
            }
            System.out.println("\n");
        }
    }
}
