package logic.validations;

import logic.functionalities.TableBuilder;

public class validateSize {
    public static boolean lessSizeSuported(int valueOfSize){ return valueOfSize < 3; }
    public static boolean rowOutOfRange(int valueOfRow, TableBuilder table){ return valueOfRow < 1 || valueOfRow > table.getRows(); }
    public static boolean columnOutOfRange(int valueOfColumn, TableBuilder table){ return valueOfColumn < 1 || valueOfColumn > table.getColumns(); }
}
