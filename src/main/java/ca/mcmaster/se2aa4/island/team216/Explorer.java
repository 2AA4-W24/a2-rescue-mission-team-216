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

//        if (groundLocation){
//            if(extraInfo found == "GROUND"){
//                range = extraInfo range
//
//            }
//            else{
//                drone.compass turn left = newDirection;
//                decision.put("parameters", parameters.put("direction", newDirection));
//            }
//
//            //drone.findGround (a method within drone)
//
//            //if current dir returns ground via radar awesome store range to be used in next phase
//            //boolean groundLocate to false
//            //set boolean groundTravel to true
//            //decision = do nothing I guess u just located the ground and how far it is
//            //if not then decision = change current dir
//            //return the decision
//        }
//
//        else if (groundTravel) {
//            if (range != 0){
//                finalDecision = String.valueOf(decision.put("action", "fly"));
//                range--;
//            }
//            else{
//                groundTravel = false;
//                criticalPoint = true;
//            }
//            return finalDecision;
//        }
//        if (criticalPoint) {

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

            case 2:
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

            case 4:
                String blah2 = drone.getDirection();
                String dir2 = Compass.turnRight(blah2);
                decision.put("parameters", parameters.put("direction", dir2));
                decision.put("action", "heading");
                counter++;
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