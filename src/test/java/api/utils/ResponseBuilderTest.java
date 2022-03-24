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
    public void testCreateMatchInfoResponseNoPlayers(){
        GameInfo gameInfoMock = Mockito.mock(GameInfo.class);

        ResponseEntity<String> response = ResponseBuilder
                .createMatchInfoResponse(gameInfoMock);

        Assert.assertTrue(response.getBody().isEmpty());
    }

    @Test
    public void testCreateMatchInfoResponseTwoPlayers(){
        GameInfo gameInfoMock = Mockito.mock(GameInfo.class);
        Player player1Mock = Mockito.mock(Player.class);
        Player player2Mock = Mockito.mock(Player.class);

        configureMocks(gameInfoMock, player1Mock, player2Mock);

        ResponseEntity<String> response = ResponseBuilder
                .createMatchInfoResponse(gameInfoMock);

        System.out.println("resp: " + response.getBody());

        Assertions.assertAll(
                () -> Assert.assertTrue(response.getBody().contains(player1Name)),
                () -> Assert.assertTrue(response.getBody().contains(player2Name))
        );
    }

    @Test
    public void testCreateMatchInfoResponseNoRecordedMove(){
        GameInfo gameInfoMock = Mockito.mock(GameInfo.class);
        Player playerMock = Mockito.mock(Player.class);
        configureMocks(gameInfoMock, playerMock, null);

        ResponseEntity<String> response = ResponseBuilder
                .createMatchInfoResponse(gameInfoMock);

        Assertions.assertAll(
                () -> Assert.assertTrue(response.getBody().contains(player1Name)),
                () -> Assert.assertTrue(response.getBody().contains(ResponseBuilder.moveNotRecorded))
        );
    }

    @Test
    public void testCreateMatchInfoResponseMoveHidden(){
        GameInfo gameInfoMock = Mockito.mock(GameInfo.class);
        Player playerMock = Mockito.mock(Player.class);
        configureMocks(gameInfoMock, playerMock, null);
        Move playerMove = Move.PAPER;
        Mockito.when(playerMock.getMove()).thenReturn(playerMove);

        ResponseEntity<String> response = ResponseBuilder
                .createMatchInfoResponse(gameInfoMock);

        Assertions.assertAll(
                () -> Assert.assertTrue(response.getBody().contains(player1Name)),
                () -> Assert.assertTrue(response.getBody().contains(ResponseBuilder.moveHidden))
        );
    }

    @Test
    public void testCreateMatchInfoResponseMoveVisable(){
        GameInfo gameInfoMock = Mockito.mock(GameInfo.class);
        Player playerMock = Mockito.mock(Player.class);
        configureMocks(gameInfoMock, playerMock, null);
        Move playerMove = Move.PAPER;
        Mockito.when(playerMock.getMove()).thenReturn(playerMove);
        Mockito.when(gameInfoMock.getWinner()).thenReturn(player1Name);

        ResponseEntity<String> response = ResponseBuilder
                .createMatchInfoResponse(gameInfoMock);

        Assertions.assertAll(
                () -> Assert.assertTrue(response.getBody().contains(player1Name)),
                () -> Assert.assertTrue(response.getBody().contains(playerMove.toString())),
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
