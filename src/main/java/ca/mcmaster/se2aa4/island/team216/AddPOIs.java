package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;
import org.json.JSONArray;

class AddPOIs implements State{
    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {

        JSONArray creekID = checker.checkCreeks();
        JSONArray siteID = checker.checkSites();

        if (!creekID.isEmpty()) {
            context.updateCreeks(creekID);
        }

        if (!siteID.isEmpty()) {
            context.updateSites(siteID);
        }

        context.changeState(new GridFly()); //should we rename to checksites/checkcreeks?

        return null;
    }
}
