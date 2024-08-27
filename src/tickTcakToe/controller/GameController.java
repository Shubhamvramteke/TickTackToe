package tickTcakToe.controller;

import tickTcakToe.constants.GameState;
import tickTcakToe.model.Game;
import tickTcakToe.model.Player;
import tickTcakToe.service.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int dimensions,
                          List<Player> players ,
                          List<WinningStrategy> winningStrategies){
      return Game.getBuilder()
              .setDimensions(dimensions)
              .setPlayers(players)
              .setWinningStrategies(winningStrategies)
              .build();
    }
    public GameState checkState(Game game){
        return game.getGameState();
    }

    public void makeMove(Game game){
        game.makeMove();
    }
    public void displayBoard(Game game){
        game.displayBoard();
    }

    public Player getWinner(Game game){
        return null;
    }

    public void undo(Game game){
        game.undo();
    }

    //Start the game
    // when Game is in progress
            // Display the game
            // make the move
            // Display the move on board | Check the winner | update game state
            //check the state
            //If the game state is success
                //Get the winner and display
                //else if Game is draw
                // Declare it draw


}
