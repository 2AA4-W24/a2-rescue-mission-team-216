package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class Phase2 {
    private Move2Grnd traveller = new Move2Grnd();

    int range = Phase1.getRange();

    private void Move2Grnd(Drone drone) {
        traveller.travel(drone, range);
    }
}
