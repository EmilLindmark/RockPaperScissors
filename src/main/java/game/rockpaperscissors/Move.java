package game.rockpaperscissors;

import java.util.Arrays;

public enum Move {
    /**
     * {@link #winnsOver(Move other)} depends on element order.
     * Every element defeats preceding element and is defeated by following element.
     */
    ROCK, PAPER, SCISSORS;

    /**
     * Determines if this move winns over another move.
     * @param other
     * @return True if this move winns,
     * false if other move winns and null if it's a tie.
     */
    public Boolean winnsOver(Move other){
        int thisIndex = Arrays.asList(values()).indexOf(this);
        int otherIndex = Arrays.asList(values()).indexOf(other);
        if(thisIndex == otherIndex){
            return null;
        }

        return (thisIndex + values().length - otherIndex) % 3 == 1;
    }
}
