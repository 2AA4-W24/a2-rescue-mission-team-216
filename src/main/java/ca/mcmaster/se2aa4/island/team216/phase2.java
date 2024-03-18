package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class phase2 {
    private move2Grnd traveller = new move2Grnd();

    int range = phase1.getRange();

    private void move2Grnd(Drone drone) {
        traveller.travel(drone, range);
    }
}
