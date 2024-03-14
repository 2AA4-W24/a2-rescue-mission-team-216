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
    Boolean groundLocation = false;
    Boolean groundTravel = false;
    Boolean criticalPoint = true;
    Boolean siteLocate = false;
    Boolean goHome = false;
    JSONObject extraInfo;
    JSONObject parameters = new JSONObject();
    Integer counter = 0;

    private final Radar radar = new Radar(); //instance of radar takes care of watching the drone boundary
    Drone drone;



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

        switch (counter) {
            case 0:
                String dirr = drone.getDirection();
                decision.put("parameters", parameters.put("direction", dirr)); //setting the parameter as the current direction
                decision = (decision.put("action", "echo")); //echo if you haven't done anything yet
                counter++;
                break;

            case 1, 5:
                if (extraInfo.has("found")) {
                    range = extraInfo.getInt("range");
                }
                if (range > 0) { //while you are not at the edge of the map
                    decision.put("action", "fly"); //fly towards the edge of the map
                    range--;
                } else {
                    counter++;
                }
                break;

            case 2, 4:
                String blah = drone.getDirection();
                String dir = Compass.turnRight(blah);
                decision.put("parameters", parameters.put("direction", dir));
                decision.put("action", "heading");
                counter++;
                break;

            case 3,7:
                Integer c = 2;
                if (c>0) {
                    decision.put("action", "fly");
                    c--;
                }
                else{
                    counter++;
                }
                break;

            case 6:
                String blah3 = drone.getDirection();
                String dir3 = Compass.turnLeft(blah3);
                decision.put("parameters", parameters.put("direction", dir3));
                decision.put("action", "heading");
                counter++;
                break;


            case 8:
                String blah4 = drone.getDirection();
                String dir4 = Compass.turnLeft(blah4);
                decision.put("parameters", parameters.put("direction", dir4));
                decision.put("action", "heading");
                counter=0;
                break;
        }
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
        extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);

    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}