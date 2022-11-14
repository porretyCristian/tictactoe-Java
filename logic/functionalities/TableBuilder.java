package logic.functionalities;

public class TableBuilder {
    private int columns;
    private int rows;
    public TableBuilder(int rows, int columns){
        this.columns = columns;
        this.rows = rows;
    }

    public String[][] table(){
        String table[][] = new String[columns][rows];
        for(String[] row:table){
            for(int i = 0; i < columns; i++){
                row[i] = "*";
            }
        }
        return table;
    }

    public void tablePrinter(String[][] table){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                System.out.print(table[i][j] + ("   "));
            }
            System.out.println("\n");
        }
    }

    public static class VerifieTheWinner {

        public enum PlayerShape {O, X}

        private static boolean winnerDiagonal(String table[][], PlayerShape figure) {
            int figureEquals = 0;
            int specificIndexColumn = 0;
            for (String[] row : table) {
                figureEquals = (row[specificIndexColumn].equalsIgnoreCase(figure.toString())) ? figureEquals+1 : 0;
                specificIndexColumn++;
            }
            return figureEquals == table[0].length;
        }

        private static boolean winnerOpossiteDiagonal(String table[][], PlayerShape figure) {
            int figureEquals = 0;
            int specificIndexColumn = table[0].length - 1;
            for (String[] row : table) {
                figureEquals = (row[specificIndexColumn].equalsIgnoreCase(figure.toString())) ? figureEquals+1 : 0;
                specificIndexColumn--;
            }
            return figureEquals == table[0].length;
        }

        private static boolean winnerInRow(String table[][], PlayerShape figure){
            int figureEquals = 0;
            boolean areEquals = false;
            for(String[] row:table){
                for(String column:row) {
                    figureEquals = (column.equalsIgnoreCase(figure.toString())) ? figureEquals+1 : 0;
                }
                if(figureEquals == row.length){
                    areEquals = true;
                    break;
                }
                figureEquals = 0;
            }
            return areEquals;
        }

        private static boolean winnerInColumn(String table[][], PlayerShape figure){
            int figureEquals = 0;
            boolean areEquals = false;
            for(int indexRowToColumn = 0; indexRowToColumn < table.length; indexRowToColumn++){
                for(int indexRow = 0; indexRow < table.length; indexRow++) {
                    figureEquals = (table[indexRow][indexRowToColumn].equalsIgnoreCase(figure.toString())) ? figureEquals+1 : 0;
                }
                if(figureEquals == table[0].length){
                    areEquals = true;
                    break;
                }
                figureEquals = 0;
            }
            return areEquals;
        }
        public static boolean verifieWinner(String table[][], PlayerShape figure){
            return (winnerInColumn(table, figure) || winnerInRow(table, figure)
                    || winnerOpossiteDiagonal(table, figure) || winnerDiagonal(table, figure));
        }
    }
}
