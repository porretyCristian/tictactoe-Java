package logic.gameModes;
import logic.features.Shapes;
import logic.functionalities.*;
import logic.players.*;
import logic.players.SecondMainPlayer;
import logic.validations.ValidateOcuped;
import logic.validations.validateOcupedPlace;
import logic.visuals.Quotes;
import javax.swing.*;
import java.util.*;

public class Multiplayer implements StartGame{
    protected static ArrayList<Map<String, Map<String, String>>> historial = new ArrayList<>();

    @Override
    public void goToGame(MainPlayer mainPlayer) throws Exception {
        if(Quotes.choseTheAction().equalsIgnoreCase("s")){ startGame(mainPlayer); }
        else{ ShowTheHistory.showHistory(historial); }
    }

    @Override
    public void startGame(MainPlayer mainPlayer) throws Exception {
        Scanner input = new Scanner(System.in);
        SecondMainPlayer secondPlayer = new SecondMainPlayer(JOptionPane.showInputDialog("digite su nombre segundo usuario: "));
        DealingShapesMultiPlayer dealerShapes = new DealingShapesMultiPlayer();
        dealerShapes.shapesAvaliables(mainPlayer);
        secondPlayer.setFigure(dealerShapes.choseShape(mainPlayer));
        Player[] players = {mainPlayer, secondPlayer};
        do {
            Quotes.choseSizeMessage();
            TableBuilder tableBuilder = new TableBuilder(Quotes.rowsSizeMessage(input), Quotes.columnSizeMessage(input));
            String[][] table = tableBuilder.table();
            Random random = new Random();
            Quotes.flippingTheCoin();
            int turno = random.nextInt(2);
            Quotes.chosingTheTurn(players[turno].figure);
            do {
                TablePrinter.tablePrinter(table, tableBuilder.getRows(), tableBuilder.getColumns());
                int row, column;
                Quotes.chosePlaceMessage(players[turno]);
                row = Quotes.rowMoveMessage(input, tableBuilder) - 1;
                column = Quotes.columnMoveMessage(input, tableBuilder) - 1;
                if (validateOcupedPlace.isOcuped(table[row][column], Shapes.shapesList())) {
                    Quotes.placeOcupedMessage();
                    continue;
                }
                players[turno].moveFigure(row, column, table);
                turno = ChangeTheTurn.changeTurn(turno);
            } while (ValidateOcuped.isAvaliableYet(table)
                    && !(TableBuilder.VerifieTheWinner.verifieWinner(table, players[ChangeTheTurn.changeTurn(turno)].figure)));
            TablePrinter.tablePrinter(table, tableBuilder.getRows(), tableBuilder.getColumns());
            Quotes.winnerQuote(players[ChangeTheTurn.changeTurn(turno)].figure,
                    (TableBuilder.VerifieTheWinner.verifieWinner(table, players[ChangeTheTurn.changeTurn(turno)].figure)));
            Multiplayer.setHistorial(players[ChangeTheTurn.changeTurn(turno)],
                    players[ChangeTheTurn.changeTurn(turno)]);
        }while(!(JOptionPane.showInputDialog("Desea continuar? X: no, another key: yes")
                .equalsIgnoreCase("X")));
    }

    public static void setHistorial(Player winnerPlayer, Player loserPlayer){
        Map<String, String> winner = new HashMap<>(); winner.put(winnerPlayer.getName(), winnerPlayer.figure);
        Map<String, String> loser = new HashMap<>(); loser.put(loserPlayer.getName(), loserPlayer.figure);
        Map<String, Map<String, String>> players = new Hashtable<String, Map<String, String>>();
        players.put("winner", winner); players.put("loser", loser);
        historial.add(players);
    }
}
