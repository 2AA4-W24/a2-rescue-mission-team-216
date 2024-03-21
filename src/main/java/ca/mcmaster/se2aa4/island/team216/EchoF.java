package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class EchoF implements State {


    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {

        JSONObject decision = drone.echoFwd();
        context.setLastEchoDirection("F");

        context.changeState(new CheckGrnd());
        return decision;
    }
}
