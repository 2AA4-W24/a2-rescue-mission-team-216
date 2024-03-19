package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class Move2Grnd {

    JSONObject decision;

    public JSONObject travel(Drone drone, int range){
        range--;

        if (range == -1) {
            decision = null; //used to move into phase3
        }
        else {
            decision = drone.fly();
        }

        return decision;
    }
}
