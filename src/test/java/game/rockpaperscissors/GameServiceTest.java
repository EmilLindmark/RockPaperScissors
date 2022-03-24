package game.rockpaperscissors;

import game.rockpaperscissors.entity.GameInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.UUID;

public class GameServiceTest {


    GameService cut;

    @Before
    public void init(){
        cut = new GameService();
    }

    @Test
    public void testCreateGame() {
        String playerName = "Adrian";
        UUID id = createGame(playerName);

        GameInfo gameInfo = cut.getGameInfo(id);

        Assertions.assertAll(
                () -> Assert.assertEquals(playerName, gameInfo.getPlayer1().getName()),
                () -> Assert.assertNull(gameInfo.getPlayer1().getMove())
        );
    }

    @Test
    public void testGetNonExistingGame(){
        Assert.assertThrows(NoSuchElementException.class,
                () -> cut.getGameInfo(UUID.randomUUID()));
    }

    @Test
    public void testJoinGame(){
        String player1Name = "Adrian";
        UUID id = createGame(player1Name);

        String player2Name = "Anna";
        cut.joinGame(id, player2Name);

        GameInfo gameInfo = cut.getGameInfo(id);

        Assertions.assertAll(
                () -> Assert.assertEquals(player1Name, gameInfo.getPlayer1().getName()),
                () -> Assert.assertNull(gameInfo.getPlayer1().getMove()),
                () -> Assert.assertEquals(player2Name, gameInfo.getPlayer2().getName()),
                () -> Assert.assertNull(gameInfo.getPlayer2().getMove())
        );
    }

    @Test
    public void testRecordAction() {
        String playerName = "Adrian";
        Move playerMove = Move.SCISSORS;
        UUID id = createGame(playerName);

        cut.recordAction(id, playerName, playerMove.toString());

        GameInfo gameInfo = cut.getGameInfo(id);

        Assertions.assertAll(
                () -> Assert.assertEquals(playerName, gameInfo.getPlayer1().getName()),
                () -> Assert.assertEquals(playerMove, gameInfo.getPlayer1().getMove()),
                () -> Assert.assertNull(gameInfo.getWinner())
        );
    }

    @Test
    public void testRecordActionUnknownPlayer() {
        String playerName = "Adrian";
        String unknownPlayerName = "Unknown";
        Move playerMove = Move.SCISSORS;
        UUID id = createGame(playerName);

        Assert.assertThrows(IllegalArgumentException.class,
                () ->cut.recordAction(id, unknownPlayerName, playerMove.toString()));
    }

    @Test
    public void testWinner() {
        String player1Name = "Adrian";
        Move player1Move = Move.SCISSORS;
        String player2Name = "Caroline";
        Move player2Move = Move.ROCK;

        UUID id = createGame(player1Name);

        cut.joinGame(id, player2Name);
        cut.recordAction(id, player1Name, player1Move.toString());
        cut.recordAction(id, player2Name, player2Move.toString());

        GameInfo gameInfo = cut.getGameInfo(id);

        Assertions.assertAll(
                () -> Assert.assertEquals(player1Name, gameInfo.getPlayer1().getName()),
                () -> Assert.assertEquals(player1Move, gameInfo.getPlayer1().getMove()),
                () -> Assert.assertEquals(player2Name, gameInfo.getPlayer2().getName()),
                () -> Assert.assertEquals(player2Move, gameInfo.getPlayer2().getMove()),
                () -> Assert.assertEquals(player2Name, gameInfo.getWinner())
        );
    }

    private UUID createGame(String playerName){
        return cut.createGame(playerName);
    }
}
