package logic.gameModes;

import logic.features.Shapes;
import logic.functionalities.SearchTheLowest;
import logic.functionalities.TableBuilder;
import logic.functionalities.TablePrinter;
import logic.players.CPU;
import logic.players.MainPlayer;
import logic.players.Player;
import logic.validations.ValidateOcuped;
import logic.validations.validateOcupedPlace;
import logic.visuals.Quotes;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MiniCup implements AsignFigure{
    //las fases estaran en 3 arrays, una matriz con las semifinales, una de tercer lugar y otra de final
    public final Player[][] semiFinal = new Player[2][2];
    public final Player[] finalGame = new Player[2];
    public final Player[] thirdPlace = new Player[2];
    public final ArrayList<Integer> records = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    boolean isChampion = false;

    @Override
    public String asignAnFigure(MainPlayer player) throws Exception{
        //asignar una figura, lo que se tiene pensado es ver si una figura de la lista de jugadores esta duplicada
        //mientras esta figura este duplicada se le asigna una figura aleatoria hasta que esta no este duplicada
    }

    public void goToGame(MainPlayer mainPlayer, String fase) throws Exception {
        if(fase.equalsIgnoreCase("semi")){
            Quotes.showTheMatches(semiFinal);
            startGame(mainPlayer, semiFinal[0], fase);
        }
        else if(fase.equalsIgnoreCase("third")){
            Quotes.showTheMatches(thirdPlace);
            startGame(mainPlayer, thirdPlace, fase);
        }
        else if(fase.equalsIgnoreCase("final")){
            Quotes.showTheMatches(finalGame);
            startGame(mainPlayer, finalGame, fase);
        }
    }

    public void startGame(MainPlayer mainPlayer, Player[] match, String fase) throws InterruptedException, Exception {
        do {
            SearchTheLowest.showTheBestTime(records, mainPlayer, (isChampion && fase == "final"));
            Quotes.choseSizeMessage();
            TableBuilder tableBuilder = new TableBuilder(Quotes.rowsSizeMessage(input), Quotes.columnSizeMessage(input));
            String[][] table = tableBuilder.table();
            Random random = new Random();
            Quotes.flippingTheCoin();
            int turno = random.nextInt(2);
            Quotes.chosingTheTurn(match[turno].figure);
            Instant startGame = Instant.now();
            do {
                TablePrinter.tablePrinter(table, tableBuilder.getRows(), tableBuilder.getColumns());
                int row, column;
                if(turno == 0){
                    Quotes.chosePlaceMessage(match[turno]);
                    row = Quotes.rowMoveMessage(input, tableBuilder) - 1;
                    column = Quotes.columnMoveMessage(input, tableBuilder) - 1;
                    if(validateOcupedPlace.isOcuped(table[row][column], Shapes.shapesList())){
                        Quotes.placeOcupedMessage();
                        continue;
                    }
                    match[turno].moveFigure(row, column, table);
                }else{
                    row = random.nextInt(tableBuilder.getRows());
                    column = random.nextInt(tableBuilder.getColumns());
                    if(validateOcupedPlace.isOcuped(table[row][column], Shapes.shapesList())){
                        continue;
                    }
                    Quotes.waitTheOponent();
                    match[turno].moveFigure(row, column, table);
                }
                turno = ChangeTheTurn.changeTurn(turno);
            }while(ValidateOcuped.isAvaliableYet(table)
                    && !(TableBuilder.VerifieTheWinner.verifieWinner(table, match[ChangeTheTurn.changeTurn(turno)].figure)));
            TablePrinter.tablePrinter(table, tableBuilder.getRows(), tableBuilder.getColumns());
            Instant endGame = Instant.now();
            records.add((int) Duration.between(startGame, endGame).toMillis()/1000);
        }while(true);
    }


    private void setMatch(MainPlayer mainPlayer, Player[][] semiFinal){
        semiFinal[0] = {mainPlayer, new CPU("CPU-1", )}
    }
}
