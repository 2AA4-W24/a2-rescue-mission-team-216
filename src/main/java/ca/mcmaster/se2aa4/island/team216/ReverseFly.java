package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

class ReverseFly implements State{
    @Override
    public JSONObject handle(MarineMission context, Drone drone, CheckRsp checker) {

        context.switchDir();

        context.changeState(new ReverseTurn2());

        return drone.fly();
    }
}
