package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONArray;
import org.json.JSONObject;

class CheckRsp {

    JSONObject extraInfo = new JSONObject();

    public JSONObject getResp(){
        return extraInfo;
    }

    public boolean hasGrnd(){
        if (extraInfo.has("found")) {
            String found = extraInfo.getString("found");
            if (found.equals("GROUND")) {
                return true;
            }
        }
        return false;
    }


    public JSONArray checkCreeks(){ //change to checkCreeks
        /*if (extraInfo.has("creeks")) {
            JSONArray creekID = extraInfo.getJSONArray("creeks");

            for (Object o : creekID) {
                creeks.put(o);
            }

        }*/
        JSONArray creekID = new JSONArray();
        if (extraInfo.has("creeks")) {
            creekID = extraInfo.getJSONArray("creeks");
        }
        return (creekID);
    }

    public JSONArray checkSites(){ //change to checkSites
        /*if (extraInfo.has("sites")) {
            JSONArray siteID = extraInfo.getJSONArray("sites");
            if (!siteID.isEmpty()) {
                sites = siteID;
            }
        }*/

        JSONArray siteID = new JSONArray();
        if (extraInfo.has("sites")) {
            siteID = extraInfo.getJSONArray("sites");
        }
        return (siteID);
    }

    public boolean hasOcean() {
        boolean ocean = false;

        if (extraInfo.has("biomes")) {
            JSONArray biomes = extraInfo.getJSONArray("biomes");

            for (int i = 0; i < biomes.length(); i++) {
                String biome = biomes.getString(i);

                if (biome.equals("OCEAN")) {
                    ocean = true;
                } else {
                    ocean = false;
                    break; // No need to continue checking if a non-OCEAN biome is found
                }
            }
        }

        return ocean;
    }


    public void receiveMsg(JSONObject response) {
        extraInfo = response.getJSONObject("extras");
    }

    public void transmitMsg(JSONObject message) {
    }


}
