package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

class Scan implements State{
    @Override
    public JSONObject handle(MarineMission context, Drone drone, CheckRsp checker) {

        context.changeState(new AddPOIs());
        return drone.scan();

    }

}
