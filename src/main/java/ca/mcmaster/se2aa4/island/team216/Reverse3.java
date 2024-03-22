package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class Reverse3 implements State{
    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {

        JSONObject decision;

        if (context.turnLeft()) {
            decision = drone.turnLeft();
        } else {
            decision = drone.turnRight();
        }

        //context.changeState(new Reverse4());
        context.changeState(new Scan());//temp
        context.secondPart = true;//temp
        context.switchDir();//temp

        return decision;
    }
}
