package logic.gameModes;

import logic.features.Shapes;
import logic.functionalities.RunTheGame;
import logic.functionalities.ShowTheHistory;
import logic.functionalities.TableBuilder;
import logic.functionalities.TablePrinter;
import logic.players.CPU;
import logic.players.MainPlayer;
import logic.players.Player;
import logic.validations.ValidateOcuped;
import logic.validations.validateOcupedPlace;
import logic.visuals.Quotes;

import javax.swing.*;
import java.util.*;

public class Apuesta implements StartGame, AsignFigure{
    protected static ArrayList<Map<String, Map<String, String>>> historial = new ArrayList<>();

    @Override
    public void goToGame(MainPlayer mainPlayer) throws Exception {
        System.out.println("su monto es: " + mainPlayer.getCoins());
        if(Quotes.choseTheAction().equalsIgnoreCase("s")){ startGame(mainPlayer); }
        else{ ShowTheHistory.showHistory(historial); }
    }

    @Override
    public String asignAnFigure(MainPlayer player){
        String figureCpu = "X";
        Random random = new Random();
        while (figureCpu.equalsIgnoreCase(player.figure)){
            figureCpu = Shapes.shapesList()[random.nextInt(Shapes.shapesList().length)];
        }
        return figureCpu;
    }

    @Override
    public void startGame(MainPlayer mainPlayer) throws Exception {
        do{
            System.out.println("su monto es: " + mainPlayer.getCoins());
            int coinsToBet = betWithCoin.setTheBet(mainPlayer.getCoins());
            CPU[] cpus = {new CPU("your cpu", mainPlayer.figure), new CPU("cpu", asignAnFigure(mainPlayer))};
            Scanner input = new Scanner(System.in);
            Quotes.choseSizeMessage();
            TableBuilder tableBuilder = new TableBuilder(Quotes.rowsSizeMessage(input), Quotes.columnSizeMessage(input));
            String[][] table = tableBuilder.table();
            Random random = new Random();
            Quotes.flippingTheCoin();
            int turno = random.nextInt(2);
            int row, column;
            Quotes.chosingTheTurn(cpus[turno].figure);
            TablePrinter.tablePrinter(table, tableBuilder.getRows(), tableBuilder.getColumns());
            do{
                Quotes.waitingRandomMovements(mainPlayer.figure, cpus[turno].figure);
                row = random.nextInt(tableBuilder.getRows());
                column = random.nextInt(tableBuilder.getColumns());
                if(validateOcupedPlace.isOcuped(table[row][column], Shapes.shapesList())){
                    System.out.println("al parecer se escogio un lugar ocupado");
                    continue;
                }
                cpus[turno].moveFigure(row, column, table);
                TablePrinter.tablePrinter(table, tableBuilder.getRows(), tableBuilder.getColumns());
                turno = ChangeTheTurn.changeTurn(turno);
            }while (ValidateOcuped.isAvaliableYet(table)
                    && !(TableBuilder.VerifieTheWinner.verifieWinner(table, cpus[ChangeTheTurn.changeTurn(turno)].figure)));
            Quotes.winnerQuote(cpus[ChangeTheTurn.changeTurn(turno)].figure,
                    (TableBuilder.VerifieTheWinner.verifieWinner(table, cpus[ChangeTheTurn.changeTurn(turno)].figure)));
            Apuesta.setHistorial(cpus[ChangeTheTurn.changeTurn(turno)],
                    cpus[ChangeTheTurn.changeTurn(turno)]);
        }while (!(JOptionPane.showInputDialog("Desea continuar? X: no, another key: yes")
                .equalsIgnoreCase("X")));
        RunTheGame.run(mainPlayer);
    }

    public static void setHistorial(Player winnerPlayer, Player loserPlayer){
        Map<String, String> winner = new HashMap<>(); winner.put(winnerPlayer.getName(), winnerPlayer.figure);
        Map<String, String> loser = new HashMap<>(); loser.put(loserPlayer.getName(), loserPlayer.figure);
        Map<String, Map<String, String>> players = new Hashtable<String, Map<String, String>>();
        players.put("winner", winner); players.put("loser", loser);
        historial.add(players);
    }

    public static class betWithCoin{
        public static void earnOrLostBet(boolean isWinner, MainPlayer player, int coinsBetted){
            player.setCoins((isWinner) ? player.getCoins() + coinsBetted : player.getCoins() - coinsBetted);
        }

        public static int setTheBet(int playerCoins) {
            int coinsToBet = Integer.parseInt(JOptionPane.showInputDialog("cuantas monedas quiere apostar: "));
            while(isInvalidValueCoins(coinsToBet, playerCoins)){
                coinsToBet = Integer.parseInt(JOptionPane.showInputDialog("el dinero apostado tiene un monto o muy bajo o sobrepasa su presupuesto: "));
            }
            return coinsToBet;
        }

        public static boolean isInvalidValueCoins(int coinsToBet, int playerCoins){ return coinsToBet > playerCoins || coinsToBet < 1; }
    }
}
