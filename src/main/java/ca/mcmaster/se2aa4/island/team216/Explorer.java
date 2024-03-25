package ca.mcmaster.se2aa4.island.team216;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private MarineMission mm;
    private Drone drone;

    @Override

    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}", info.toString(2));

        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");

        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);

        drone = new Drone(direction, batteryLevel);

        mm = new MarineMission(drone);
    }

    @Override
    public String takeDecision() {
        JSONObject decision = new JSONObject();

        if (drone.getBattery() >= 30){
            decision = mm.takeDecision();
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
        mm.transmitMsg(response);

        logger.info("Additional information received: {}", extraInfo);
        logger.info("Final Report {}", deliverFinalReport());
    }

    @Override
    public String deliverFinalReport() {
        String s = ("Closest Creek: "+mm.rescueCreek);
        return s;
    }

}