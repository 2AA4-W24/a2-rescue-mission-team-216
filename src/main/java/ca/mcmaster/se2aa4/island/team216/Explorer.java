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
    Boolean groundLocation = true;
    Boolean groundTravel = false;
    Boolean criticalPoint = false;
    Boolean siteLocate = false;
    Boolean goHome = false;
    JSONObject extraInfo;
    private final Radar radar = new Radar(); //instance of radar takes care of watching the drone boundary



    @Override

    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}", info.toString(2));

        // Initialize an instance of the Drone class
        Drone drone = new Drone(s);

        // Get the direction and battery level from the Drone object
        String direction = drone.getDirection();
        Integer batteryLevel = drone.getBatteryLevel();

        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() {
        Integer range=0;
        JSONObject decision = new JSONObject();
        String finalDecision = "";

        if (groundLocation){
            if(extraInfo found == "GROUND"){
                range = extraInfo range

            }
            else{
                drone.compass turn left = newDirection;
                decision.put("parameters", parameters.put("direction", newDirection));
            }

            //drone.findGround (a method within drone)

            //if current dir returns ground via radar awesome store range to be used in next phase
            //boolean groundLocate to false
            //set boolean groundTravel to true
            //decision = do nothing I guess u just located the ground and how far it is
            //if not then decision = change current dir
            //return the decision
        }

        else if (groundTravel) {
            if (range != 0){
                finalDecision = String.valueOf(decision.put("action", "fly"));
                range--;
            }
            else{
                groundTravel = false;
                criticalPoint = true;
            }
            return finalDecision;
        }
        else if (criticalPoint){

            // grid search

            //if we are still near perimeter of island
            //photoscanner checks for creeks
            //while there are still creeks to be found
            //if a creek is found, append to creek array
            //decision = action fly
            //decision = move to get closer to perimeter (direction headed will depend on conditons)

            //if all creeks are found bool creekLocate = false and siteLocate=true
        }

        else{
            //uturn
            //action fly
        }

        return "Sorry";
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