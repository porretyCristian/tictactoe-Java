package logic.gameModes;

public interface ChangeTheTurn {
    static int changeTurn(int turn){ return (turn == 1) ? 0 : 1; }
}
