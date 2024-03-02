package ca.mcmaster.se2aa4.island.team216;

import org.apache.xpath.operations.Bool;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.StringReader;

public class Drone {
    private String direction;
    private Integer batteryLevel;
    private Radar radar; // Adding a Radar instance variable


    public Drone(String initializationInfo) {
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(initializationInfo)));
        this.direction = info.getString("heading");
        this.batteryLevel = info.getInt("budget");
        this.radar = new Radar();
    }



    // Getters for direction and batteryLevel
    public String getDirection() {
        return direction;
    }

    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    //setter for current direction
    public void setDirection(String dir) {
        this.direction = dir;
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
