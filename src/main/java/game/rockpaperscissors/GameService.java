package game.rockpaperscissors;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameService {

    private GameService(){}

    private static GameService instance = null;

    private Map<UUID, MatchInfo> games = new HashMap();

    public static GameService getInstance (){
        if(instance == null){
            instance = new GameService();
        }
        return instance;
    }

    //TODO: what to do if not all info is present?
    public MatchInfo getMatchInfo(UUID id){
        if(!games.containsKey(id)){
            throw new RuntimeException("Could not find game id: " + id); //TODO: faulthandling?
        }
        return games.get(id);
    }

    public UUID createGame(String playerName){
        //TODO: Fix
        UUID id = UUID.fromString("8fe97dfa-0fb0-4f4f-9ec2-bcc7a8cbe5ad");//UUID.randomUUID();
        MatchInfo mi = new MatchInfo();
        mi.setPlayer1(playerName);
        games.put(id, mi);
        return id;
    }

    public void joinGame(UUID id, String playerName) {
        MatchInfo mi = getMatchInfo(id);
        mi.setPlayer2(playerName);
    }

    public void recordAction(UUID id, String name, String move){
        MatchInfo mi = getMatchInfo(id);
        setMove(mi, name, move);

        if(mi.getWinnerName() == null &&
                mi.getPlayer1Move() != null && mi.getPlayer2Move() != null){
            determineWinner(mi);
        }
    }

    private void setMove(MatchInfo mi, String name, String move){
        if(mi.getPlayer1().equals(name)){
            mi.setPlayer1Move(Move.valueOf(move));
        } else if(mi.getPlayer2() != null && mi.getPlayer2().equals(name)){
            mi.setPlayer2Move(Move.valueOf(move));
        } else{
            //TODO: Runtime?
            throw new IllegalStateException("Player '" + name + "' is not playing in this game.");
        }
    }

    private void determineWinner(MatchInfo mi){
        //TODO:Snyggare?
        if(mi.getPlayer1Move().equals(mi.getPlayer2Move())){
            mi.setWinnerName("tie");
            return;
        }

        if(mi.getPlayer1Move().equals(Move.PAPER)){
            if(mi.getPlayer2Move().equals(Move.ROCK)){
                mi.setWinnerName(mi.getPlayer1());
            }
            mi.setWinnerName(mi.getPlayer2());
        } else if(mi.getPlayer1Move().equals(Move.ROCK)){
            if(mi.getPlayer2Move().equals(Move.SCISSORS)){
                mi.setWinnerName(mi.getPlayer1());
            }
            mi.setWinnerName(mi.getPlayer2());
        } else {
            //P1 has scissors
            if(mi.getPlayer2Move().equals(Move.PAPER)){
                mi.setWinnerName(mi.getPlayer1());
            }
            mi.setWinnerName(mi.getPlayer2());
        }
    }
}
