package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class Reverse implements State{
    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {
        context.switchDir(); //temp

        JSONObject decision;

        if (context.turnLeft()) {
            decision = drone.turnLeft();
        } else {
            decision = drone.turnRight();
        }

        context.changeState(new Reverse2());

        return decision;
    }
}
