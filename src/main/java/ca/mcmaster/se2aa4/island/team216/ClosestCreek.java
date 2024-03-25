package ca.mcmaster.se2aa4.island.team216;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

import java.util.HashMap;


class ClosestCreek {
    private HashMap<Object, Double[]> creeks = new HashMap<>();
    private HashMap<JSONArray, Double[]> sites = new HashMap<>();
    private HashMap<Object, Double> distances = new HashMap<>();
    private final Logger logger = LogManager.getLogger();

    public void updateCreeks(JSONArray creekID, Double[] coords) {
        for (Object o : creekID) {
            creeks.put(o,coords);
        }
    }

    public void updateSites(JSONArray siteID, Double[] coords) {
        sites.put(siteID, coords);
    }

    void calculateDistance() {

        JSONArray siteID = sites.keySet().iterator().next();

        Double[] siteCoords = sites.get(siteID);

        double xSite = siteCoords[0];
        double ySite = siteCoords[1];


        for (Object creekID : creeks.keySet()) {
            Double[] creekCoords = creeks.get(creekID);
            double xCreek = creekCoords[0];
            double yCreek = creekCoords[1];

            // Calculate distance using Pythagorean theorem
            double len = Math.sqrt(Math.pow(xCreek - xSite, 2) + Math.pow(yCreek - ySite, 2));
            double length = Math.abs(len);

            distances.put(creekID, length);
        }
    }

    public Object rescueCreek(){
        calculateDistance();

        double minDistance = Double.MAX_VALUE;
        Object closestCreek = null;

        for (Object creekID : distances.keySet()) {
            double dist = distances.get(creekID);
            if (dist < minDistance){
                minDistance = dist;
                closestCreek = creekID;
            }

        }

        return closestCreek;

    }

}


