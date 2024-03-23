package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

class Move2Grnd implements State{
    JSONObject decision;

    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {

        if (context.range > 0){
            decision = drone.fly();
            context.range--;
        }
        else {
            context.changeState(new Scan());
            decision = null;
        }
        return decision;
    }
}
