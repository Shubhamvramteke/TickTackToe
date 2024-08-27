package tickTcakToe.model;

import tickTcakToe.constants.CellState;
import tickTcakToe.constants.GameState;
import tickTcakToe.constants.PlayerType;
import tickTcakToe.service.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;

    private int nextPlayerIndex;
    private List<WinningStrategy> winningStrategies;

    private  Game(Builder builder){
        this.board=new Board(builder.dimensions);
        this.players=builder.players;
        this.winningStrategies=builder.winningStrategies;
        this.moves=new ArrayList<>();
        this.gameState=GameState.IN_PROGRESS;
        this.nextPlayerIndex=0;
        this.winner=null;
    }
    public static Builder getBuilder(){
        return new Builder();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public void displayBoard() {
        board.display();
    }
    public boolean validateMove(Move move){
        int row=move.getCell().getRow();
        int col=move.getCell().getColumn();

        // If input is outside boundry
        if(row<0 || row> board.getSize()-1 || col> board.getSize()-1){
            return false;
        }
        return board.getGrid().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    public void makeMove() {
        Player currentPlayer=players.get(nextPlayerIndex);
        System.out.println("it's "+currentPlayer.getName()+" turn! please make the move ");
        Move move=currentPlayer.makeMove(board);
        if(!validateMove(move)){
            System.out.println("Not a valid move");
            return;
        }
        int row=move.getCell().getRow();
        int col=move.getCell().getColumn();

        Cell cellToChane=board.getGrid().get(row).get(col);
        cellToChane.setCellState(CellState.FILLED);
        cellToChane.setSymbol(currentPlayer.getSymbol());


        move.setCell(cellToChane);
        move.setPlayer(currentPlayer);

        moves.add(move);

        nextPlayerIndex++;
        nextPlayerIndex%=players.size();

        // Confom if there is change in Game State
        if (checkWinner(move)){
            setWinner(currentPlayer);
            setGameState(GameState.SUCCESS);
        } else if (moves.size()== board.getSize()* board.getSize()) {
            setWinner(null);
            setGameState(GameState.DRAW);
        }

    }
    public boolean checkWinner(Move move){
        for(WinningStrategy strategy:winningStrategies){
            if(strategy.checkWinner(board,move)){
                return true;
            }
        }
        return false;
    }

    public void undo() {
        if(moves.isEmpty()){
            System.out.println("Nothing to Undo");
            return;
        }
        Move lasstMove=moves.get(moves.size()-1);
        moves.remove(moves.size()-1);
        lasstMove.getCell().setCellState(CellState.EMPTY);
        lasstMove.getCell().setSymbol(null);
        nextPlayerIndex--;
        nextPlayerIndex=(nextPlayerIndex+players.size()) % players.size();

        for(WinningStrategy strategy:winningStrategies){
            strategy.handelUndo(board,lasstMove);
        }

        setGameState(GameState.IN_PROGRESS);
        setWinner(null);
    }


    public static class Builder{
        private int  dimensions;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public Builder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private void validate(){
            //check player count
            if(players.size()>dimensions-1){
                throw new RuntimeException("Invalid Players count");
            }
            // We want to have only one bot in the game
            int botCount=0;
            for (Player player:players){
                if (player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount>1){
                throw new RuntimeException("Mort than one bot is not allowed");
            }

            // Every player should have a separate symbol
            Set<Character> symbolChar=new HashSet<>();
            for(Player player:players){
                if(!symbolChar.add(player.getSymbol().getSymbol())){
                    throw new RuntimeException("Multiple players have same symbol");
                }
            }
        }
        public Game build(){
            //validation
            validate();
            return new Game(this);
        }
    }
}
