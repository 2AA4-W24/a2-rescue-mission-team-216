package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ClosestCreekTest {
    @Test
    void testCalculateDistanceAndRescueCreek() {
            ClosestCreek closestCreek = new ClosestCreek();
// Creek A
            JSONArray creekIDA = new JSONArray();
            creekIDA.put("A");
            Double[] creekCoordsA = {499.0063056, 1063.269031};
            closestCreek.updateCreeks(creekIDA, creekCoordsA);

            // Creek B
            JSONArray creekIDB = new JSONArray();
            creekIDB.put("B");
            Double[] creekCoordsB = {1049.147971, 1345.824575};
            closestCreek.updateCreeks(creekIDB, creekCoordsB);

            // Creek C
            JSONArray creekIDC = new JSONArray();
            creekIDC.put("C");
            Double[] creekCoordsC = {119.2087516, 983.6460253};
            closestCreek.updateCreeks(creekIDC, creekCoordsC);

            // Creek D
            JSONArray creekIDD = new JSONArray();
            creekIDD.put("D");
            Double[] creekCoordsD = {180.0803375, 436.025178};
            closestCreek.updateCreeks(creekIDD, creekCoordsD);

            JSONArray siteIDArray = new JSONArray();
            siteIDArray.put("F");

            Double[] siteCoords = {1010.162928, 1073.091571};
            closestCreek.updateSites(siteIDArray, siteCoords);

            closestCreek.calculateDistance();

            // Now call rescueCreek() to get the ID of the closest creek to the site
            Object closestCreekID = closestCreek.rescueCreek();

            closestCreek.calculateDistance();

            // Add an assertion to verify that the closest creek ID matches the expected value
            assertEquals("B", closestCreekID); // Assuming "C" is expected to be the closest creek to the site
        }
    }
