package ca.mcmaster.se2aa4.island.team216;

import org.json.JSONObject;


//extendable as it allows for many different types of missions to be implemented
public interface Mission {

    JSONObject phase1();
    JSONObject phase2();
    JSONObject phase3();
    JSONObject phase4();
    JSONObject phase5();



}
