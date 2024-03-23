package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.Map;

public class MMContext {

    //mandatory
    private State state;
    private Drone drone;
    private CheckRsp checker = new CheckRsp();
    private JSONObject decision;
    private String lastEchoed = "";
    public Integer range = -1;

    public Boolean phase3 = false; //temporary???
    private Boolean left = true; //rename
    public Boolean turnComplete = false;
    public Boolean secondPart = false; //temporary
    public JSONArray creeks = new JSONArray(); //CHANGE BACK TO PRIV
    public JSONArray sites = new JSONArray(); //CHANGE BACK TO PRIV
    public HashMap<Object, double[]> Creeks = new HashMap<>();
    public HashMap<JSONArray, double[]> Sites = new HashMap<>();
    private double[] distances;

    public MMContext(Drone drone) {
        //this.state = new EchoF(); // Initial state is echoing fwd
        this.state = new EchoL();
        this.drone = drone;
    }

    public void handle() {
        state.handle(this, drone, checker); // Delegate action to the current state
    }

    public void changeState(State newState) {
        this.state = newState; // Change the current state to a new state
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

    public void transmitMsg(JSONObject response) {
        checker.receiveMsg(response);
    }

    public void switchDir() {
        left = !left;
    }

    public boolean turnLeft() {
        return left;
    }

    public void updateCreeks(JSONArray creekID) {
        double[] coords = drone.coords();
        //add to hashmap
        for (Object o : creekID) {
            Creeks.put(o,coords);
        }
    }

    public void updateSites(JSONArray siteID) {
        double[] coords = drone.coords();
        //add to hashmap
        Sites.put(siteID, coords);
    }

}
