package api.utils;

import game.rockpaperscissors.entity.GameInfo;
import game.rockpaperscissors.entity.Player;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
    public static ResponseEntity<String> createMatchInfoResponse(GameInfo gameInfo){
        StringBuilder body = new StringBuilder();

        body.append("===Match Info===\n");
        if(gameInfo.getWinner() == null){
            int noOfPlayers = gameInfo.getPlayer2() == null ? 1 : 2;
            body.append("Match in progress... \n");
            body.append("Number of players: " + noOfPlayers + "/2\n");
        } else{
            body.append(
                    createPlayerinfo(gameInfo.getPlayer1()) + "\n");
            body.append(
                    createPlayerinfo(gameInfo.getPlayer2()) + "\n");
            body.append("Winner: " + gameInfo.getWinner());
        }

        return ResponseEntity.ok().body(body.toString());
    }

    private static String createPlayerinfo(Player player){
        String result = "Name: " + player.getName() +
                ", Move: " + player.getMove().toString();
        return result;
    }
}
