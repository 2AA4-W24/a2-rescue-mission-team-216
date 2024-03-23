package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

class EchoL implements State{

    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {

        context.setLastEchoDirection("L");
        context.changeState(new CheckGrnd());
        return drone.echoLeft();

    }
}
