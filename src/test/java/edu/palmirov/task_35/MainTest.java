package edu.palmirov.task_35;

import static org.junit.Assert.*;
import org.junit.Test;
import static edu.palmirov.task_35.Main.*;


public class MainTest {
    @Test
    public void testConvertStreamToList() throws Exception {
        assertEquals(13, convert(getStream()).size());
    }
}