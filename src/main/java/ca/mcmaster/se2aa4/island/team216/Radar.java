package ca.mcmaster.se2aa4.island.team216;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class Radar {

    private String Decision = "";
    private final Logger logger = LogManager.getLogger();
    private int c = 0;

    public String getDecision(int j, int k, JSONObject decision) {
        //detection(j, k, decision);
        detect2(decision);
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

<<<<<<< HEAD
=======
    private void detect2(JSONObject decision) {
        JSONObject parameters = new JSONObject();
        if (c == 0) {
            decision.put("parameters", parameters.put("direction", "E")); //change the direction
            decision.put("action", "echo");
            c+=1;
        } else if (c == 1) {
            decision.put("parameters", parameters.put("direction", "N")); //change the direction
            decision.put("action", "echo");
            c+=1;
        }
        else if (c == 2) {
            decision.put("parameters", parameters.put("direction", "S")); //change the direction
            decision.put("action", "echo");
            c+=1;
        } else {
            decision.put("action","fly");
            c=0;
        }
        Decision = decision.toString();
    }
<<<<<<< HEAD
}
=======
>>>>>>> 010665d4e9425e3152eb93b36a3be361ac68716c
}
>>>>>>> 72ceb1a36ffbcdeda24285f507572ad135d2bc3b
