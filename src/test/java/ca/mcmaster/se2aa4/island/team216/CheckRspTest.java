package ca.mcmaster.se2aa4.island.team216;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONArray;
import org.json.JSONObject;

public class CheckRspTest {
    private CheckRsp checkRsp;

    @BeforeEach
    public void setUp() {
        checkRsp = new CheckRsp();
    }

    @Test
    public void testReceiveMsg() {
        JSONObject response = new JSONObject("{\"extras\": {\"data\": \"example\"}}");
        checkRsp.receiveMsg(response);
        assertEquals("example", checkRsp.getResp().getString("data"));
    }

    @Test
    public void testGetResp() {
        JSONObject extraInfo = new JSONObject("{\"data\": \"sample\"}");
        checkRsp.receiveMsg(new JSONObject().put("extras", extraInfo));
        assertEquals(extraInfo.toString(), checkRsp.getResp().toString());
    }

    @Test
    public void testHasGrnd() {
        JSONObject response = new JSONObject("{\"extras\": {\"found\": \"GROUND\"}}");
        checkRsp.receiveMsg(response);
        assertTrue(checkRsp.hasGrnd());
    }

    @Test
    public void testHasOcean() {
        JSONObject response = new JSONObject("{\"extras\": {\"biomes\": [\"OCEAN\", \"FOREST\"]}}");
        checkRsp.receiveMsg(response);
        assertTrue(checkRsp.hasOcean());
    }

    @Test
    public void testCheckCreeks() {
        JSONObject response = new JSONObject("{\"extras\": {\"creeks\": [1, 2, 3]}}");
        checkRsp.receiveMsg(response);
        JSONArray creekID = checkRsp.checkCreeks();
        assertEquals(3, creekID.length());
    }

    @Test
    public void testCheckSites() {
        JSONObject response = new JSONObject("{\"extras\": {\"sites\": [101, 102, 103]}}");
        checkRsp.receiveMsg(response);
        JSONArray siteID = checkRsp.checkSites();
        assertEquals(3, siteID.length());
    }

}
