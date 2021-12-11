package model;

import java.util.Map;

public class Truck extends AbstractVehicle {


    public Truck(int x, int y, Direction direction) {
        super(x, y, direction, "Truck", "truck.gif", "truck_dead.gif", 0, 5);
    }


    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return theTerrain == Terrain.STREET || ((theTerrain == Terrain.LIGHT || theTerrain == Terrain.CROSSWALK) && (theLight == Light.GREEN || theLight == Light.YELLOW));
    }

    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        if (theNeighbors.get(this.getDirection()) == Terrain.STREET) {
            return this.getDirection();
        } else if (theNeighbors.get(this.getDirection().left()) == Terrain.STREET) {
            return this.getDirection().left();
        } else if (theNeighbors.get(this.getDirection().right()) == Terrain.STREET) {
            return this.getDirection().right();
        } else if (theNeighbors.get(this.getDirection()) == Terrain.LIGHT || theNeighbors.get(this.getDirection()) == Terrain.CROSSWALK) {
            return this.getDirection();
        } else if (theNeighbors.get(this.getDirection().left()) == Terrain.LIGHT || theNeighbors.get(this.getDirection().left()) == Terrain.CROSSWALK) {
            return this.getDirection().left();
        } else if (theNeighbors.get(this.getDirection().right()) == Terrain.LIGHT || theNeighbors.get(this.getDirection().right()) == Terrain.CROSSWALK) {
            return this.getDirection().right();
        } else {
            return this.getDirection().reverse();
        }
    }


}
