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
    private PhotoScanner scanner;
    private JSONObject decision;
    private JSONObject parameters;


    public Drone(String initializationInfo) { //class constructor
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(initializationInfo)));
        this.init_direction = info.getString("heading");
        this.direction = info.getString("heading");
        this.batteryLevel = info.getInt("budget");
        this.radar = new Radar();
        this.scanner = new PhotoScanner();
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

    //echoing
    public JSONObject echoFwd() {
        JSONObject result = radar.echoFwd(direction);
        return result;
    }

    public JSONObject echoRight() {
        JSONObject result = radar.echoRight(direction);
        return result;
    }

    public JSONObject echoLeft() {
        JSONObject result = radar.echoLeft(direction);
        return result;
    }

    public JSONObject scan(){
        JSONObject result = scanner.scan();
        return result;
    }

    public int[] coords() {
        int[] arr = {this.x, this.y};
        return arr;
    }

//    public JSONObject uTurn(){
//        turnLeft();
//    }

}