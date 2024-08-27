import tickTcakToe.constants.BotDifficultyLevel;
import tickTcakToe.constants.GameState;
import tickTcakToe.constants.PlayerType;
import tickTcakToe.controller.GameController;
import tickTcakToe.model.*;
import tickTcakToe.service.RowWinningStrategy;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    //Client
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {

        List<Player> players=new ArrayList<>();
        Player player=new HumanPlayer(1,"Shubham",new Symbol('X'), PlayerType.HUMAN);
        Player player1=new BotPlayer(2,"BOT",PlayerType.BOT,new Symbol('O'), BotDifficultyLevel.EASY);
        players.add(player);
        players.add(player1);
        GameController gc=new GameController();
        Game game=gc.startGame(3,players,List.of(new RowWinningStrategy()));
        gc.displayBoard(game);

        while(gc.checkState(game).equals(GameState.IN_PROGRESS)){
            gc.makeMove(game);
            gc.displayBoard(game);
            System.out.println("Do you want to Undo ? [Y/N]");
            String undoAns=sc.nextLine();
            if(undoAns.equals("Y")){
                gc.undo(game);
                System.out.println("Undo is Successfull");
                gc.displayBoard(game);
            }
        }

        if(game.getGameState().equals(GameState.SUCCESS)){
            System.out.println("game won by "+game.getWinner().getName());
        }
        else if (game.getGameState().equals(GameState.DRAW)){
            System.out.println("game is draw");
        }

    }
}
//1) Creating Models
//2) Get ready with controller and client interaction with client}