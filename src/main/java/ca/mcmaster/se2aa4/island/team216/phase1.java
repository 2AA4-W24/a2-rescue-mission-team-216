package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

class phase1 {

    JSONObject decision;
    boolean echoF = true;
    boolean echoL = false;
    boolean echoR = false;
    boolean fly = false;
    static int range;
    String rangeDir = null;


    private JSONObject phase1(Drone drone, checkRsp checker) {

        JSONObject response = checker.getResp();

        if (checker.hasGrnd()) {
            range = response.getInt("range");
            if (rangeDir.equals("L")) {
                decision = drone.turnLeft();
            } else if (rangeDir.equals("R")) {
                decision = drone.turnRight();
            }
            else{
                decision = drone.fly();
                range--;
            }
        }
        else{
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
        }
        return decision;
    }


    public static int getRange(){
        return range;
    }

    //needs to extract the range at the end and pass it into phase 2

}

//phase 1 ends w finding range to grnd and turning towards it
//phase 2 ends w reaching ground (a.k.a. range = 0)
//phase 3 ends w finding all creeks and islands

