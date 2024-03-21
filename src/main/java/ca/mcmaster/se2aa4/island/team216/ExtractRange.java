package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class ExtractRange implements State{
    JSONObject decision;

    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {
        if (context.range > 0){
            decision = drone.fly();
        }
        else {
            context.changeState(new GridFly());
            decision = drone.scan();
        }
        return decision;
    }
}
