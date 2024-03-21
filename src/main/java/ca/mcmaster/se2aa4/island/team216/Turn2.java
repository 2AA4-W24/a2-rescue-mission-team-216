package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class Turn2 implements State{

    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {
        context.changeState(new GridFly());
        return drone.turnLeft();
    }
}
