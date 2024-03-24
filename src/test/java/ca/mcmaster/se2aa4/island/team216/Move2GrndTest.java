import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Move2GrndTest {
    private MMContext context;
    private Drone drone;
    private CheckRsp checker;
    private Move2Grnd move2Grnd;

    @Before
    public void setUp() {
        drone = new Drone("NORTH", 100); // Initialize the drone with direction "NORTH" and battery level 100
        checker = new CheckRsp();
        context = new MMContext(drone);
        move2Grnd = new Move2Grnd();
    }

    @Test
    public void testMove2GrndWithRangeAbove0() {
        context.range = 1; // Setting range above 0

        JSONObject decision = move2Grnd.handle(context, drone, checker);

        // Verify that the decision is not null and contains the expected action "fly"
        assertEquals("fly", decision.getString("action"));
        assertEquals(0, (int) context.range); // Check that range decreased by 1
    }

    @Test
    public void testMove2GrndWithRange0() {
        context.range = 0; // Setting range to 0

        JSONObject decision = move2Grnd.handle(context, drone, checker);

        // Verify that the decision is null when range is 0
        assertNull(decision);
        // Verify that the context state changed to Scan when range is 0
        assertEquals(Scan.class, context.getState().getClass());
    }
}
