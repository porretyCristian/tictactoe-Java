package logic.functionalities;
import logic.validations.validateSize;
public class TableBuilder {
    private int columns;
    private int rows;

    public TableBuilder(int rows, int columns) throws Exception {
        setRows(rows);
        setColumns(columns);
    }

    public void setColumns(int columns) throws Exception {
        if(validateSize.lessSizeSuported(columns)){ throw new Exception("valor demasiado pequeño, minimo 3"); }
        else{ this.columns = columns; }
    }

    public void setRows(int rows) throws Exception {
        if(validateSize.lessSizeSuported(rows)){ throw new Exception("valor demasiado pequeño, minimo 3"); }
        else{ this.rows = rows; }
    }

    public int getColumns() {return columns;}

    public int getRows() {return rows;}

    public String[][] table(){
        String table[][] = new String[rows][columns];
        for(String[] row:table){
            for(int i = 0; i < columns; i++){
                row[i] = "*";
            }
        }
        return table;
    }

    public static class VerifieTheWinner {
        private static boolean winnerDiagonal(String table[][], String figure) {
            int figureEquals = 0;
            int specificIndexColumn = 0;
            for (String[] row : table) {
                figureEquals = (row[specificIndexColumn].equalsIgnoreCase(figure)) ? figureEquals+1 : 0;
                specificIndexColumn++;
            }
            return figureEquals == table[0].length;
        }

        private static boolean winnerOpossiteDiagonal(String table[][], String figure) {
            int figureEquals = 0;
            int specificIndexColumn = table[0].length - 1;
            for (String[] row : table) {
                figureEquals = (row[specificIndexColumn].equalsIgnoreCase(figure)) ? figureEquals+1 : 0;
                specificIndexColumn--;
            }
            return figureEquals == table[0].length;
        }

        private static boolean winnerInRow(String table[][], String figure){
            int figureEquals = 0;
            boolean areEquals = false;
            for(String[] row:table){
                for(String column:row) {
                    figureEquals = (column.equalsIgnoreCase(figure)) ? figureEquals+1 : 0;
                }
                if(figureEquals == row.length){
                    areEquals = true;
                    break;
                }
                figureEquals = 0;
            }
            return areEquals;
        }

        private static boolean winnerInColumn(String table[][], String figure){
            int figureEquals = 0;
            boolean areEquals = false;
            for(int indexRowToColumn = 0; indexRowToColumn < table.length; indexRowToColumn++){
                for(int indexRow = 0; indexRow < table.length; indexRow++) {
                    figureEquals = (table[indexRow][indexRowToColumn].equalsIgnoreCase(figure)) ? figureEquals+1 : 0;
                }
                if(figureEquals == table[0].length){
                    areEquals = true;
                    break;
                }
                figureEquals = 0;
            }
            return areEquals;
        }
        public static boolean verifieWinner(String table[][], String figure){
            return (winnerInColumn(table, figure) || winnerInRow(table, figure)
                    || winnerOpossiteDiagonal(table, figure) || winnerDiagonal(table, figure));
        }
    }
}
