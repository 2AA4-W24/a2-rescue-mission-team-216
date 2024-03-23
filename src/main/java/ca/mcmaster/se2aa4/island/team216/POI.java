package ca.mcmaster.se2aa4.island.team216;



public class POI {
    double siteX;
    double siteY;
    double offsetX;
    double offsetY;


    private double calcRange(){
        double z;
        z = Math.sqrt(offsetX*offsetX + offsetY*offsetY);
        return z;

    }

    private double[] calcOffset(double creekX, double creekY){
        offsetX = Math.abs(creekX-siteX);
        offsetY = Math.abs(creekY - siteY);
    }
}
