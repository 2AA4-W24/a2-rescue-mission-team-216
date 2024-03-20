package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONArray;
import org.json.JSONObject;

class MarineMission implements Radio {

    JSONObject decision;

    private CheckRsp checker = new CheckRsp();
    private Phase1 phase1 = new Phase1();
    private Phase2 phase2;
    private Phase3 phase3 = new Phase3();
    int c = 250;
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
        if (c > 0) {
            if (!phase1.isDone()) {
                decision = phase1.searchGrnd(drone, checker);
            } else if (phase2 == null) {
                phase2 = new Phase2();
            } else if (!phase2.isDone()) {
                decision = phase2.travIsland(drone, checker);
            } else {
                decision = phase3.scanner(drone, checker);
            }
            c--;
        } else {
            decision = drone.stop();
        }
        return decision;
    }
}
