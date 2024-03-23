package ca.mcmaster.se2aa4.island.team216;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

import java.util.HashMap;


class ClosestCreek {
    HashMap<Object, double> distance;
    private final Logger logger = LogManager.getLogger();




    public HashMap<Object, double> calculateDistance(HashMap<Object, double[]> Creeks, HashMap<JSONArray, double[]> Sites) {

        JSONArray siteID = Sites.keySet().iterator().next();

        if (siteID == null){ //no key exists in the HashMap
            return null;
        }

        double[] siteCoords = Sites.get(siteID);

        double xSite = siteCoords[0];
        double ySite = siteCoords[1];


        for (Object creekID : Creeks.keySet()) {
            double[] creekCoords = Creeks.get(creekID);
            double xCreek = creekCoords[0];
            double yCreek = creekCoords[1];

            // Calculate distance using Pythagorean theorem
            double len = Math.sqrt(Math.pow(xCreek - xSite, 2) + Math.pow(yCreek - ySite, 2));
            double length = Math.abs(len);

            distance.put(creekID, length);

        }

        return distance;

    }

    public Object rescueCreek(HashMap<Object, double> distance){
        double minDistance = Integer.MAX_VALUE;
        Object closestCreek = null;

        if (distance == null){
            logger.info("No sites found, therefore the default closest creek is the first creek found");
            return null; //fix this
        }

        for (Object creekID : distance.keySet()) {
            double dist = distance.get(creekID);
            if (dist < minDistance){
                dist = minDistance;
                closestCreek = creekID;
            }
        }

        return closestCreek;

    }



}

//    private double calcDistance(double offsetX, double offsetY) {
//        double distance = Math.sqrt(offsetX * offsetX + offsetY * offsetY);
//        return distance;
//
//    }
//
//    private double[] calcOffset(double creekX, double creekY) {
//        double offsetX = Math.abs(creekX - siteX);
//        double offsetY = Math.abs(creekY - siteY);
//        double[] arr = {offsetX, offsetY};
//
//        return arr;
//    }
//
