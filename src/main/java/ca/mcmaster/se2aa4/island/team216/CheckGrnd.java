package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

class CheckGrnd implements State{

    private JSONObject decision;

    @Override
    public JSONObject handle(MarineMission context, Drone drone, CheckRsp checker) {

        if (checker.hasGrnd()) {
            context.changeState(new FaceGround());
        } else if (context.getLastEchoDirection().equals("F")) { //implies you are in the middle of grid search

            context.changeState(new UTurn1());
            decision = null;

        } else if (context.getLastEchoDirection().equals("L")) {
            context.changeState(new EchoR());
            decision = null;
        } else {
            context.changeState(new EchoL());
            decision = drone.fly();
        }
        return decision;
    }
}