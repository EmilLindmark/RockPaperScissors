package api.utils;

import game.rockpaperscissors.Move;
import org.junit.Assert;
import org.junit.Test;

public class RequestParserTest {

    @Test
    public void testGetPlayerName(){
        String playerName = "Sofie";
        String body = "{name: " + playerName + "}";
        Assert.assertEquals(playerName, RequestParser.getPlayerName(body));
    }

    @Test
    public void testGetPlayerMove(){
        Move playerMove = Move.ROCK;
        String body = "{move: " + playerMove + "}";
        Assert.assertEquals(playerMove.toString(), RequestParser.getMove(body));
    }
}
