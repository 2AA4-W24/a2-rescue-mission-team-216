package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

class EchoR implements State{

    @Override
    public JSONObject handle(MarineMission context, Drone drone, CheckRsp checker) {
        context.setLastEchoDirection("R");
        context.changeState(new CheckGrnd());
        return drone.echoRight();

    }
}
