package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;
import org.json.JSONArray;

class AddPOIs implements State{
    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {

        JSONArray creekID = checker.checkCreeks();
        JSONArray siteID = checker.checkSites();
        Double[] coords = drone.coords();

        if (!creekID.isEmpty()) {
            context.updateCreeks(creekID, coords);
        }

        if (!siteID.isEmpty()) {
            context.updateSites(siteID, coords);
        }

        context.changeState(new GridFly());

        return null;
    }
}
