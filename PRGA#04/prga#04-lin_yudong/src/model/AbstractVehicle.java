package model;

public abstract class AbstractVehicle implements Vehicle {
    private int x;
    private int y;
    private Direction direction;

    public AbstractVehicle(int theX, int theY, Direction theDir) {
        this.x = theX;
        this.y = theY;
        this.direction = theDir;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
