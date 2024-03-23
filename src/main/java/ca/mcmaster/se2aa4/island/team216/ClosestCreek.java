package ca.mcmaster.se2aa4.island.team216;

class ClosestCreek {
    double siteX;
    double siteY;

    private double calcDistance(double offsetX, double offsetY){
        double distance = Math.sqrt(offsetX*offsetX + offsetY*offsetY);
        return distance;

    }

    private double[] calcOffset(double creekX, double creekY){
        double offsetX = Math.abs(creekX - siteX);
        double offsetY = Math.abs(creekY - siteY);
        double[] arr = {offsetX, offsetY};

        return arr;
    }
}
