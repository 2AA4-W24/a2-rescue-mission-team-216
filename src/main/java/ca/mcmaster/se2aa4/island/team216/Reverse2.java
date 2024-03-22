package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class Reverse2 implements State{
    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {

        JSONObject decision;

        /*if (context.turnLeft()) {
            decision = drone.turnLeft();
        } else {
            decision = drone.turnRight();
        }*/

        decision = drone.fly(); //temp
        context.switchDir();//temp

        context.changeState(new Reverse3());

        return decision;
    }
}
