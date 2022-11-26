package logic.functionalities;

import logic.features.Games;
import logic.gameModes.MiniCup;
import logic.players.MainPlayer;

import java.util.Scanner;

public class ChosingAGameMode {
    public static void goToMode(String gameModeSelected, MainPlayer player) throws Exception {
        if(gameModeSelected.equals("MINICUP")){
            MiniCup miniCupMode = new MiniCup();
            miniCupMode.goToGame(player, "semi");
        }else {
            Games.valueOf(gameModeSelected).GAMEMODE.goToGame(player);
        }
    }

    public static void showTheGameModes(){
        System.out.println("gamemodes:");
        for (Games gamemode: Games.values()) {
            System.out.println(" - " + gamemode.toString());
        }
        System.out.println("- MINICUP");
        System.out.print("chose a gamemode: ");
    }

    public static String selectTheGame(MainPlayer mainPlayer){
       Scanner input = new Scanner(System.in);
        String game = "";
        do{
            game = input.nextLine().toUpperCase();
            if(game.equals("BETMODE") && !(EnoughCoint.haveCoins(mainPlayer))){
                System.out.print("don't have the enough money to chose this mode, chose another: ");
            }
        }while(game.equals("BETMODE") && !(EnoughCoint.haveCoins(mainPlayer)));
        return game;
    }

}
