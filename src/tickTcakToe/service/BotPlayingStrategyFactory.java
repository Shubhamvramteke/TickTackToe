package tickTcakToe.service;

import tickTcakToe.constants.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel){
        if (botDifficultyLevel.equals(BotDifficultyLevel.EASY)){
            return new EasyBotPlayingStrategy();
        }
        return null;
    }
}
