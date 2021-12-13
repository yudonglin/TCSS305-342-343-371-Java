/*
 * TCSS 305 - Road Rage
 */

package model;

import java.util.Map;

public final class Human extends AbstractVehicle {

    // the image that will be shown when the vehicle is alive
    private static final String aliveImageFileName = "human.gif";
    // the image that will be shown when the vehicle is dead
    private static final String deadImageFileName = "human_dead.gif";
    // the number of updates this vehicle's death and when it should revive
    private static final int deathTime = 45;

    /**
     * @param x         vehicle's x-coordinate
     * @param y         vehicle's y-coordinate
     * @param direction vehicle's direction
     */
    public Human(int x, int y, Direction direction) {
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
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        if (theNeighbors.get(this.getDirection()) == Terrain.CROSSWALK) {
            return this.getDirection();
        } else if (theNeighbors.get(this.getDirection().left()) == Terrain.CROSSWALK) {
            return this.getDirection().left();
        } else if (theNeighbors.get(this.getDirection().right()) == Terrain.CROSSWALK) {
            return this.getDirection().right();
        } else if (
                theNeighbors.get(this.getDirection()) == Terrain.GRASS
                        || theNeighbors.get(this.getDirection().left()) == Terrain.GRASS
                        || theNeighbors.get(this.getDirection().right()) == Terrain.GRASS
        ) {
            while (true) {
                var direction = Direction.random();
                if (direction != this.getDirection().reverse() && theNeighbors.get(direction) == Terrain.GRASS) {
                    return direction;
                }
            }
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
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return theTerrain == Terrain.GRASS || (theTerrain == Terrain.CROSSWALK && theLight != Light.GREEN);
    }
}