package ca.mcmaster.se2aa4.island.team216;

import org.apache.xpath.operations.Bool;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.StringReader;

class Drone {
    private String direction;
    private Integer batteryLevel;
    private Radar radar; // Adding a Radar instance variable
    private JSONObject decision;
    private JSONObject parameters;

    public Drone(String initializationInfo) { //class constructor
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(initializationInfo)));
        this.direction = info.getString("heading");
        this.batteryLevel = info.getInt("budget");
        this.radar = new Radar();
        this.decision = new JSONObject();
        this.parameters = new JSONObject();
    }

    // Getters for direction and batteryLevel
    public String getDirection() { //we might not need this anymore
        return direction;
    }

    public Integer getBatteryLevel() {
        return batteryLevel;
    }


    //turning left & right (heading)

    public JSONObject turnRight() {
        this.direction = Compass.right(direction);
        decision.put("parameters", parameters.put("direction", direction));
        decision.put("action", "heading");
        return decision;
    }

    public JSONObject turnLeft() {
        this.direction = Compass.left(direction);
        decision.put("parameters", parameters.put("direction", direction));
        decision.put("action", "heading");
        return decision;
    }

    public JSONObject fly(){ //
        decision.put("action", "fly");
        return decision;
    }

    //echoing
    public JSONObject echoFwd() {
        JSONObject result = radar.echoFwdR(direction);
        return result;

    }

    public JSONObject echoRight() {
        JSONObject result = radar.echoRightR(direction);
        return result;
    }

    public JSONObject echoLeft() {
        JSONObject result = radar.echoLeftR(direction);
        return result;
    }


}