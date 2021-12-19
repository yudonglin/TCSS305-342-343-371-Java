package model;

import java.util.ArrayList;
import java.util.Map;

/**
 * A subclass of AbstractVehicle
 */
public final class Atv extends AbstractVehicle {

    // the image that will be shown when the vehicle is alive
    private static final String aliveImageFileName = "atv.gif";
    // the image that will be shown when the vehicle is dead
    private static final String deadImageFileName = "atv_dead.gif";
    // the number of updates this vehicle's death and when it should revive
    private static final int deathTime = 25;

    /**
     * Constructs a new Atv
     *
     * @param x         vehicle's x-coordinate
     * @param y         vehicle's y-coordinate
     * @param direction vehicle's direction
     */
    public Atv(final int x, final int y, final Direction direction) {
        super(x, y, direction, aliveImageFileName, deadImageFileName, deathTime);
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
        final var directions = new ArrayList<Direction>();
        if (theNeighbors.get(this.getDirection()) != Terrain.WALL) {
            directions.add(this.getDirection());
        }
        if (theNeighbors.get(this.getDirection().left()) != Terrain.WALL) {
            directions.add(this.getDirection().left());
        }
        if (theNeighbors.get(this.getDirection().right()) != Terrain.WALL) {
            directions.add(this.getDirection().right());
        }
        return !directions.isEmpty() ? directions.get(RANDOM.nextInt(directions.size())) : this.getDirection().reverse();
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
        // just in case if atv choose to reverse and there is wall behind it
        return theTerrain != Terrain.WALL;
    }

}