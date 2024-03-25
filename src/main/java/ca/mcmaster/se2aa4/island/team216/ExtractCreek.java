package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class ExtractCreek implements State{
    @Override
    public JSONObject handle(MarineMission context, Drone drone, CheckRsp checker) {

        context.rescueCreek();

        return drone.stop();

    }
}
