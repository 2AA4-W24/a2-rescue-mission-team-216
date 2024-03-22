package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class StillIsland implements State{
    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {
        JSONObject decision = null;

        if(checker.hasGrnd()) {
            JSONObject response = checker.getResp();
            context.range = response.getInt("range");
            context.changeState(new ExtractRange());
        }
        else{
            if (!context.secondPart) {
                context.changeState(new Reverse());
            } else {
                decision = drone.stop();
            }
        }
        return decision;
    }
}
