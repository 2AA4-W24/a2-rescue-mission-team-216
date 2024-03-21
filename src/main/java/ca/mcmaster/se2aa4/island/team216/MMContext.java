package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class MMContext {
    private State state;
    private Drone drone;
    private CheckRsp checker = new CheckRsp();
    private JSONObject decision;
    private String lastEchoed = "";
    public Integer range = -1;
    public Boolean phase3 = false;
    private Boolean left = true;

    public MMContext(Drone drone) {
        this.state = new EchoF(); // Initial state is echoing fwd
        this.drone = drone;
    }

    public void handle() {
        state.handle(this, drone, checker); // Delegate action to the current state
    }

    public void changeState(State newState) {
        this.state = newState; // Change the current state to a new state
    }

    public String getLastEchoDirection() {
        return lastEchoed;
    }

    public void setLastEchoDirection(String lastEchoed) {
        this.lastEchoed = lastEchoed;
    }

    public JSONObject takeDecision() {

        do {
            decision = state.handle(this, drone, checker);
        } while (decision == null);

        return decision;
    }

    public void transmitMsg(JSONObject response) {
        checker.receiveMsg(response);
    }

    public void switchDir() {
        left = !left;
    }

    public boolean turnLeft() {
        return left;
    }



}
