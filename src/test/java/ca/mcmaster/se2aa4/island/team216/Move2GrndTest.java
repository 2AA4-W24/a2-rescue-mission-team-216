package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Move2GrndTest {
    private MarineMission context;
    private Drone drone;
    private CheckRsp checker;
    private Move2Grnd move2Grnd;
    @Before
    public void setUp() {
        drone = new Drone("EAST", 100); // Initialize the drone with direction "NORTH" and battery level 100
        checker = new CheckRsp();
        context = new MarineMission(drone);
        move2Grnd = new Move2Grnd();
    }
    @Test
    public void testMove2GrndWithRangeAbove0() {
        context.range = 5;

        JSONObject decision = move2Grnd.handle(context, drone, checker);

        assertEquals("fly", decision.getString("action"));
        assertEquals(4, (int) context.range); // Check that range decreased by 1
    }

    @Test
    public void testMove2GrndWithRange0() {
        context.range = 0; // Setting range to 0

        JSONObject decision = move2Grnd.handle(context, drone, checker);

        assertNull(decision);
        assertEquals(Scan.class, context.getState().getClass());
    }
}
