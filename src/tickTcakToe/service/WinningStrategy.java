package tickTcakToe.service;

import tickTcakToe.model.Board;
import tickTcakToe.model.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
    public void handelUndo(Board board,Move move);
}
