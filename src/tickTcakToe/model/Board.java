package tickTcakToe.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> grid;

    public Board(int size) {
        this.size = size;
        grid=new ArrayList<>();
        for(int i=0;i<this.size;i++){
            grid.add(new ArrayList<>());
            for(int j=0;j<this.size;j++){
                grid.get(i).add(new Cell(i,j));
            }
        }
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<Cell>> grid) {
        this.grid = grid;
    }

    public void display() {
        for (List<Cell> row: grid){
            for(Cell cell: row){
                cell.display();
            }
            System.out.println();
        }
    }
}
