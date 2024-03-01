package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class Compass {


    private static final int N=0;
    private static final int E=1;
    private static final int S=2;
    private static final int W=3;
    JSONObject parameters = new JSONObject();

    private String turnLeft(String direction){
        switch(direction)
        {
            case "E":
                return "N";
                break;
            case "N":
                return "W";
            case "S":
                return "E";
            case "W":
                return "N";




    private String turnRight(String direction){
                switch(direction)
                {
                    case "E":
                        return "S";
                        break;
                    case "N":
                        return "E";
                    case "S":
                        return "W";
                    case "W":
                        return "N";

            }


    }

}



    // if ground is out of range, turn left
    // check direction facing
    // if facing north, hits wall, turn right
    // if facing north when hits wall, turn right, move
}
