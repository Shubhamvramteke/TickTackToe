package tickTcakToe.model;

import tickTcakToe.constants.CellState;


public class Cell {
    private int row;
    private int column;
    private Symbol symbol;
    private CellState cellState;

    private Player player;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.cellState=CellState.EMPTY;
        this.symbol=null;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void display() {
        if (symbol!=null){
            System.out.print("| "+symbol.getSymbol()+" |");
        }
        else{
            System.out.print("| - |");
        }
    }
}
