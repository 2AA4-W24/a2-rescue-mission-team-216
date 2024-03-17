package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class photoScanner {

    private JSONObject decision = new JSONObject();
    public JSONObject scan(){
        decision.put("action", "scan"); //echoing in the current direction
        return decision;
    }

}
