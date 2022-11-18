package logic.gameModes;

import logic.players.MainPlayer;

public interface StartGame {
    void startGame(MainPlayer mainPlayer) throws Exception;
    void goToGame(MainPlayer mainPlayer) throws Exception;
}
