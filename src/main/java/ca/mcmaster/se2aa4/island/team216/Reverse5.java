package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class Reverse5 implements State{
    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {

        JSONObject decision;

        if (context.turnLeft()) {
            decision = drone.turnLeft();
        } else {
            decision = drone.turnRight();
        }

        context.changeState(new Scan());

        context.secondPart = true;
        context.switchDir();

        return decision;
    }
}
