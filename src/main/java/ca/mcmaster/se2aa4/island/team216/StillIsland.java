package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class StillIsland implements State{
    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {
        JSONObject decision = null;

        if(checker.hasGrnd()) {
            JSONObject response = checker.getResp();
            context.range = response.getInt("range");
            context.changeState(new Move2Grnd());
        }
        else{
            if (!context.secondScan) {
                context.changeState(new ReverseTurn1());
            } else {
                decision = drone.stop();
                //go to a final state
                //this shouldnt be part of this state anyway
            }
        }
        return decision;
    }
}
