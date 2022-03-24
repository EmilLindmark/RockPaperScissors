package game.rockpaperscissors.entity;

import game.rockpaperscissors.Move;

public class Player {
    private final String name;
    private Move move;

    public Player(String name){
        this.name = name;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    public String getName() {
        return name;
    }
}
