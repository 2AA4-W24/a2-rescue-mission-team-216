package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class Turn2 implements State{

    JSONObject decision;

    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {

        if (context.turnLeft()) {
            decision = drone.turnLeft();
        } else {
            decision = drone.turnRight();
        }

        context.switchDir();
        context.changeState(new EchoF());
        return decision;
    }
}
