package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

class EchoF implements State {


    @Override
    public JSONObject handle(MarineMission context, Drone drone, CheckRsp checker) {

        context.setLastEchoDirection("F");

        context.changeState(new CheckGrnd());

        if (context.turnComplete){
            context.turnComplete = false;
            context.changeState(new StillIsland());
        }

        return drone.echoFwd();
    }
}
