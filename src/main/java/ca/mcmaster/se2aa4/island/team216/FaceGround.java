package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class FaceGround implements State{
    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {

        JSONObject decision;
        JSONObject response = checker.getResp();
        context.range = response.getInt("range");

        if(context.getLastEchoDirection().equals("F")){
            decision = drone.fly();
            context.range--;
        } else if (context.getLastEchoDirection().equals("L")) {
            decision = drone.turnLeft();
            context.switchDir(); //ensures the first Uturn is right not left
        }
        else {
            decision = drone.turnRight();
        }

        context.changeState(new Move2Grnd());
        return decision;
    }
}
