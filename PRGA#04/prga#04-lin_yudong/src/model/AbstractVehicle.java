package model;

public abstract class AbstractVehicle implements Vehicle {

    private final String aliveImageFileName;
    private final String deadImageFileName;
    private final int deathTime;
    private final String name;
    private final int init_x;
    private final int init_y;
    private final Direction init_direction;
    private final int weight;
    private int x;
    private int y;
    private Direction direction;
    private int reviveTime = 0;

    public AbstractVehicle(int x, int y, Direction direction, String name, String aliveImageFileName, String deadImageFileName, int deathTime, int weight) {
        this.x = x;
        this.init_x = x;
        this.y = y;
        this.init_y = y;
        this.direction = direction;
        this.init_direction = direction;
        this.name = name;
        this.aliveImageFileName = aliveImageFileName;
        this.deadImageFileName = deadImageFileName;
        this.deathTime = deathTime;
        this.weight = weight;
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

    @Override
    public int getDeathTime() {
        return deathTime;
    }

    @Override
    public String getImageFileName() {
        return this.isAlive() ? aliveImageFileName : deadImageFileName;
    }

    @Override
    public boolean isAlive() {
        return reviveTime == 0;
    }

    @Override
    public void poke() {
        if (reviveTime > 0) {
            reviveTime--;
            if (reviveTime == 0) {
                this.direction = Direction.random();
            }
        }
    }

    @Override
    public void reset() {
        this.x = this.init_x;
        this.y = this.init_y;
        this.direction = this.init_direction;
        this.reviveTime = 0;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public void collide(Vehicle theOther) {
        if (this.weight < theOther.getWeight()) {
            this.reviveTime = this.deathTime;
        }
    }
}
