package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public interface State {

    JSONObject handle(MarineMission context, Drone drone, CheckRsp checker);

}
