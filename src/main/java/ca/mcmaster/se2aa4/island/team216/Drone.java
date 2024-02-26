package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.StringReader;

public class Drone {
    private String direction;
    private Integer batteryLevel;

    public Drone(String initializationInfo) {
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(initializationInfo)));
        this.direction = info.getString("heading");
        this.batteryLevel = info.getInt("budget");
    }

    // Getters for direction and batteryLevel
    public String getDirection() {
        return direction;
    }

    public Integer getBatteryLevel() {
        return batteryLevel;
    }



}