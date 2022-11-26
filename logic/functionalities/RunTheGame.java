package logic.functionalities;

import logic.players.MainPlayer;

public class RunTheGame {

    public static void run(MainPlayer mainPlayer) throws Exception {
        ChosingAGameMode.showTheGameModes();
        ChosingAGameMode.goToMode(ChosingAGameMode.selectTheGame(mainPlayer), mainPlayer);
    }
}
