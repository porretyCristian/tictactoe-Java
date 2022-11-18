package logic.visuals;
import logic.functionalities.TableBuilder;
import logic.validations.validateSize;
import java.util.Scanner;
public class Quotes {

    public static String choseTheAction() throws Exception{
        Scanner input = new Scanner(System.in);
        System.out.print("que quieres hacer: empezar(s)   ver historial(h): ");
        String selection = input.nextLine();
        if(!(selection.equalsIgnoreCase("s")) && !(selection.equalsIgnoreCase("h"))){
            throw new Exception("mala eleccion de accion a realizar");
        }
        return selection;
    }

    public static void choseSizeMessage(){
        System.out.println("escoge el numero de...");
    }

    public static void chosePlaceMessage(){
        System.out.println("a que posicion te quieres mover especificamente");
    }

    public static int rowsSizeMessage(Scanner input){
        System.out.print("filas: ");
        return input.nextInt();
    }
    public static int columnSizeMessage(Scanner input){
        System.out.print("\ncolumnas: ");
        return input.nextInt();
    }

    public static int rowMoveMessage(Scanner input, TableBuilder table) throws Exception {
        System.out.print("fila: ");
        int row = input.nextInt();
        if(validateSize.rowOutOfRange(row, table)){ throw new Exception("el valor de movimiento esta fuera del rango soportado por el tamaño de la tabla"); }
        else { return row; }
    }

    public static int columnMoveMessage(Scanner input, TableBuilder table) throws Exception {
        System.out.print("columna: ");
        int columna = input.nextInt();
        if(validateSize.rowOutOfRange(columna, table)){ throw new Exception("el valor de movimiento esta fuera del rango soportado por el tamaño de la tabla"); }
        else { return columna; }
    }

    public static void chosingTheTurn(String figure) throws InterruptedException{
        System.out.println("empieza " + figure);
        Thread.sleep(1000);
    }

    public static void flippingTheCoin() throws InterruptedException {
        System.out.println("flipping the coin to chose the starter...");
        Thread.sleep(2500);
    }

    public static void waitTheOponent() throws InterruptedException {
        System.out.println("esperando a tu oponente");
        Thread.sleep(2000);
    }

    public static void placeOcupedMessage() throws InterruptedException{
        System.out.println("ese lugar ya esta ocupado");
        Thread.sleep(2000);
    }

    public static void winnerQuote(String figure, boolean thereIsWinner){
        if(thereIsWinner){
            System.out.println("el ganador ha sido: " + figure);
        }else{
            System.out.println("la tabla ha sido llenada y no hay ganador");
        }
    }

}
