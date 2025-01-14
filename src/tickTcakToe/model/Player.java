package tickTcakToe.model;

import tickTcakToe.constants.PlayerType;

import java.util.Scanner;

public abstract class Player {
    private int id;
    private String name;
    private Symbol symbol;
    private PlayerType playerType;

    private Scanner scanner=new Scanner(System.in);

    public Player(int id, String name, Symbol symbol, PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public  Move makeMove(Board board){
        System.out.println("PLease enter the row where you want to place the symbol");
        int row=scanner.nextInt();

        System.out.println("PLease enter the column where you want to place the symbol");
        int column=scanner.nextInt();

        return new Move(new Cell(row,column),this);
    }
}
