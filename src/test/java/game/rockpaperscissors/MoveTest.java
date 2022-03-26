package game.rockpaperscissors;

import org.junit.Assert;
import org.junit.Test;

public class MoveTest {

    @Test
    public void testRockVSRock(){
        Assert.assertNull(Move.ROCK.winnsOver(Move.ROCK));
    }

    @Test
    public void testPaperVSPaper(){
        Assert.assertNull(Move.PAPER.winnsOver(Move.PAPER));
    }

    @Test
    public void testScissorsVSScissors(){
        Assert.assertNull(Move.SCISSORS.winnsOver(Move.SCISSORS));
    }

    @Test
    public void testRockVSPaper(){
        Assert.assertEquals(false, Move.ROCK.winnsOver(Move.PAPER));
    }

    @Test
    public void testPaperVSRock(){
        Assert.assertEquals(true, Move.PAPER.winnsOver(Move.ROCK));
    }

    @Test
    public void testScissorsVSRock(){
        Assert.assertEquals(false, Move.SCISSORS.winnsOver(Move.ROCK));
    }

    @Test
    public void testRockVSScissors(){
        Assert.assertEquals(true, Move.ROCK.winnsOver(Move.SCISSORS));
    }

    @Test
    public void testScissorsVSPaper(){
        Assert.assertEquals(true, Move.SCISSORS.winnsOver(Move.PAPER));
    }

    @Test
    public void testPaperVSScissors(){
        Assert.assertEquals(false, Move.PAPER.winnsOver(Move.SCISSORS));
    }
}
