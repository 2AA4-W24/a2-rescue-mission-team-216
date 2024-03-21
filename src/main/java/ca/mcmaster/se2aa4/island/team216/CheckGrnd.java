package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class CheckGrnd implements State{

    private Compass compass;
    private JSONObject decision;

    @Override
    public JSONObject handle(MMContext context, Drone drone, CheckRsp checker) {


        if(checker.hasGrnd()){
            JSONObject response = checker.getResp();
            context.range = response.getInt("range"); //extracting the range

            if(context.getLastEchoDirection().equals("F")){
                decision = drone.fly();
                context.range --;
            } else if (context.getLastEchoDirection().equals("L")) {
                decision = drone.turnLeft();
                context.switchDir(); //ensures the first Uturn is right not left
            }
            else {
                decision = drone.turnRight();
            }
            context.changeState(new ExtractRange());
        }

        //will return empty decisions
        else if (context.getLastEchoDirection().equals("F")){

            if(context.phase3){
                context.changeState(new Turn1());
            }
            else{
                context.changeState(new EchoL());
            }
            decision = null;

        } else if (context.getLastEchoDirection().equals("L")) {
            context.changeState(new EchoR());
            decision = null;
        }
        else {
            context.changeState(new EchoL());
            decision = drone.fly();
        }

        return decision;
    }
}

