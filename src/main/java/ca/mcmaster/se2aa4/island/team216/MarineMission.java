package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;

class MarineMission implements Mission {
    private JSONObject response;
    private Drone drone;
    private Integer range;

    JSONObject decision; //do we need to create a new JSONObject?



    private void takeDecision(Drone drone){

        //phase 1 = find ground
        //phase 2 = move to ground
        //phase 3 = perimeter of island
        //phase 4 = grid search for critical points
        //phase 5 = go home

    }

    public void getResponse(JSONObject missionResponse) {
        response = missionResponse;
    }





    //return echo fwd one time
    //set rangeDir variable to equal fwd
    //decision goes to AR, then take decision reenters phase1 and checks if the found == GROUND
    //if it does then it extracts the range and set the heading to be the direction where the range was extracted
    //if it doesnt then it goes on to echo left
    //decision goes to AR, then take decision reenters phase1 and checks if the found == GROUND
    //if it does then it extracts the range and set the heading to be the direction where the range was extracted
    //if it doesnt then it goes on to echo right
    //decision goes to AR, then take decision reenters phase1 and checks if the found == GROUND
    //if it does then it extracts the range and set the heading to be the direction where the range was extracted
    //if it doesnt it moves forward one pace using action fly and then repeats the process of echoing left and right (NOT FWD)
    //and checking if ground is found



    @Override
    public JSONObject phase1() {
        // Counter to keep track of the phase
        int c = 1;
        String rangeDir = null;

        // Check the current phase
        switch (c) {
            case 1:
                decision = drone.echoFwd();
                rangeDir = "F";
                c++;
                break;
            case 2:
                if (response.has("found")) {
                    String found = response.getString("found");
                    if (found.equals("GROUND")) {

                        if (rangeDir.equals("F")){
                            range = response.getInt("range") - 1;
                            decision.put("action", "fly");
                        } else if (rangeDir.equals("R")) {
                            drone.turnRight();
                        } else if (rangeDir.equals("L")) {
                            drone.turnLeft();

                        }

                        System.out.println("Ground has been located in the" + drone.getDirection() + "direction");
                        break; // Exit the switch statement
                    }

                    else{
                        c++;
                    }
                }
            case 3: // Third phase
                // Continue similar logic for other directions (left and right)
                // Here, you would echo left or right based on the previous action
                break;
            // Add more cases for subsequent phases as needed
            default:
                break;
        }
        // Return the decision
        return decision;
    }


    @Override
    public JSONObject phase2() {

        if (range != 0){
            decision.put("action", "fly");
        }
        else{
            decision = drone.echoFwd(); //you have reached the island, check how far until you reach the edge of the island
        }
        return decision;
    }

    @Override
    public JSONObject phase3() {

        return null;
    }

    @Override
    public JSONObject phase4() {
        return null;
    }

    @Override
    public JSONObject phase5() {

        return null;

    }
}
