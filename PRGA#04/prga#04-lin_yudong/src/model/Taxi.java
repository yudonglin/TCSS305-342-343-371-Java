package model;

import java.util.Map;

public final class Taxi extends AbstractVehicle {

    // the image that will be shown when the vehicle is alive
    private static final String aliveImageFileName = "taxi.gif";
    // the image that will be shown when the vehicle is dead
    private static final String deadImageFileName = "taxi_dead.gif";
    // the number of updates this vehicle's death and when it should revive
    private static final int deathTime = 15;
    // clock cycles the taxi will wait if crosswalk light is red
    private final int clockCyclesWillWait = 3;
    // the count-down of the waiting time
    private int waitCountDown = clockCyclesWillWait;

    /**
     * @param x         vehicle's x-coordinate
     * @param y         vehicle's y-coordinate
     * @param direction vehicle's direction
     */
    public Taxi(int x, int y, Direction direction) {
        super(x, y, direction, aliveImageFileName, deadImageFileName, deathTime);
    }

    /**
     * Moves this vehicle back to its original position.
     */
    public void reset() {
        super.reset();
        // and reset count-down
        waitCountDown = clockCyclesWillWait;
    }

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        return this.chooseOnStreetDirection(theNeighbors);
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
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        if (theTerrain == Terrain.CROSSWALK) {
            if (theLight != Light.RED) {
                waitCountDown = clockCyclesWillWait;
                return true;
            } else {
                if (waitCountDown == 0) {
                    waitCountDown = clockCyclesWillWait;
                    return true;
                } else {
                    waitCountDown--;
                    return false;
                }
            }
        } else {
            return theTerrain == Terrain.STREET || (theTerrain == Terrain.LIGHT && theLight != Light.RED);
        }

    }

}
