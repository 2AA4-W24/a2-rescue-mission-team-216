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
    private Integer range = 0;
    private Integer a = 3; //for echoing in 3 dirs or flying 3 steps
    private String found = "";
    Boolean groundLocation = true;
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
        logger.info("Counter {}", counter);

        if (groundLocation) {
            switch (counter) {
                case 0, 5:
                    if (a == 3) {
                        decision = drone.echoRight();
                        a--;
                    } else if (a == 2) {
                        decision = drone.echoLeft();
                        a--;
                    } else {
                        decision = drone.echoFwd();
                        a = 3; //reset this counter (a) for next use
                        counter++; //move to next phase
                    }
                    break;

                case 1, 6:
                    if (extraInfo.has("found")) { //have to eventually change to account for ground
                        range = extraInfo.getInt("range"); //extracting range and found vars if they exist (aka if the last action was echo)
                        found = extraInfo.getString("found");
                        if (found.equals("GROUND")) { //if ground found, fly forward and then move to 10
                            decision.put("action", "fly");
                            counter = 10;
                        }
                    } //only happens at the very beginning to know how far you can go down the map/check for ground

                    if (range >= 3) { //while you are not 3 squares from the edge of the map
                        logger.info("Range: {}", range);
                        decision.put("action", "fly"); //fly towards the edge of the map
                        range--;
                        if (range == 3) {
                            counter++; // once you've reached 3 squares from edge, increment counter so you enter the next phase on the next loop
                        }
                    }
                    break;

                case 2:
                    decision = drone.turnRight();
                    counter++; //next phase
                    break;

                case 3, 8:
                    if (a >= 0) { //fly 3 steps down
                        decision.put("action", "fly");
                        a--;
                        if (a == 0) {
                            counter++;
                            a = 3; //when 3 steps away, increment counter for next phase & reset a
                        }
                    }
                    break;

                case 4:
                    //head right to complete u-turn
                    decision = drone.turnRight();
                    counter++; //next phase
                    break;

                case 7:
                    decision = drone.turnLeft();
                    counter++; //next phase
                    break;

                case 9:
                    decision = drone.turnLeft();
                    counter = 0; //next phase
                    break;

                case 10:
                    if (range >= 2) { //fly to ground
                        decision.put("action", "fly");
                        range--;
                        if (range == 2) { //when ground reached, stop & set vars accordingly for next phase
                            decision.put("action", "stop");
                            groundLocation = false;
                            groundTravel = true; //technically this phase is case 10, will change later or combine phases
                        }
                    }
                    break;
            }
        } else if (groundTravel) {
            //will refactor moving to ground once it's found to be in this phase (???)
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
        logger.info("The drone is facing {}", drone.getDirection());
        extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}