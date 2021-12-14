package model;

import java.util.Map;

public final class Truck extends AbstractVehicle {

    // the image that will be shown when the vehicle is alive
    private static final String aliveImageFileName = "truck.gif";
    // the image that will be shown when the vehicle is dead
    private static final String deadImageFileName = "truck_dead.gif";
    // the number of updates this vehicle's death and when it should revive
    private static final int deathTime = 0;

    /**
     * @param x         vehicle's x-coordinate
     * @param y         vehicle's y-coordinate
     * @param direction vehicle's direction
     */
    public Truck(final int x, final int y, final Direction direction) {
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
        if (this.isNextTo(theNeighbors, Terrain.STREET) || this.isNextTo(theNeighbors, Terrain.LIGHT) || this.isNextTo(theNeighbors, Terrain.CROSSWALK)) {
            while (true) {
                final var direction = Direction.random();
                if (direction != this.getDirection().reverse() && (
                        theNeighbors.get(direction) == Terrain.STREET
                                || theNeighbors.get(direction) == Terrain.LIGHT
                                || theNeighbors.get(direction) == Terrain.CROSSWALK
                )) {
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
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT || (theTerrain == Terrain.CROSSWALK && theLight != Light.RED);
    }


    /**
     * check if the truck is near a certain Terrain
     *
     * @param theNeighbors The map of neighboring terrain.
     * @param theTerrain   The Terrain that developer wants to check
     * @return whether the truck is near a certain Terrain
     */
    private boolean isNextTo(final Map<Direction, Terrain> theNeighbors, final Terrain theTerrain) {
        return theNeighbors.get(this.getDirection()) == theTerrain
                || theNeighbors.get(this.getDirection().left()) == theTerrain
                || theNeighbors.get(this.getDirection().right()) == theTerrain;
    }
}
