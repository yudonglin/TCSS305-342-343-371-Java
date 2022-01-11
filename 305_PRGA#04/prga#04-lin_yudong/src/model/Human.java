/*
 * TCSS 305 - Road Rage
 */

package model;

import java.util.ArrayList;
import java.util.Map;

/**
 * A subclass of AbstractVehicle
 */
public final class Human extends AbstractVehicle {

    // the number of updates this vehicle's death and when it should revive
    private static final int deathTime = 45;

    /**
     * Constructs a new Human
     *
     * @param x         vehicle's x-coordinate
     * @param y         vehicle's y-coordinate
     * @param direction vehicle's direction
     */
    public Human(final int x, final int y, final Direction direction) {
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
        if (theNeighbors.get(this.getDirection()) == Terrain.CROSSWALK) {
            return this.getDirection();
        } else if (theNeighbors.get(this.getDirection().left()) == Terrain.CROSSWALK) {
            return this.getDirection().left();
        } else if (theNeighbors.get(this.getDirection().right()) == Terrain.CROSSWALK) {
            return this.getDirection().right();
        } else {
            final var directions = new ArrayList<Direction>();
            if (theNeighbors.get(this.getDirection()) == Terrain.GRASS) {
                directions.add(this.getDirection());
            }
            if (theNeighbors.get(this.getDirection().left()) == Terrain.GRASS) {
                directions.add(this.getDirection().left());
            }
            if (theNeighbors.get(this.getDirection().right()) == Terrain.GRASS) {
                directions.add(this.getDirection().right());
            }
            return !directions.isEmpty() ? directions.get(RANDOM.nextInt(directions.size())) : this.getDirection().reverse();
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
        return theTerrain == Terrain.GRASS || (theTerrain == Terrain.CROSSWALK && theLight != Light.GREEN);
    }
}