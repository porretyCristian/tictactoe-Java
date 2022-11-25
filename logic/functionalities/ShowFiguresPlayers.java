package logic.functionalities;

import logic.players.Player;

public class ShowFiguresPlayers {
    public static String[] figures(Player[][] semifinal){
        String[] figuresPlayers = new String[4];
        int indexToPlace = 0;
        for(Player[] match:semifinal){
            for(Player player:match){
                figuresPlayers[indexToPlace] = player.figure;
                indexToPlace++;
            }
        }
        return figuresPlayers;
    }
}
