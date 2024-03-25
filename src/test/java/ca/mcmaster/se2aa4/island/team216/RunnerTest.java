package ca.mcmaster.se2aa4.island.team216;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RunnerTest {
    @Test
    public void testStartingAt() {
        Runner runner = new Runner();
        String startingInfo = runner.startingAt(1, 1, "EAST");
        assertNotNull(startingInfo);
        assertEquals("Starting position set at (1, 1) facing EAST", startingInfo);
    }

}