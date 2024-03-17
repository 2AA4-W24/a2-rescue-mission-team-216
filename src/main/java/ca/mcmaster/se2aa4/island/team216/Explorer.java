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
    JSONObject extraInfo;
    private MarineMission MM = new MarineMission();
    private Drone drone; //move this into marine mission and only reference marine mission via mission

//    private Mission mission;
//
//    public Explorer(Mission mission) {
//        this.mission = mission;
//    }

    @Override

    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}", info.toString(2));

        // Initialize an instance of the Drone class

        // Get the direction and battery level from the Drone object
        drone = new Drone(s);
        String direction = drone.getDirection();
        Integer batteryLevel = drone.getBatteryLevel();

        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() {
        JSONObject decision = new JSONObject();
        decision = MM.takeDecision(drone);
        logger.info("** Decision: {}", decision.toString());
        return decision.toString();
    }




    @Override
    public void acknowledgeResults(String s) { //takes in the string which is the decision made
        //returns nothing? interface specification won't allow return statements? I think?
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n" + response.toString(2));
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        logger.info("The drone is facing {}", drone.getDirection());
        extraInfo = response.getJSONObject("extras");
        MM.checkResponse(extraInfo);
        logger.info("Additional information received: {}", extraInfo);
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}