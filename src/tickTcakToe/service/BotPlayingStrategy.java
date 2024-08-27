package tickTcakToe.service;

import tickTcakToe.model.Board;
import tickTcakToe.model.Move;

public interface BotPlayingStrategy {
    public Move makeMove(Board board);
}
