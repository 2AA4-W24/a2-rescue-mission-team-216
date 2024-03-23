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
    private JSONObject decision;
    private String lastEchoed = "";
    public Integer range = -1;
    private Object rescueCreek = null;

    private Boolean turnLeft = true;
    public Boolean turnComplete = false;
    public Boolean secondScan = false; //temporary

    private JSONArray creeks = new JSONArray();
    private JSONArray sites = new JSONArray();
    private HashMap<Object, double[]> CreekLocation = new HashMap<>();
    private HashMap<JSONArray, double[]> SiteLocation = new HashMap<>();
    private HashMap<Object, double[]> Distance = new HashMap<>();

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

    public void updateCreeks(JSONArray creekID) {
        double[] coords = drone.coords();
        for (Object o : creekID) {
            CreekLocation.put(o,coords);
            creeks.put(o);
        }
    }

    public void updateSites(JSONArray siteID) {
        double[] coords = drone.coords();
        SiteLocation.put(siteID, coords);
        sites = siteID;
    }


    //to print in the final report
    public JSONArray getCreeks(){
        return creeks;
    }

    public JSONArray getSites(){
        return sites;
    }

    public HashMap<Object, double[]> getCreekLocation(){
        return CreekLocation;
    }
    public HashMap<JSONArray, double[]> getSiteLocation(){
        return SiteLocation;
    }

    public void rescueCreek(Object creek){
        rescueCreek = creek;
    }

}
