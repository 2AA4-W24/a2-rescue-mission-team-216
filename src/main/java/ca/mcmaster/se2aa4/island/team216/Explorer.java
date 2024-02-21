package ca.mcmaster.se2aa4.island.team216;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    //private JSONObject response = new JSONObject();
    private boolean i = true;
    private Integer range = 0;
    private String found = "";
    private int counter = 0;
    private int j = 5;
    private int k = 5;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() {
        JSONObject decision = new JSONObject();
        JSONObject parameters = new JSONObject();
        /*if (i) {
            decision.put("parameters", parameters.put("direction", "E"));
            decision.put("action", "echo");
        } else {
            if (range >= 1) decision.put("action", "fly");
            else decision.put("action","stop");
        }*/
        if (j > 0) {
            decision.put("action", "fly");
        } else if (j == 0) {
            decision.put("parameters", parameters.put("direction", "S"));
            decision.put("action", "heading");
        }
        else if (j == -1){
            decision.put("parameters", parameters.put("direction", "W"));
            decision.put("action", "heading");
        } else {
            if (k > 0) {
                decision.put("action","fly");
                k--;
            }
            else decision.put("action","stop");
        }
        logger.info("** Decision: {}",decision.toString());
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);

        j--;

        /*if (i) {
            range = extraInfo.getInt("range");
            found = extraInfo.getString("found");
        i = !i;*/
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
