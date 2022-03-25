package api;

import api.utils.ResponseBuilder;
import game.rockpaperscissors.GameService;
import game.rockpaperscissors.entity.GameInfo;
import game.rockpaperscissors.Move;
import game.rockpaperscissors.entity.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class RestControllerTest {

    @InjectMocks
    RestController cut;

    @Mock
    GameService gameService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMatchInfo(){
        UUID id = UUID.randomUUID();
        GameInfo gameInfoMock = Mockito.mock(GameInfo.class);
        Mockito.when(gameService.getGameInfo(id)).thenReturn(gameInfoMock);

        ResponseEntity<String> response = cut.getGameInfo(id.toString());

        Mockito.verify(gameService, Mockito.times(1))
                .getGameInfo(id);
        Assertions.assertFalse(response.getBody().isEmpty());
    }

    @Test
    public void testCreate(){
        String name = "Nils";
        String body = "{name: Nils}";

        cut.createGame(body);

        Mockito.verify(gameService, Mockito.times(1))
                .createGame(name);
    }

    @Test
    public void testJoin(){
        UUID id = UUID.randomUUID();
        String playerName = "Samuel";
        String body = "{name: "+ playerName + "}";

        cut.joinGame(id.toString(), body);

        Mockito.verify(gameService, Mockito.times(1))
                .joinGame(id, playerName);
    }


    @Test
    public void testMove(){
        UUID id = UUID.randomUUID();
        String playerName = "Amanda";
        Move playerMove = Move.SCISSORS;

        String body = "{name: "+ playerName + ", move: " + playerMove + "}";

        cut.move(id.toString(), body);

        Mockito.verify(gameService, Mockito.times(1))
                .recordAction(id, playerName, playerMove.toString());
    }
}
