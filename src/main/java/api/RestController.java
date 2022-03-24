package api;

import api.utils.ResponseBuilder;
import game.rockpaperscissors.GameService;
import game.rockpaperscissors.entity.GameInfo;
import api.utils.RequestParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@org.springframework.web.bind.annotation.RestController
@ComponentScan("game.rockpaperscissors")
public class RestController {

    @Autowired
    private GameService gameService;

    @GetMapping("/api/games/{id}")
    public ResponseEntity<String> getGameInfo(@PathVariable(value = "id") String id){
        System.out.println("Returning matchinfo for game " + id);
        GameInfo mi = gameService.getGameInfo(UUID.fromString(id));
        return ResponseBuilder.createMatchInfoResponse(mi);
    }

    @PostMapping("/api/games")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createGame (@Validated @RequestBody String body) {
        String name = RequestParser.getPlayerName(body);

        System.out.println("Creating game for " + name);
        return gameService.createGame(name);
    }

    @PostMapping("/api/games/{id}/join")
    public void joinGame (@PathVariable(value = "id") String id,
                          @Validated @RequestBody String body) {
        String name = RequestParser.getPlayerName(body);

        gameService.joinGame(UUID.fromString(id), name);
        System.out.println(name + " joined " + id);
    }

    @PostMapping("/api/games/{id}/move")
    public void move (@PathVariable(value = "id") String id,
                          @Validated @RequestBody String body) {
        String name = RequestParser.getPlayerName(body);
        String move = RequestParser.getMove(body);

        System.out.println("Set move " + move + " for " + name);
        gameService.recordAction(UUID.fromString(id), name, move);
    }
}
