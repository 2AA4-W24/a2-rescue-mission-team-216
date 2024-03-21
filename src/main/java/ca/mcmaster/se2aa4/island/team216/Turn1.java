package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class Turn1 implements State{
    JSONObject decision;
    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {
        decision = drone.turnLeft();
        context.changeState(new Turn2());
        return decision;
    }
}
