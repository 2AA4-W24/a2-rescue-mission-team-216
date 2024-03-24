package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class ExtractCreek implements State{
    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {

        ClosestCreek locate = new ClosestCreek();

        HashMap<Object, Double[]> creeks = context.getCreekLocation();
        HashMap<JSONArray, Double[]> sites = context.getSiteLocation();

        HashMap<Object, Double> distance = locate.calculateDistance(creeks, sites);
        Object closestCreek = locate.rescueCreek(distance);
        context.rescueCreek(closestCreek);

        return drone.stop();

    }
}
