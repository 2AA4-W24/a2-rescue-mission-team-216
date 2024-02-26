package ca.mcmaster.se2aa4.island.team216;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class Radar {

    private String Decision = "";
    private final Logger logger = LogManager.getLogger();

    public String getDecision(int j, int k, JSONObject decision) {
        detection(j, k, decision);
        return Decision;
    }

    private void detection(int j, int k, JSONObject decision){

        JSONObject parameters = new JSONObject();

        if (j > 0) {
            decision.put("action", "fly"); //if within range, make the drone fly
        } else if (j == 0) {
            decision.put("parameters", parameters.put("direction", "S")); //change the direction
            decision.put("action", "heading");
        } else if (j == -1) {
            decision.put("parameters", parameters.put("direction", "W")); //change the direction
            decision.put("action", "heading");
        } else {
            if (k > 0) {
                decision.put("action", "fly");
                k--;
            } else decision.put("action", "stop");
        }

        logger.info("** Decision: {}", decision.toString());
        Decision = decision.toString();
    }

}
