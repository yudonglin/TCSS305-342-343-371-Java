package model;

import java.util.Map;

public class Atv extends AbstractVehicle {


    public Atv(int x, int y, Direction direction) {
        super(x, y, direction, "ATV", "atv.gif", "atv_dead.gif", 25, 2);
    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return theTerrain != Terrain.WALL;
    }

    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        return Direction.random();
    }

}