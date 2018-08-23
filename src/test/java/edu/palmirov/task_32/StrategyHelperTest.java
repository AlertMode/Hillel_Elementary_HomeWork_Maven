package edu.palmirov.task_32;


import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class StrategyHelperTest {
    @Test
    public void testSelectStrategyGreenFalse(){
        assertFalse(StrategyHelper.selectStrategy(Complexity.GREEN).sayStop(1));
    }

    @Test
    public void testSelectStrategyGreenTrue(){
        assertTrue(StrategyHelper.selectStrategy(Complexity.GREEN).sayStop(13));
    }

    @Test
    public void testSelectStrategyAdvanceTest(){
        assertFalse(StrategyHelper.selectStrategy(Complexity.ADVANCE).sayStop(1));
    }

    @Test
    public void testSelectStrategyAdvanceTrue(){
        assertTrue(StrategyHelper.selectStrategy(Complexity.ADVANCE).sayStop(15));
    }

    @Test
    public void testSelectStrategyExpertFalse(){
        assertFalse(StrategyHelper.selectStrategy(Complexity.EXPERT).sayStop(1));
    }

    @Test
    public void testSelectStrategyExpertTrue(){
        assertTrue(StrategyHelper.selectStrategy(Complexity.EXPERT).sayStop(17));
    }
}