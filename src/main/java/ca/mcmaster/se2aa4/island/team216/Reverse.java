package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class Reverse implements State{
    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {
        return drone.stop();
    }
}
