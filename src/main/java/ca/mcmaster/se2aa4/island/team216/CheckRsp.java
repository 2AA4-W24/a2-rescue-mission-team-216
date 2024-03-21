package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONArray;
import org.json.JSONObject;

class CheckRsp {

    JSONArray creeks = new JSONArray();
    JSONArray sites = new JSONArray();

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


    public boolean hasCreeks(){
        if (extraInfo.has("creeks")) {
            JSONArray creekID = extraInfo.getJSONArray("creeks");
            if (!creekID.isEmpty()) {
                creeks.put(creekID);
                return true;
            }
        }
        return false;
    }

    public boolean hasSites(){
        if (extraInfo.has("sites")) {
            JSONArray siteID = extraInfo.getJSONArray("sites");
            if (!siteID.isEmpty()) {
                sites.put(siteID);
                return true;
            }
        }
        return false;
    }


//} else if (extraInfo.has("sites")) {
//        JSONArray siteID = extraInfo.getJSONArray("sites");
//        if (siteID.length() != 0) {
//        sites.put(siteID);
//        }
//        }

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
