package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

class Phase3 {
//    private Move2Grnd traveller = new Move2Grnd();
    JSONObject decision;
    boolean echoed = false;
    boolean turned = false;
    boolean fly = true;
    boolean hasOcean = false;
    boolean left = true;
    Integer range;

    public JSONObject scanner(Drone drone, CheckRsp checker) {

        JSONObject response = checker.getResp();
        if (response.has("biomes")) { //scan boolean??
            hasOcean = checker.hasOcean();
        }

        if (fly){
            if (hasOcean) {
                if (!echoed) {
                    decision = drone.echoFwd();
                    echoed = true;
                } else if (checker.hasGrnd()) {
                    if (range == null) {
                        range = response.getInt("range");
                    }
                    range--;
                    decision = drone.fly();
                    if (range < 0) {
                        echoed = false;
                        range = null;
                    }
                } else {
                    if (!turned) {
                        if (left) {
                            decision = drone.turnLeft();
                        } else {
                            decision = drone.turnRight();
                        }
                        turned = true;
                    } else {
                        if (left) {
                            decision = drone.turnLeft();
                            left = false;
                        } else {
                            decision = drone.turnRight();
                            left = true;
                        }
                        turned = false;
                        echoed = false;
                        fly = false;
                    }
                }
            } else {
                decision = drone.fly();
                fly = false;
            }
        }
        else {
            decision = drone.scan();
            fly = true;
        }

        return decision;
    }
}
