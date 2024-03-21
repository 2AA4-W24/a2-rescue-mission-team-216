package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class GridFly implements State {
    JSONObject decision;

    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {

        context.phase3 = true;

        if (checker.getResp().has("biomes")) { //last decision was a scan
            if (checker.hasOcean()) {
                context.changeState(new EchoF());
            } else {
                checker.hasCreeks();
                checker.hasSites();
                decision = drone.fly();
                context.changeState(new Scan());
            }
        }
        decision = drone.fly();
        context.changeState(new Scan());

        return decision;
    }
}

