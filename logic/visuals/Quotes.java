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
        System.out.print("\ncolumna: ");
        return input.nextInt();
    }

    public static void chosingTheTurn(String figure) throws InterruptedException{
        System.out.println("le toca a... " + figure);
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

    public static void winnerQuote(String figure){ System.out.println("el ganador ha sido: " + figure); }

}
