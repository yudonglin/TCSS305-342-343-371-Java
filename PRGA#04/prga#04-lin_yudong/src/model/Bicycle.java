package model;

import java.util.Map;

public final class Bicycle extends AbstractCar {

    // the image that will be shown when the vehicle is alive
    private static final String aliveImageFileName = "bicycle.gif";
    // the image that will be shown when the vehicle is dead
    private static final String deadImageFileName = "bicycle_dead.gif";
    // the number of updates this vehicle's death and when it should revive
    private static final int deathTime = 35;

    /**
     * @param x         vehicle's x-coordinate
     * @param y         vehicle's y-coordinate
     * @param direction vehicle's direction
     */
    public Bicycle(int x, int y, Direction direction) {
        super(x, y, direction, aliveImageFileName, deadImageFileName, deathTime);
    }

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        if (theNeighbors.get(this.getDirection()) == Terrain.TRAIL) {
            return this.getDirection();
        } else if (theNeighbors.get(this.getDirection().left()) == Terrain.TRAIL) {
            return this.getDirection().left();
        } else if (theNeighbors.get(this.getDirection().right()) == Terrain.TRAIL) {
            return this.getDirection().right();
        } else {
            return super.chooseDirection(theNeighbors);
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
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return theTerrain == Terrain.STREET
                || theTerrain == Terrain.TRAIL
                || ((theTerrain == Terrain.LIGHT || theTerrain == Terrain.CROSSWALK) && theLight == Light.GREEN);
    }

}
