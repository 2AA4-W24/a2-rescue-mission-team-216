package ca.mcmaster.se2aa4.island.team216;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private MMContext MarineMission;
    private Drone drone;

    @Override

    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}", info.toString(2));

        drone = new Drone(s);
        String direction = drone.getDirection();
        Integer batteryLevel = drone.getBattery();

        MarineMission = new MMContext(drone);

        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() {
        JSONObject decision = new JSONObject();

        if (drone.getBattery() >= 30){
            decision = MarineMission.takeDecision();
        }
        else{
            decision = drone.stop();
        }

        logger.info("Coordinates: {}", drone.coords());
        logger.info("** Decision: {}", decision.toString());

        return decision.toString();
    }



    @Override
    public void acknowledgeResults(String s) {

        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n" + response.toString(2));

        Integer cost = response.getInt("cost");
        drone.updateBattery(cost);
        logger.info("The cost of the action was {}", cost);

        logger.info("The current battery is {}", drone.getBattery());
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);

        logger.info("The drone is facing {}", drone.getDirection());

        JSONObject extraInfo = response.getJSONObject("extras");
        MarineMission.transmitMsg(response);

        logger.info("Additional information received: {}", extraInfo);
        logger.info("Final Report {}", deliverFinalReport());
    }

    @Override
    public String deliverFinalReport() {
        String s = (/*"Sites: "+MarineMission.getSites() + " Creeks: \n" + MarineMission.getCreeks() + */"Closest Creek: "+MarineMission.rescueCreek); //temppp
        return s;
    }

}