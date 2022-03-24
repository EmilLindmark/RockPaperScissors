package api.utils;

import game.rockpaperscissors.entity.GameInfo;
import game.rockpaperscissors.entity.Player;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    protected final static String moveNotRecorded = "Not recorded";
    protected final static String moveHidden = "Hidden";

    public static ResponseEntity<String> createMatchInfoResponse(GameInfo mi){
        StringBuilder body = new StringBuilder();

        boolean hideWinner = mi.getWinner() == null;
        if(mi.getPlayer1() != null){
            body.append("===Match Info===\n");
            body.append(
                    createPlayerinfo(mi.getPlayer1(), hideWinner) + "\n");
        }

        if(mi.getPlayer2() != null){
            body.append(
                    createPlayerinfo(mi.getPlayer2(), hideWinner) + "\n");
        }

        if(!hideWinner){
            body.append("Winner: " + mi.getWinner());
        }

        return ResponseEntity.ok().body(body.toString());
    }

    private static String createPlayerinfo(Player player, boolean hideMove){
        String result = "Name: " + player.getName() +
                ", Move: " + createMoveInfo(player, hideMove);
        return result;
    }

    private static String createMoveInfo(Player player, boolean hideMove){
        if(player.getMove() == null){
            return moveNotRecorded;
        }
        else if(hideMove){
            return moveHidden;
        }

        return player.getMove().toString();
    }
}
