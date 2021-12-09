package model;

public class Truck extends AbstractVehicle {

    private static final String imageFileName = "truck.gif";

    public Truck(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir);
    }


    public String getImageFileName() {
        return imageFileName;
    }
}
