package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class EchoR implements State{

    JSONObject decision;
    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {
        decision = drone.echoRight();
        context.setLastEchoDirection("R");

        context.changeState(new CheckGrnd());
        return decision;
    }
}