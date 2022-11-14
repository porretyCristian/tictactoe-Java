package logic.visuals;

import java.util.Scanner;

public class Quotes {
    public static void choseSizeMessage(){
        System.out.println("escoge el numero en");
    }

    public static void chosePlaceMessage(){
        System.out.println("a que posicion te quieres mover especificamente");
    }

    public static int rowsMessage(Scanner input){
        System.out.print("fila: ");
        return input.nextInt();
    }
    public static int columnMessage(Scanner input){
        System.out.println("\ncolumna: ");
        return input.nextInt();
    }
}
