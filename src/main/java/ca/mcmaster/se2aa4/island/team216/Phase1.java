package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class Phase1 {

    JSONObject decision;
    boolean echoF = true;
    boolean echoL = false;
    boolean echoR = false;
    boolean fly = false;
    static Integer range;
    static String groundDir;

    public Phase1(Drone drone, CheckRsp checker) {

        JSONObject response = checker.getResp();

        if (checker.hasGrnd()) {
            range = response.getInt("range");
            /*if (groundDir.equals("L")) {
                decision = drone.turnLeft();
            } else if (groundDir.equals("R")) {
                decision = drone.turnRight();
            }
            else{
                decision = drone.fly();
                range--;
            }*/
            decision = null; //go to phase 3
        }
        else{
            if (fly) {
                decision = drone.fly();
                fly = false;
                echoL = true;
            } else if (echoR) {
                decision = drone.echoRight();
                groundDir = "R";
                echoR = false;
                fly = true;
            } else if (echoL) {
                decision = drone.echoLeft();
                groundDir = "L";
                echoL = false;
                echoR = true;
            } else {
                decision = drone.echoFwd();
                groundDir = "F";
                echoF = false;
                echoL = true;
            }
        }
    }


    public static Integer getRange(){
        return range;
    }

    public static String getGroundDir() {
        return groundDir;
    }
    //needs to extract the range at the end and pass it into phase 2

    public JSONObject getDecision() {
        return this.decision;
    }

}

//phase 1 ends w finding range to grnd and turning towards it
//phase 2 ends w reaching ground (a.k.a. range = 0)
//phase 3 ends w finding all creeks and islands

