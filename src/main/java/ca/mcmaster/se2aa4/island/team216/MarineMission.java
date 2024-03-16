
package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

class MarineMission /*implements Mission*/ {
    private JSONObject response;
    private Drone drone;
    private Integer range;
    private String rangeDir;
    //public Integer counter=0;
    //public Integer a=3;
    boolean searchGround = true;
    boolean turnGround = false;
    boolean faceGround = false;
    boolean atGround = false;
    boolean echoF = true;
    boolean echoL = false;
    boolean echoR = false;
    boolean fly = false;

    JSONObject extraInfo;
    public JSONObject decision; //do we need to create a new JSONObject?

    public JSONObject takeDecision(Drone drone){

        //phase 1 = find ground
        if (!faceGround) {
            phase1(drone);
        }
        else {
            phase2(drone);
        }
        //phase 2 = move to ground
        //phase 3 = perimeter of island
        //phase 4 = grid search for critical points
        //phase 5 = go home
        return decision;
    }

    public void checkResponse(JSONObject missionResponse) {
        this.response = missionResponse;
        extraInfo = response.getJSONObject("extras");

        if (extraInfo.has("found")) {
            range = extraInfo.getInt("range");
            String found = extraInfo.getString("found");
            if (found.equals("GROUND")) {
                searchGround = false;
                if (rangeDir.equals("F")) {
                    faceGround = true;
                }
            }
        }
    }

    private void phase1(Drone drone) { //temp drone parameter
        // Counter to keep track of the phase
        int c = 1;

        if (searchGround) {

            if (fly) {
                decision = drone.fly();
                fly = false;
                echoL = true;
            } else if (echoR) {
                decision = drone.echoRight();
                rangeDir = "R";
                echoR = false;
                fly = true;
            } else if (echoL) {
                decision = drone.echoLeft();
                rangeDir = "L";
                echoL = false;
                echoR = true;
            } else {
                decision = drone.echoFwd();
                rangeDir = "F";
                echoL = true;
            }
        }
        else if (!faceGround) {
            if (rangeDir.equals("L")) {
                decision = drone.turnLeft();
            } else {
                decision = drone.turnRight();
            }
            faceGround = true;
        }
    }

    private void phase2(Drone drone) { // going to ground

        if (range > 0){
            decision = drone.fly();
            range--;
        }
        else if (!atGround) {
            decision = drone.echoFwd(); //you have reached the island, check how far until you reach the edge of the island
            atGround = true;
        } else {
            decision = drone.stop();
        }
    }

    public JSONObject phase3() {

        return null;
    }

    public JSONObject phase4() {
        return null;
    }

    public JSONObject phase5() {

        return null;

    }
}
