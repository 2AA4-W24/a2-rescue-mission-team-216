package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

class EchoL implements State{

    @Override
    public JSONObject handle(MarineMission context, Drone drone, CheckRsp checker) {

        context.setLastEchoDirection("L");
        context.changeState(new CheckGrnd());
        return drone.echoLeft();

    }
}
