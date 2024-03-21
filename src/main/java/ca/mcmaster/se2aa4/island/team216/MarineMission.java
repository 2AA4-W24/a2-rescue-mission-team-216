package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONArray;
import org.json.JSONObject;

//class MarineMission {
//
//    JSONObject decision;
//
//    private CheckRsp checker = new CheckRsp();
//    private Phase1 phase1 = new Phase1();
//    private Phase2 phase2;
//    private Phase3 phase3 = new Phase3();
//
//    public void receiveMsg(JSONObject message) {
//
//    }
//
//    public JSONObject takeDecision(Drone drone) {
//        if (!phase1.isDone()) {
//            decision = phase1.searchGrnd(drone, checker);
//        } else if (phase2 == null) {
//            phase2 = new Phase2();
//        } else if (!phase2.isDone()) {
//            decision = phase2.travIsland(drone, checker);
//        } else {
//            decision = phase3.scanner(drone, checker);
//        }
//        return decision;
//    }
//}
