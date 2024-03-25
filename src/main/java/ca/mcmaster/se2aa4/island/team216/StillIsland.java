package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

class StillIsland implements State{
    @Override
    public JSONObject handle(MarineMission context, Drone drone, CheckRsp checker) {

        if(checker.hasGrnd()) {
            JSONObject response = checker.getResp();
            context.range = response.getInt("range");
            context.changeState(new Move2Grnd());
        }
        else{
            if (!context.secondScan) {
                context.changeState(new ReverseTurn1());

            } else {

                context.changeState(new ExtractCreek());

            }
        }
        return null;
    }
}
