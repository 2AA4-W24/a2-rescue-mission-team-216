package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONArray;
import org.json.JSONObject;

class MarineMission implements Radio {

    private checkRsp checker = new checkRsp();
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
    public int transmitMsg(JSONObject extraInfo) {
        checker.receiveMsg(extraInfo);
        return -1; //for the sake of interface
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
//        if (!faceGround) {
//            phase1(drone);
//        } else if (!atGround) {
//            phase2(drone);
//        } else {
//            phase3(drone);
//
//        }
//        return decision;

        return null;

    }


    public JSONObject phase4() {
        return null;
    }

    public JSONObject phase5() {
        return null;
    }
}
