package ca.mcmaster.se2aa4.island.team216;

import org.apache.xpath.operations.Bool;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.StringReader;

public class Drone {
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

    //echoing
    public JSONObject echoFwd() {
        return radar.echoFwdR(direction);
    }

    public JSONObject echoRight() {
        return radar.echoRightR(direction);
    }

    public JSONObject echoLeft() {
        return radar.echoLeftR(direction);
    }

    public Integer extras(JSONObject extraInfo){
        if(extraInfo.has("found")){
            Integer range = extraInfo.getInt("range");
            String found = extraInfo.getString("found");
            if(found == "GROUND"){
                return range;

            }
        }
        return -1;
    }




}