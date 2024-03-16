
package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

class Compass {

    public static String left(String direction) {
        switch (direction) {
            case "E":
                return "N";
            case "N":
                return "W";
            case "S":
                return "E";
            case "W":
                return "S";
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);

        }
    }

    public static String right(String direction) {
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