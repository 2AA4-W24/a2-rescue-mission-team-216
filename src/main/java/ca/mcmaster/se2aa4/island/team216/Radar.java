package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

package ca.mcmaster.se2aa4.island.team216;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Radar{

// get coordinate and direction, check in front, right and left
}
public class Direction {
    private int direction;
    private static final int NORTH =0;
    private static final int EAST =1;
    private static final int SOUTH =2;
    private static final int WEST =3;
    public static final int VALID = 1;
    public static final int INVALID = 2;




public class Explorer implements IExplorerRaid {
    private JSONObject info;
    private final Logger logger = LogManager.getLogger();


    @Override
    public void initialize(String s) {
        info = new JSONObject(new JSONTokener(new StringReader(s)));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    public static void checkDirection(String s){
        if(dire)


    }
}
public class Radar {
// sends an echo signal. if the signal answers OUT_OF_RANge - indicates number of cells away there is no land in this direction
    // if it bounces back, answers with GROUND and indicates the number of  cells away

    //create detect method
    //

    JSONObject decision = new JSONObject();

        if(.equals("GROUND")){
        decision.put("action", "stop"); // we stop the exploration immediately
    }


}
// get direction facing from Drone class
//

    // to do this : create method to check the direction