package ca.mcmaster.se2aa4.island.team216;

import static org.junit.jupiter.api.Assertions.*;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class CheckRspTest {
    private CheckRsp checkRsp;
    private JSONObject mockResponse;

    @Before
    public void setUp() {
        checkRsp = new CheckRsp();
        mockResponse = new JSONObject();
    }

    @Test
    public void testReceiveMsg() {
        JSONObject mockExtras = new JSONObject();
        mockResponse.put("extras", mockExtras);

        checkRsp.receiveMsg(mockResponse);

        assertNotNull(checkRsp.getResp());
        assertEquals(mockExtras, checkRsp.getResp());
    }

    @Test
    public void testHasGrnd() {
        JSONObject mockExtras = new JSONObject();
        mockExtras.put("found", "GROUND");
        mockResponse.put("extras", mockExtras);

        checkRsp.receiveMsg(mockResponse);

        assertTrue(checkRsp.hasGrnd());
    }

}




