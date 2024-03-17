package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONArray;
import org.json.JSONObject;

class checkRsp {

    JSONArray creeks;
    JSONArray sites;




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
            if (!(creekID.isEmpty())) {
                creeks.put(creekID);
            }
        } else if (extraInfo.has("sites")) {
            JSONArray siteID = extraInfo.getJSONArray("sites");
            if (!(siteID.isEmpty())) {
                sites.put(siteID);
            }
        }
    }

    public boolean hasOcean(JSONObject extraInfo){
        if (extraInfo.has("biomes")) {
            JSONArray biomes = extraInfo.getJSONArray("biomes");
            for (int i = 0; i < biomes.length(); i++) {
                String biome = biomes.getString(i);
                if (biome.equals("OCEAN")) {
                    return true;
                }
            }
        }
        return false;
    }
}
