package logic.features;

import logic.gameModes.Apuesta;
import logic.gameModes.Multiplayer;
import logic.gameModes.SinglePlayer;
import logic.gameModes.StartGame;

public enum Games {
    SINGLEPLAYER(new SinglePlayer()),
    MULTIPLAYER(new Multiplayer()),
    BETMODE(new Apuesta());

    public final StartGame GAMEMODE;
    Games(StartGame gameMode){
        GAMEMODE = gameMode;
    }
}
