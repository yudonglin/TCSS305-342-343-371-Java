package model;

import java.util.Map;

/**
 * A subclass of AbstractCar
 */
public final class Bicycle extends AbstractCar {

    // the number of updates this vehicle's death and when it should revive
    private static final int deathTime = 35;

    /**
     * Constructs a new Bicycle
     *
     * @param x         vehicle's x-coordinate
     * @param y         vehicle's y-coordinate
     * @param direction vehicle's direction
     */
    public Bicycle(final int x, final int y, final Direction direction) {
        super(x, y, direction, deathTime);
    }

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
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
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain == Terrain.STREET
                || theTerrain == Terrain.TRAIL
                || ((theTerrain == Terrain.LIGHT || theTerrain == Terrain.CROSSWALK) && theLight == Light.GREEN);
    }

}
