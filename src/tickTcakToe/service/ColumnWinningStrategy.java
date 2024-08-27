package tickTcakToe.service;

import tickTcakToe.model.Board;
import tickTcakToe.model.Move;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy{
    Map<Integer, HashMap<Character,Integer>> counts=new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {

        int col=move.getCell().getColumn();
        Character symbol=move.getCell().getSymbol().getSymbol();
        if(!counts.containsKey(col)){
            counts.put(col,new HashMap<>());
        }
        HashMap<Character,Integer> countRow=counts.get(col);
        if (!countRow.containsKey(symbol)) {
            countRow.put(symbol,0);
        }
        countRow.put(symbol,countRow.get(symbol)+1);
        if (countRow.get(symbol)==board.getSize()){
            return true;
        }
        return false;
    }

    @Override
    public void handelUndo(Board board, Move move) {
        int column=move.getCell().getRow();
        char symbol=move.getCell().getSymbol().getSymbol();
        counts.get(column).put(symbol,counts.get(column).get(symbol)-1);
    }
}
