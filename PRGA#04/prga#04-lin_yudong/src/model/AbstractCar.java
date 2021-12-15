package model;

import java.util.Map;


/**
 * the parent of Bicycle, Car, and Taxi
 */
public abstract class AbstractCar extends AbstractVehicle {

    // the number of updates this vehicle's death and when it should revive
    private static final int deathTime = 15;


    /**
     * Constructs a new AbstractCar (with default 15 deathTime)
     *
     * @param x                  the x position of the vehicle
     * @param y                  the y position of the vehicle
     * @param direction          the direction of the vehicle
     * @param aliveImageFileName the name of the image file that will be shown when the vehicle is alive
     * @param deadImageFileName  the name of the image file that will be shown when the vehicle is dead
     */
    protected AbstractCar(final int x, final int y, final Direction direction, final String aliveImageFileName, final String deadImageFileName) {
        super(x, y, direction, aliveImageFileName, deadImageFileName, deathTime);
    }

    /**
     * Constructs a new AbstractCar (with custom deathTime)
     *
     * @param x                  the x position of the vehicle
     * @param y                  the y position of the vehicle
     * @param direction          the direction of the vehicle
     * @param aliveImageFileName the name of the image file that will be shown when the vehicle is alive
     * @param deadImageFileName  the name of the image file that will be shown when the vehicle is dead
     * @param deathTime          the number of updates between this vehicle's death and when it should be revived
     */
    protected AbstractCar(final int x, final int y, final Direction direction, final String aliveImageFileName, final String deadImageFileName, final int deathTime) {
        super(x, y, direction, aliveImageFileName, deadImageFileName, deathTime);
    }

    /**
     * Returns the direction of an on street vehicle would like to move, based on the given
     * map of the neighboring terrain.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        if (
                theNeighbors.get(this.getDirection()) == Terrain.STREET
                        || theNeighbors.get(this.getDirection()) == Terrain.LIGHT
                        || theNeighbors.get(this.getDirection()) == Terrain.CROSSWALK
        ) {
            return this.getDirection();
        } else if (
                theNeighbors.get(this.getDirection().left()) == Terrain.STREET
                        || theNeighbors.get(this.getDirection().left()) == Terrain.LIGHT
                        || theNeighbors.get(this.getDirection().left()) == Terrain.CROSSWALK
        ) {
            return this.getDirection().left();
        } else if (
                theNeighbors.get(this.getDirection().right()) == Terrain.STREET
                        || theNeighbors.get(this.getDirection().right()) == Terrain.LIGHT
                        || theNeighbors.get(this.getDirection().right()) == Terrain.CROSSWALK
        ) {
            return this.getDirection().right();
        } else {
            return this.getDirection().reverse();
        }
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
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain == Terrain.STREET || (theTerrain == Terrain.LIGHT && theLight != Light.RED);
    }
}
