package ca.mcmaster.se2aa4.island.team216;

import org.apache.xpath.operations.Bool;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.StringReader;

class Drone {
    private String direction;
    private final String init_direction;
    private double x = 0;
    private double y = 0;
    private Integer batteryLevel;
    private final Radar radar; // Adding a Radar instance variable
    private JSONObject decision;
    private final JSONObject parameters;


    public Drone(String initializationInfo) { //class constructor
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(initializationInfo)));
        this.init_direction = info.getString("heading");
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

    public Integer getBattery() {
        return batteryLevel;
    }

    public void updateBattery(int cost) {
        this.batteryLevel -= cost;
    }
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

    public JSONObject fly(){

        //might need to introduce an enum
        if (direction.equals(init_direction)) {
            this.x += 1;
        } else if (direction.equals(Compass.left(init_direction))) {
            this.y -= 1;
        } else if (direction.equals(Compass.right(init_direction))) {
            this.y += 1;
        } else {
            this.x -= 1;
        }

        decision.put("action", "fly");

        return decision;
    }

    public JSONObject stop(){ //
        decision.put("action", "stop");
        return decision;
    }

    //echoing methods from radar
    public JSONObject echoFwd() {
        decision = radar.echoFwd(direction);
        return decision;
    }

    public JSONObject echoRight() {
        decision = radar.echoRight(direction);
        return decision;
    }

    public JSONObject echoLeft() {
        decision = radar.echoLeft(direction);
        return decision;
    }

    public JSONObject scan(){
        decision.put("action", "scan");
        return decision;
    }

    public double[] coords() {
        double[] arr = {this.x, this.y};
        return arr;
    }

}