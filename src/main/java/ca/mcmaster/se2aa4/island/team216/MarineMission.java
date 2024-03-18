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

    boolean scan = true;
    boolean uTurn = false;
    int c = 1;



    public void checkResponse(JSONObject extraInfo) {

        if (!faceGround) {
            if (checker.hasGrnd(extraInfo)) {
                range = extraInfo.getInt("range");
                searchGround = false;
            }
            else{ //ensure this doesn't interfere w other phases
                uTurn = true;
            }
        } else if (atGround) { //during phase3
            checker.hasCriticalPts(extraInfo);
            if (checker.hasOcean(extraInfo)) {
                offIsland = true;
                fly = false;
            }
        }

    }

    public JSONObject takeDecision(Drone drone) {
        if (!faceGround) {
            phase1(drone);
        } else if (!atGround) {
            phase2(drone);
        } else {
            phase3(drone);

        }
        return decision;

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

        range--;

        if (range <= 1) {
            atGround = true;
        }
        else {
            decision = drone.fly();
        }

    }

    private void phase3(Drone drone) {

        if (scan){
            decision = drone.scan();
            fly = true;
            scan = false;
        }
        else if (fly) {
            decision = drone.fly();
            fly = false;
            scan = true;
        }

        else if (offIsland){
            if(uTurn){

                //u-turn logic :(
                if (c<3){
                    decision = drone.turnLeft();
                    c++;
                }
                else{
                    offIsland = false;
                    scan = true;
                }

            }
            else{
                decision = drone.echoFwd(); //cehck rsp is grn or oob
                faceGround = false;
            }
        }

    }

    public JSONObject phase4() {
        return null;
    }

    public JSONObject phase5() {
        return null;
    }
}