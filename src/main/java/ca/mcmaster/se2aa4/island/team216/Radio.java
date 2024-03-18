package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public interface Radio {


    void receiveMsg(JSONObject message);
    int transmitMsg(JSONObject message);

}
