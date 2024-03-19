package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONArray;
import org.json.JSONObject;

class MarineMission implements Radio {

    JSONObject decision;

    private CheckRsp checker = new CheckRsp();
//    private Integer range;
//    private String rangeDir;
//    boolean searchGround = true;
//    boolean faceGround = false;
//    boolean atGround = false;
//
//    JSONArray creeks;
//    JSONArray sites;
//    boolean offIsland = false;
//    public JSONObject decision;
//
//    boolean scan = true;
//    boolean uTurn = false;
//    int c = 1;


    @Override
    public void receiveMsg(JSONObject message) {

    }

    @Override
    public void transmitMsg(JSONObject extraInfo) {
        checker.receiveMsg(extraInfo);
    }


    //phase1(Drone drone, checkRsp checker)

    //if checker.hasGrnd()
    //

//        if (!faceGround) {
//            if (checker.hasGrnd(extraInfo)) {
//                range = extraInfo.getInt("range");
//                searchGround = false;
//                if(atGround){
//                    faceGround = true;
//                    atGround = false;
//                }
//
//            }
//            else{ //ensure this doesn't interfere w other phases
//                uTurn = true;
//            }
//        } else if (atGround) { //during phase3
//            checker.hasCriticalPts(extraInfo);
//            if (checker.hasOcean(extraInfo)) {
//                offIsland = true;
//                fly = false;
//            }
//        }

    public JSONObject takeDecision(Drone drone) {
        Phase1 phase1 = new Phase1(drone, checker);
        decision = phase1.getDecision();
        return decision;
    }
}
