package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

class Phase2 {
    private Move2Grnd traveller = new Move2Grnd();
    int range = Phase1.getRange();
    String groundDir = Phase1.getGroundDir();
    boolean done = false;
    JSONObject decision;

    public JSONObject travIsland(Drone drone, CheckRsp checker) {

        switch (groundDir) {
            case "F":
                //move2Grnd(drone);
                range--;

                if (range < 0) {
                    decision = drone.scan();
                    done = true;
                }
                else {
                    decision = drone.fly();
                }

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

        return decision;
    }

    /*private void move2Grnd(Drone drone) {
        this.decision = traveller.travel(drone, range); //???? how do we get drone into this class
    }*/

    public boolean isDone() {
        return done;
    }

}
