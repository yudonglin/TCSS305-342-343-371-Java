package model;

import java.util.ArrayList;
import java.util.Map;

/**
 * A subclass of AbstractVehicle
 */
public final class Truck extends AbstractVehicle {

    // the number of updates this vehicle's death and when it should revive
    private static final int deathTime = 0;

    /**
     * Constructs a new Truck
     *
     * @param x         vehicle's x-coordinate
     * @param y         vehicle's y-coordinate
     * @param direction vehicle's direction
     */
    public Truck(final int x, final int y, final Direction direction) {
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
        final var directions = new ArrayList<Direction>();
        if (willFaceTerrain(theNeighbors, this.getDirection(), Terrain.STREET, Terrain.LIGHT, Terrain.CROSSWALK)) {
            directions.add(this.getDirection());
        }
        if (willFaceTerrain(theNeighbors, this.getDirection().left(), Terrain.STREET, Terrain.LIGHT, Terrain.CROSSWALK)) {
            directions.add(this.getDirection().left());
        }
        if (willFaceTerrain(theNeighbors, this.getDirection().right(), Terrain.STREET, Terrain.LIGHT, Terrain.CROSSWALK)) {
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
        return theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT || (theTerrain == Terrain.CROSSWALK && theLight != Light.RED);
    }

    /**
     * check if the truck will face certain Terrain(s) if it chooses to face to a certain direction
     *
     * @param theNeighbors The map of neighboring terrain.
     * @param direction    the direction Truck may choose to go
     * @param theTerrains  The Terrain(s) that developer wants to check
     * @return whether the truck will face certain Terrain(s) if it chooses to face to a certain direction
     */
    private boolean willFaceTerrain(final Map<Direction, Terrain> theNeighbors, final Direction direction, final Terrain... theTerrains) {
        for (Terrain _terrain : theTerrains) {
            if (theNeighbors.get(direction) == _terrain) {
                return true;
            }
        }
        return false;
    }
}
