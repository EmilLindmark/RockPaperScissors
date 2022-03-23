package game.rockpaperscissors;

import java.util.Optional;

public class MatchInfo {
    private String player1;
    private Move player1Move = null;

    private String player2;
    private Move player2Move = null;

    private String winnerName = null;

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1Move(Move player1Move) {
        this.player1Move = player1Move;
    }

    public Move getPlayer1Move() {
        return player1Move;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2Move(Move player2Move) {
        this.player2Move = player2Move;
    }

    public Move getPlayer2Move() {
        return player2Move;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getWinnerName() {
        return winnerName;
    }
}
