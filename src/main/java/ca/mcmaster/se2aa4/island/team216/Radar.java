
package ca.mcmaster.se2aa4.island.team216;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

class Radar {
    private JSONObject decision = new JSONObject();
    private JSONObject parameters = new JSONObject();

    public JSONObject echoFwd(String dir){
        decision.put("parameters", parameters.put("direction", dir)); //setting the parameter as the current direction
        decision.put("action", "echo"); //echoing in the current direction
        return decision;
    }

    public JSONObject echoRight(String dir){
        decision.put("parameters", parameters.put("direction", Compass.right(dir))); //setting the parameter as the right direction
        decision.put("action", "echo"); //echoing in the right direction
        return decision;
    }
    public JSONObject echoLeft(String dir){
        decision.put("parameters", parameters.put("direction", Compass.left(dir))); //setting the parameter as the left direction
        decision.put("action", "echo"); //echoing in the current direction
        return decision;
    }

}