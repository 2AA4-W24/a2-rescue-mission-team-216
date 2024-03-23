package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class GridFly implements State {
    JSONObject decision;

    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {

        if (checker.getResp().has("biomes")) { //last decision was a scan
            if (checker.hasOcean()) {
                context.changeState(new EchoF());

            } else {
                decision = drone.fly();
                context.changeState(new Scan());
            }
        }

        return decision;
    }
}

