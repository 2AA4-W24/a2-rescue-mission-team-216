package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;
import org.json.JSONArray;

class MarineMission {

    //mandatory
    private State state;
    private final Drone drone;

    private final CheckRsp checker = new CheckRsp();
    private final ClosestCreek cc = new ClosestCreek();

    private JSONObject decision;
    private String lastEchoed = "";
    public Integer range = -1;
    public Object rescueCreek = null;

    private Boolean turnLeft = true;
    public Boolean turnComplete = false;
    public Boolean secondScan = false;

    public MarineMission(Drone drone) {
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
        cc.updateCreeks(creekID, coords);
    }

    public void updateSites(JSONArray siteID, Double[] coords) {
        cc.updateSites(siteID, coords);
    }
    public void rescueCreek(){
        rescueCreek = cc.rescueCreek();
    }

    public Object getState() {
        return state;
    }
}
