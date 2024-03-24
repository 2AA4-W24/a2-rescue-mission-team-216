//package ca.mcmaster.se2aa4.island.team216;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Test;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class MMContextTest {
//    private MMContext mmContext;
//    private Drone drone;
//
//    @BeforeEach
//    void setUp() {
//        drone = new Drone(""); // Pass initialization info if needed
//        mmContext = new MMContext(drone);
//    }
//
//    @Test
//    void testChangeState() {
//        mmContext = new MMContext();
//        State newState = new EchoR();
//        mmContext.changeState(newState);
//        assertEquals(newState, mmContext.turnComplete);
//    }
//
//    @Test
//    void testTransmitMsg() {
//        JSONObject response = new JSONObject();
//        mmContext.transmitMsg(response);
//        assertEquals(response, mmContext.getChecker().getResp());
//    }
//
//    @Test
//    void testTakeDecision() {
//        // This test needs an actual implementation based on the logic in takeDecision() method
//        // You need to simulate the behavior of takeDecision() and assert the expected outcome
//        // For example:
//        // JSONObject expectedDecision = ...; // Create an expected decision based on the logic
//        // assertEquals(expectedDecision, mmContext.takeDecision());
//    }
//
//    @Test
//    void testSwitchDir() {
//        boolean initialTurnLeft = mmContext.turnLeft();
//        mmContext.switchDir();
//        boolean newTurnLeft = mmContext.turnLeft();
//        assertNotEquals(initialTurnLeft, newTurnLeft);
//    }
//
//    @Test
//    void testUpdateCreeks() {
//        JSONArray creekID = new JSONArray();
//        creekID.put("creek1");
//        creekID.put("creek2");
//        mmContext.updateCreeks(creekID);
//        assertEquals(creekID, mmContext.getCreeks());
//    }
//
//    @Test
//    void testUpdateSites() {
//        JSONArray siteID = new JSONArray();
//        siteID.put("site1");
//        siteID.put("site2");
//        mmContext.updateSites(siteID);
//        assertEquals(siteID, mmContext.getSites());
//    }
//
//    @Test
//    void testRescueCreek() {
//        Object creek = new Object();
//        mmContext.rescueCreek(creek);
//        assertEquals(creek, mmContext.getRescueCreek());
//    }
//}
//
//    }
//}