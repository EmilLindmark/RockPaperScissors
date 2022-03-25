package api.utils;

import game.rockpaperscissors.entity.GameInfo;
import game.rockpaperscissors.Move;
import game.rockpaperscissors.entity.Player;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

public class ResponseBuilderTest {

    private final String player1Name = "Adam";
    private final String player2Name = "Mikaela";

    @Test
    public void testCreateMatchInfoResponseWinnerNotDetermined(){
        GameInfo gameInfoMock = Mockito.mock(GameInfo.class);
        Player player1Mock = Mockito.mock(Player.class);
        Player player2Mock = Mockito.mock(Player.class);

        configureMocks(gameInfoMock, player1Mock, player2Mock);

        ResponseEntity<String> response = ResponseBuilder
                .createMatchInfoResponse(gameInfoMock);

        System.out.println("resp: " + response.getBody());

        Assertions.assertAll(
                () -> Assert.assertTrue(!response.getBody().contains(player1Name)),
                () -> Assert.assertTrue(!response.getBody().contains(player2Name))
        );
    }

    @Test
    public void testCreateMatchInfoResponseWinnerDetermined(){
        GameInfo gameInfoMock = Mockito.mock(GameInfo.class);
        Player player1Mock = Mockito.mock(Player.class);
        Player player2Mock = Mockito.mock(Player.class);
        configureMocks(gameInfoMock, player1Mock, player2Mock);
        Move player1Move = Move.PAPER;
        Move player2Move = Move.ROCK;
        Mockito.when(player1Mock.getMove()).thenReturn(player1Move);
        Mockito.when(player2Mock.getMove()).thenReturn(player2Move);
        Mockito.when(gameInfoMock.getWinner()).thenReturn(player1Name);

        ResponseEntity<String> response = ResponseBuilder
                .createMatchInfoResponse(gameInfoMock);

        Assertions.assertAll(
                () -> Assert.assertTrue(response.getBody().contains(player1Name)),
                () -> Assert.assertTrue(response.getBody().contains(player1Move.toString())),
                () -> Assert.assertTrue(response.getBody().contains(player2Name)),
                () -> Assert.assertTrue(response.getBody().contains(player2Move.toString())),
                () -> Assert.assertTrue(response.getBody().contains("Winner: " + player1Name))
        );
    }

    private void configureMocks(GameInfo gameInfoMock, Player player1Mock, Player player2Mock){
        Mockito.when(gameInfoMock.getPlayer1()).thenReturn(player1Mock);
        Mockito.when(player1Mock.getName()).thenReturn(player1Name);

        if(player2Mock != null){
            Mockito.when(gameInfoMock.getPlayer2()).thenReturn(player2Mock);
            Mockito.when(player2Mock.getName()).thenReturn(player2Name);
        }
    }
}
