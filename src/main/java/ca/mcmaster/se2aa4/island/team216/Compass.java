package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

public class Compass {

    public static String turnLeft(String direction) {
        switch (direction) {
            case "E":
                return "N";
            case "N":
                return "W";
            case "S":
                return "E";
            case "W":
                return "N";
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);

        }
    }

    public static String turnRight(String direction) {
        switch(direction) {
            case "E":
                return "S";
            case "N":
                return "E";
            case "S":
                return "W";
            case "W":
                return "N";
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }

    }


}



    // if ground is out of range, turn left
    // check direction facing
    // if facing north, hits wall, turn right
    // if facing north when hits wall, turn right, move
