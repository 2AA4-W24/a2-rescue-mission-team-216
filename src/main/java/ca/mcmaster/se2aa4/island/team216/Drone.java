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

    public Integer extras(JSONObject extraInfo){
        if(extraInfo.has("found"){
            Integer range = extraInfo.getInt("range");
            String found = extraInfo.getString("found");
            if(found == "GROUND"){
                return range;

            }
            else{
                // update current
                return -1;
            }



        }
    }
        /*Drone.radar.echo in current direction
        goes to acknowledge results
        extras is extracted in AR
        if extras found is Ground then
        extract the range
else
        update the durrent position using the compass
        / takes range as a parameter and returns an array with

    }*/

    public String findGround( String direction){

        decision.put("action","fly");

                // uses cpm
       // if drone.radar.echo returns ground then extract the range and store it in range variable
       // else echo to the left
        // echoing until finds which direction to face




    }




}
