package model;


import java.util.Map;

public class Bicycle extends AbstractVehicle {


    public Bicycle(int x, int y, Direction direction) {
        super(x, y, direction, "Bicycle", "bicycle.gif", "bicycle_dead.gif", 35, 1);
    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return theTerrain == Terrain.STREET || theTerrain == Terrain.TRAIL || ((theTerrain == Terrain.LIGHT || theTerrain == Terrain.CROSSWALK) && theLight == Light.GREEN);
    }

    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        if (theNeighbors.get(this.getDirection()) == Terrain.TRAIL) {
            return this.getDirection();
        } else if (theNeighbors.get(this.getDirection().left()) == Terrain.TRAIL) {
            return this.getDirection().left();
        } else if (theNeighbors.get(this.getDirection().right()) == Terrain.TRAIL) {
            return this.getDirection().right();
        } else if (theNeighbors.get(this.getDirection()) == Terrain.STREET || theNeighbors.get(this.getDirection()) == Terrain.LIGHT || theNeighbors.get(this.getDirection()) == Terrain.CROSSWALK) {
            return this.getDirection();
        } else if (theNeighbors.get(this.getDirection().left()) == Terrain.STREET || theNeighbors.get(this.getDirection().left()) == Terrain.LIGHT || theNeighbors.get(this.getDirection().left()) == Terrain.CROSSWALK) {
            return this.getDirection().left();
        } else if (theNeighbors.get(this.getDirection().right()) == Terrain.STREET || theNeighbors.get(this.getDirection().right()) == Terrain.LIGHT || theNeighbors.get(this.getDirection().right()) == Terrain.CROSSWALK) {
            return this.getDirection().right();
        } else {
            return this.getDirection().reverse();
        }
    }

}
