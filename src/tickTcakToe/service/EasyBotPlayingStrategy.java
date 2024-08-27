package tickTcakToe.service;

import tickTcakToe.constants.CellState;
import tickTcakToe.model.Board;
import tickTcakToe.model.Cell;
import tickTcakToe.model.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {
        for (List<Cell> row: board.getGrid()){
            for (Cell cell:row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                        return new Move(new Cell(cell.getRow(),cell.getColumn()), null);
                }
            }
        }
        return null;
    }
}
