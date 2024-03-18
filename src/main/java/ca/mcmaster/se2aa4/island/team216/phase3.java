package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class phase3 {
    private move2Grnd traveller = new move2Grnd();
    JSONObject decision;

    private JSONObject phase3(Drone drone, checkRsp checker) {

        JSONObject response = checker.getResp();

        if (response.has("biomes")){
            decision = drone.fly();
        }
        else {
            decision = drone.scan();
        }

        

        return decision;


//        if (scan){
//            decision = drone.scan();
//            fly = true;
//            scan = false;
//        }
//        else if (fly) {
//            decision = drone.fly();
//            fly = false;
//            scan = true;
//        }
//
//        else if (offIsland){
//            if(uTurn){
//
//                //u-turn logic :(
//                if (c<3){
//                    decision = drone.turnLeft();
//                    c++;
//                }
//                else{
//                    offIsland = false;
//                    scan = true;
//                }
//
//            }
//            else{
//                decision = drone.echoFwd(); //cehck rsp is grn or oob
//                faceGround = false;
//            }
//        }

    }
}
