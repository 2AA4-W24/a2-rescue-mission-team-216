package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONArray;
import org.json.JSONObject;

class MarineMission /*implements Mission*/ {

    private checkRsp checker = new checkRsp();
    private Integer range;
    private String rangeDir;
    boolean searchGround = true;
    boolean faceGround = false;
    boolean atGround = false;
    boolean echoF = true;
    boolean echoL = false;
    boolean echoR = false;
    boolean fly = false;
    JSONArray creeks;
    JSONArray sites;
    boolean offIsland = false;
    public JSONObject decision;

    public JSONObject takeDecision(Drone drone) {
        if (!faceGround) {
            phase1(drone);
        } else {
            phase2(drone);
        }
        return decision;
    }

    public void checkResponse(JSONObject extraInfo) {

        if (!faceGround) {
            checker.hasGrnd(extraInfo);
        } else if (atGround) { //during phase3
            checker.hasCriticalPts(extraInfo);
            checker.hasOcean(extraInfo);
        }
    }

    private void phase1(Drone drone) {
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
                echoF = false;
                echoL = true;
            }
        } else if (!faceGround) {
            if (rangeDir.equals("L")) {
                decision = drone.turnLeft();
            } else {
                decision = drone.turnRight();
            }
            faceGround = true; //begin phase2
            fly = true;
        }
    }

    private void phase2(Drone drone) {
        if (range > 0) {
            decision = drone.fly();
            range--;
        } else if (!atGround) {
            decision = drone.echoFwd(); //you have reached the island, check how far until you reach the edge of the island
            atGround = true; //begin phase3
        }
    }

    private void phase3(Drone drone) {
        if (fly) {

            if(offIsland){
                decision = drone.echoFwd();
                faceGround = false; //need to fix this later when we abstract checkResponse
            }

            decision = drone.fly();
            fly = false;



        } else if (!fly) {
            decision = drone.scan();
            fly = true;
        }



    }

    public JSONObject phase4() {
        return null;
    }

    public JSONObject phase5() {
        return null;
    }
}