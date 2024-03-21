package ca.mcmaster.se2aa4.island.team216;

import org.apache.xpath.operations.Bool;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.StringReader;

class Drone {
    private String direction;
    private String init_direction;
    private int x = 0;
    private int y = 0;
    private Integer batteryLevel;
    private Radar radar; // Adding a Radar instance variable
    private JSONObject decision;
    private JSONObject parameters;


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

    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    public void updateBattery(int cost) {
        this.batteryLevel -= cost;
    }
    public JSONObject turnRight() {
        this.direction = Compass.right(direction);

        if (this.batteryLevel <= 7)
            decision = stop();
        else {
            decision.put("parameters", parameters.put("direction", direction));
            decision.put("action", "heading");
        }

        return decision;
    }

    public JSONObject turnLeft() {
        this.direction = Compass.left(direction);

        if (this.batteryLevel <= 7)
            decision = stop();
        else {
            decision.put("parameters", parameters.put("direction", direction));
            decision.put("action", "heading");
        }

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

        if (this.batteryLevel <= 5)
            decision = stop();
        else {
            decision.put("action", "fly");
        }

        return decision;
    }

    public JSONObject stop(){ //
        decision.put("action", "stop");
        return decision;
    }

    //echoing
    public JSONObject echoFwd() {
        if (this.batteryLevel <= 6)
            decision = stop();
        else {
            decision = radar.echoFwd(direction);
        }

        return decision;
    }

    public JSONObject echoRight() {
        if (this.batteryLevel <= 6)
            decision = stop();
        else {
            decision = radar.echoRight(direction);
        }

        return decision;
    }

    public JSONObject echoLeft() {
        if (this.batteryLevel <= 6)
            decision = stop();
        else {
            decision = radar.echoLeft(direction);
        }

        return decision;
    }

    public JSONObject scan(){
        if (this.batteryLevel <= 7)
            decision = stop();
        else {
            decision.put("action", "scan");
        }

        return decision;
    }

    public int[] coords() {
        int[] arr = {this.x, this.y};
        return arr;
    }

}