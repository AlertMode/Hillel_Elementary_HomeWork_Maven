package edu.palmirov.task_33;

import org.junit.Test;
import junit.framework.TestCase;

public class StringWorkerTest {
    @Test
    public void testAcct(){
        TestCase.assertEquals(3,
                (int)StringWorker.act(StringAlg::getNumberParts, "The Good, The Bad, The Ugly"));
    }
}