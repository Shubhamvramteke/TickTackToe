package tickTcakToe.model;

import tickTcakToe.constants.BotDifficultyLevel;
import tickTcakToe.constants.PlayerType;
import tickTcakToe.service.BotPlayingStrategy;
import tickTcakToe.service.BotPlayingStrategyFactory;

public class BotPlayer extends Player{
    private BotDifficultyLevel difficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public BotPlayer(int id, String name, PlayerType playerType,Symbol symbol, BotDifficultyLevel difficultyLevel) {
        super(id,name,symbol,playerType);
        this.botPlayingStrategy= BotPlayingStrategyFactory.getBotPlayingStrategy(difficultyLevel);
        this.difficultyLevel = difficultyLevel;
    }
    @Override
    public Move makeMove(Board board){
        return botPlayingStrategy.makeMove(board);
    }


}
