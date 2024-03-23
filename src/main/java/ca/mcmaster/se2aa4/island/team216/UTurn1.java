package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

class UTurn1 implements State{ //rename uturn1?
    JSONObject decision;
    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {

        if (context.turnLeft()) {
            decision = drone.turnLeft();
        } else {
            decision = drone.turnRight();
        }

        context.changeState(new UTurn2());
        return decision;
    }
}
