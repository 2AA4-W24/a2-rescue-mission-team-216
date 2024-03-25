package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.Map;

class MMContext {

    //mandatory
    private State state;
    private Drone drone;

    private CheckRsp checker = new CheckRsp();
    private ClosestCreek CC = new ClosestCreek();

    private JSONObject decision;
    private String lastEchoed = "";
    public Integer range = -1;
    public Object rescueCreek = null;

    private Boolean turnLeft = true;
    public Boolean turnComplete = false;
    public Boolean secondScan = false;

    public MMContext(Drone drone) {
        this.state = new EchoL();
        this.drone = drone;
    }

    public void handle() {
        state.handle(this, drone, checker);
    }

    public void changeState(State newState) {
        this.state = newState;
    }

    public void transmitMsg(JSONObject response) {
        checker.receiveMsg(response);
    }

    public String getLastEchoDirection() {
        return lastEchoed;
    }

    public void setLastEchoDirection(String lastEchoed) {
        this.lastEchoed = lastEchoed;
    }

    public JSONObject takeDecision() {

        do {
            decision = state.handle(this, drone, checker);
        } while (decision == null);

        return decision;
    }

    public void switchDir() {
        turnLeft = !turnLeft;
    }

    public boolean turnLeft() {
        return turnLeft;
    }

    public void updateCreeks(JSONArray creekID, Double[] coords) {
        CC.updateCreeks(creekID, coords);
    }

    public void updateSites(JSONArray siteID, Double[] coords) {
        CC.updateSites(siteID, coords);
    }
    public void rescueCreek(){
        Object closestCreek = CC.rescueCreek();
        rescueCreek = closestCreek;
    }

    public Object getState() {
        return state;
    }
}
