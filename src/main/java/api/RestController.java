package api;

import game.rockpaperscissors.GameService;
import game.rockpaperscissors.MatchInfo;
import game.rockpaperscissors.utils.RequestParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    //@Autowired
    //private GameService gameService;

    @GetMapping("/api/games/{id}")
    public MatchInfo getMatchInfo(@PathVariable(value = "id") String id){
        System.out.println("Returning matchinfo for game " + id);
        return GameService.getInstance().getMatchInfo(UUID.fromString(id));
    }

    @PostMapping("/api/games")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createGame (@Validated @RequestBody String body) {
        String name = RequestParser.getPlayerName(body);

        System.out.println("Creating game for " + name);
        return GameService.getInstance().createGame(name);
    }

    @PostMapping("/api/games/{id}/join")
    @ResponseStatus(HttpStatus.OK)
    public void joinGame (@PathVariable(value = "id") String id,
                          @Validated @RequestBody String body) {
        String name = RequestParser.getPlayerName(body);

        System.out.println(name + " Joining " + id);
        GameService.getInstance().joinGame(UUID.fromString(id), name);
    }

    @PostMapping("/api/games/{id}/move")
    @ResponseStatus(HttpStatus.OK)
    public void move (@PathVariable(value = "id") String id,
                          @Validated @RequestBody String body) {
        String name = RequestParser.getPlayerName(body);
        String move = RequestParser.getMove(body);

        System.out.println("Set move " + move + " for " + name);
        GameService.getInstance().recordAction(UUID.fromString(id), name, move);
    }

}
