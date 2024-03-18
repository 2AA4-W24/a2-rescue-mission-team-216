package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONArray;
import org.json.JSONObject;

class checkRsp {

    JSONArray creeks = new JSONArray();
    JSONArray sites = new JSONArray();

    public boolean hasGrnd(JSONObject extraInfo){
        if (extraInfo.has("found")) {
            String found = extraInfo.getString("found");
            if (found.equals("GROUND")) {
                return true;
            }
        }
        return false;
    }


    public void hasCriticalPts(JSONObject extraInfo){
        if (extraInfo.has("creeks")) {
            JSONArray creekID = extraInfo.getJSONArray("creeks");
            if (creekID.length() != 0) {
                creeks.put(creekID);
            }
        } else if (extraInfo.has("sites")) {
            JSONArray siteID = extraInfo.getJSONArray("sites");
            if (siteID.length() != 0) {
                sites.put(siteID);
            }
        }
    }

    public boolean hasOcean(JSONObject extraInfo) {
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

}
