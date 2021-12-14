package model;

public final class Car extends AbstractCar {

    // the image that will be shown when the vehicle is alive
    private static final String aliveImageFileName = "car.gif";
    // the image that will be shown when the vehicle is dead
    private static final String deadImageFileName = "car_dead.gif";

    /**
     * @param x         vehicle's x-coordinate
     * @param y         vehicle's y-coordinate
     * @param direction vehicle's direction
     */
    public Car(int x, int y, Direction direction) {
        super(x, y, direction, aliveImageFileName, deadImageFileName);
    }

    /**
     * Returns whether this object may move onto the given type of
     * terrain, when the streetlights are the given color.
     *
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether this object may move onto the given type of
     * terrain when the streetlights are the given color.
     */
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return super.canPass(theTerrain, theLight) || (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN);
    }
}

