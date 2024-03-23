package ca.mcmaster.se2aa4.island.team216;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

import java.util.HashMap;

class ClosestCreek {
    HashMap<Object, Double> distance = new HashMap<>();
    private final Logger logger = LogManager.getLogger();

    public HashMap<Object, Double> calculateDistance(HashMap<Object, Double[]> Creeks, HashMap<JSONArray, Double[]> Sites) {

        JSONArray siteID = Sites.keySet().iterator().next();

        if (siteID == null){ //no key exists in the HashMap
            return null;
        }

        Double[] siteCoords = Sites.get(siteID);

        Double xSite = siteCoords[0];
        Double ySite = siteCoords[1];
//        logger.info("Site X {}", xSite);
//        logger.info("Site Y {}", ySite);

        for (Object creekID : Creeks.keySet()) {
            Double[] creekCoords = Creeks.get(creekID);
            Double xCreek = creekCoords[0];
            Double yCreek = creekCoords[1];

            // Calculate distance using Pythagorean theorem
            Double len = Math.sqrt(Math.pow(xCreek - xSite, 2) + Math.pow(yCreek - ySite, 2));
            Double length = Math.abs(len);
//            logger.info("Creek {}", creekID);
//            logger.info("X {}", xCreek);
//            logger.info("Y {}", yCreek);
//            logger.info("len {}", len);
//            logger.info("length {}", length);

            distance.put(creekID, length);

        }

        return distance;

    }

    public Object rescueCreek(HashMap<Object, Double> distance){
        Double minDistance = Double.MAX_VALUE;
        Object closestCreek = null;

        if (distance == null){
            logger.info("No sites found, therefore the default closest creek is the first creek found");
            return null; //fix this
        }

        for (Object creekID : distance.keySet()) {
            Double dist = distance.get(creekID);
            if (dist < minDistance){
                minDistance = dist;
                closestCreek = creekID;
            }
//            logger.info("Min distance: {}", minDistance);
        }

        return closestCreek;

    }



}