package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

class StillIsland implements State{
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

                ClosestCreek locate = new ClosestCreek();

                HashMap<Object, Double[]> creeks = context.getCreekLocation();
                HashMap<JSONArray, Double[]> sites = context.getSiteLocation();

                HashMap<Object, Double> distance = locate.calculateDistance(creeks, sites);
                Object closestCreek = locate.rescueCreek(distance); //what now?
                context.rescueCreek(closestCreek);

                decision = drone.stop();

            }
        }
        return decision;
    }
}
