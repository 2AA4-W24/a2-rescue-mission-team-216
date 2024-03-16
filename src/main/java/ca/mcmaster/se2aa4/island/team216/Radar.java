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





//OLD CODE FOR RADAR/TAKE DECISION WHICH WAS REMOVED


//    private void 5stepsFwd(int j, int k, JSONObject decision){
//
//        JSONObject parameters = new JSONObject();
//
//        if (j > 0) {
//            decision.put("action", "fly"); //if within range, make the drone fly
//        } else if (j == 0) {
//            decision.put("parameters", parameters.put("direction", "S")); //change the direction
//            decision.put("action", "heading");
//        } else if (j == -1) {
//            decision.put("parameters", parameters.put("direction", "W")); //change the direction
//            decision.put("action", "heading");
//        } else {
//            if (k > 0) {
//                decision.put("action", "fly");
//                k--;
//            } else decision.put("action", "stop");
//        }
//
//        logger.info("** Decision: {}", decision.toString());
//        Decision = decision.toString();
//    }
//
//    private void detect2(JSONObject decision) {
//        JSONObject parameters = new JSONObject();
//        if (c == 0) {
//            decision.put("parameters", parameters.put("direction", "E")); //change the direction
//            decision.put("action", "echo");
//            c+=1;
//        } else if (c == 1) {
//            decision.put("parameters", parameters.put("direction", "N")); //change the direction
//            decision.put("action", "echo");
//            c+=1;
//        }
//        else if (c == 2) {
//            decision.put("parameters", parameters.put("direction", "S")); //change the direction
//            decision.put("action", "echo");
//            c+=1;
//        } else {
//            decision.put("action","fly");
//            c=0;
//        }
//        Decision = decision.toString();
//    }
//}