package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class Phase2 {
    private Move2Grnd traveller = new Move2Grnd();
    String groundDir = Phase1.getGroundDir();
    Integer range = Phase1.getRange();
    JSONObject decision;

    public Phase2(Drone drone) {
        switch (groundDir) {
            case "F":
                move2Grnd(drone);
                break;

            case ("L"):
                decision = drone.turnLeft();
                groundDir = "F"; //locally updates groundDir for this class only
                break;

            case ("R"):
                decision = drone.turnRight();
                groundDir = "F";
                break;
        }

    }

    private void move2Grnd(Drone drone) {
        this.decision = traveller.travel(drone, range); //???? how do we get drone into this class
    }

    public JSONObject getDecision() {
        return this.decision;
    }
}
