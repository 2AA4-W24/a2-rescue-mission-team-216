package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

class ReverseTurn1 implements State{
    @Override
    public JSONObject handle(MarineMission context, Drone drone, CheckRsp checker) {

        context.switchDir();

        JSONObject decision;

        if (context.turnLeft()) {
            decision = drone.turnLeft();
        } else {
            decision = drone.turnRight();
        }

        context.changeState(new ReverseFly());

        return decision;
    }
}
