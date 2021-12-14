package model;

import java.util.Map;

public final class Atv extends AbstractVehicle {

    // the image that will be shown when the vehicle is alive
    private static final String aliveImageFileName = "atv.gif";
    // the image that will be shown when the vehicle is dead
    private static final String deadImageFileName = "atv_dead.gif";
    // the number of updates this vehicle's death and when it should revive
    private static final int deathTime = 25;

    /**
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
        while (true) {
            final var direction = Direction.random();
            if (direction != this.getDirection().reverse() && theNeighbors.get(direction) != Terrain.WALL) {
                return direction;
            }
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
        return theTerrain != Terrain.WALL;
    }

}