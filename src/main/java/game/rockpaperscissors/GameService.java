package game.rockpaperscissors;

import game.rockpaperscissors.entity.GameInfo;
import game.rockpaperscissors.entity.Player;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GameService {

    private final Map<UUID, GameInfo> games = new HashMap<>();

    private static final String errorMsg = "Could not find game id or player name.";

    /**
     * Get info for specified game
     * @param id
     * @return
     */
    public GameInfo getGameInfo(UUID id){
        if(!games.containsKey(id)){
            throw new NoSuchElementException(errorMsg);
        }
        return games.get(id);
    }

    /**
     * Creates a game for player
     * @param playerName
     * @return
     */
    public UUID createGame(String playerName){
        //TODO: Fix
        UUID id = UUID.fromString("8fe97dfa-0fb0-4f4f-9ec2-bcc7a8cbe5ad");//UUID.randomUUID();
        GameInfo gameInfo = new GameInfo();
        gameInfo.setPlayer1(new Player(playerName));
        games.put(id, gameInfo);
        return id;
    }

    /**
     * Adds player to existing game
     * @param id
     * @param playerName
     */
    public void joinGame(UUID id, String playerName) {
        GameInfo gameInfo = getGameInfo(id);
        gameInfo.setPlayer2(new Player(playerName));
    }

    /**
     * Records an action for player.
     * If both players has recorded an action a winner is determined.
     * @param id
     * @param name
     * @param move
     */
    public void recordAction(UUID id, String name, String move){
        GameInfo gameInfo = getGameInfo(id);
        Player player = getPlayerWithName(gameInfo, name);
        player.setMove(Move.valueOf(move.toUpperCase()));

        if(gameInfo.getWinner() == null &&
                gameInfo.getPlayer1().getMove() != null &&
                gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getMove() != null) {
            determineWinner(gameInfo);
        }
    }

    private Player getPlayerWithName(GameInfo gameInfo, String name){
        if(gameInfo.getPlayer1().getName().equals(name)){
            return gameInfo.getPlayer1();
        } else if(gameInfo.getPlayer2() != null && gameInfo.getPlayer2().getName().equals(name)){
            return gameInfo.getPlayer2();
        }
        throw new IllegalArgumentException(errorMsg);
    }

    private void determineWinner(GameInfo gameInfo){
        if(gameInfo.getPlayer1().getMove().equals(gameInfo.getPlayer2().getMove())){
            gameInfo.setWinner("tie");
            return;
        }

        if(gameInfo.getPlayer1().getMove().equals(Move.PAPER)){
            if(gameInfo.getPlayer2().getMove().equals(Move.ROCK)){
                gameInfo.setWinner(gameInfo.getPlayer1().getName());
            } else {
                gameInfo.setWinner(gameInfo.getPlayer2().getName());
            }
        } else if(gameInfo.getPlayer1().getMove().equals(Move.ROCK)){
            if(gameInfo.getPlayer2().getMove().equals(Move.SCISSORS)){
                gameInfo.setWinner(gameInfo.getPlayer1().getName());
            } else {
                gameInfo.setWinner(gameInfo.getPlayer2().getName());
            }
        } else {
            //Player1 has scissors
            if(gameInfo.getPlayer2().getMove().equals(Move.PAPER)){
                gameInfo.setWinner(gameInfo.getPlayer1().getName());
            } else {
                gameInfo.setWinner(gameInfo.getPlayer2().getName());
            }
        }
    }
}
