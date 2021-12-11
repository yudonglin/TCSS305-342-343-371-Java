/*
 * TCSS 305 - Road Rage
 */

package model;

import java.util.Map;

public class Human extends AbstractVehicle {

    public Human(int x, int y, Direction direction) {
        super(x, y, direction, "Human", "human.gif", "human_dead.gif", 45, 0);
    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return theTerrain == Terrain.GRASS || (theTerrain == Terrain.CROSSWALK && theLight != Light.GREEN);
    }

    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        while (true) {
            var direction = Direction.random();
            if (theNeighbors.get(direction) == Terrain.GRASS || theNeighbors.get(direction) == Terrain.CROSSWALK) {
                return direction;
            }
        }
    }
}