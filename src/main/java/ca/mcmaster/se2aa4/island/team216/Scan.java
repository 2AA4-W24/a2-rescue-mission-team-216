package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class Scan implements State{
    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {

        context.changeState(new AddPOIs());
        return drone.scan();

    }

}
