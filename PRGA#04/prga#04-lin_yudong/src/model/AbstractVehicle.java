package model;

import java.util.Random;

/**
 * the parent for all vehicles
 */
public abstract class AbstractVehicle implements Vehicle {

    // A Random that we use for generating random directions.
    protected static final Random RANDOM = new Random();
    // the number of updates between this vehicle's death and when it should be revived
    private final int deathTime;
    // the initial x position of the vehicle
    private final int init_x;
    // the initial y position of the vehicle
    private final int init_y;
    // the initial direction of the vehicle
    private final Direction init_direction;
    // the current x position of the vehicle
    private int x;
    // the current y position of the vehicle
    private int y;
    // the current direction of the vehicle
    private Direction direction;
    // the time before the vehicle become alive again
    private int reviveCountDown = 0;
    // whether the vehicle is truly alive
    private boolean isVehicleAlive = true;


    /**
     * Constructs a new AbstractVehicle
     *
     * @param x         the x position of the vehicle
     * @param y         the y position of the vehicle
     * @param direction the direction of the vehicle
     * @param deathTime the number of updates between this vehicle's death and when it should be revived
     */
    protected AbstractVehicle(final int x, final int y, final Direction direction, final int deathTime) {
        this.x = x;
        this.init_x = x;
        this.y = y;
        this.init_y = y;
        this.direction = direction;
        this.init_direction = direction;
        this.deathTime = deathTime;
    }

    /**
     * Returns this Vehicle object's x-coordinate.
     *
     * @return the x-coordinate.
     */
    @Override
    public final int getX() {
        return x;
    }

    /**
     * Sets this object's x-coordinate to the given value.
     *
     * @param theX The new x-coordinate.
     */
    @Override
    public final void setX(final int theX) {
        this.x = theX;
    }

    /**
     * Returns this Vehicle object's y-coordinate.
     *
     * @return the y-coordinate.
     */
    @Override
    public final int getY() {
        return y;
    }

    /**
     * Sets this object's y-coordinate to the given value.
     *
     * @param theY The new y-coordinate.
     */
    @Override
    public final void setY(final int theY) {
        this.y = theY;
    }

    /**
     * Returns this Vehicle object's direction.
     *
     * @return the direction.
     */
    @Override
    public final Direction getDirection() {
        return direction;
    }

    /**
     * Sets this object's facing direction to the given value.
     *
     * @param theDir The new direction.
     */
    @Override
    public final void setDirection(final Direction theDir) {
        this.direction = theDir;
    }

    /**
     * Returns the number of updates between this vehicle's death and when it
     * should be revived.
     *
     * @return the number of updates.
     */
    @Override
    public final int getDeathTime() {
        return deathTime;
    }

    /**
     * Returns the file name of the image for this Vehicle object, such as
     * "car.gif".
     *
     * @return the file name.
     */
    @Override
    public final String getImageFileName() {
        return this.isAlive() ? this + ".gif" : this + "_dead.gif";
    }

    /**
     * Returns whether this Vehicle object is alive and should move on the map.
     *
     * @return true if the object is alive, false otherwise.
     */
    @Override
    public final boolean isAlive() {
        return reviveCountDown == 0;
    }

    /**
     * Called by the UI to notify a dead vehicle that 1 movement round has
     * passed, so that it will become 1 move closer to revival.
     */
    @Override
    public final void poke() {
        if (reviveCountDown > 0) {
            reviveCountDown--;
        } else if (reviveCountDown == 0 && !this.isVehicleAlive) {
            this.direction = Direction.random();
            this.isVehicleAlive = true;
        }
    }

    /**
     * Moves this vehicle back to its original position.
     */
    @Override
    public void reset() {
        this.x = this.init_x;
        this.y = this.init_y;
        this.direction = this.init_direction;
        this.reviveCountDown = 0;
        this.isVehicleAlive = true;
    }

    /**
     * @return the name of the vehicle
     */
    @Override
    public final String toString() {
        // return this.getClass().getSimpleName().toLowerCase() + ": " + reviveCountDown + " / " + deathTime +" "+this.direction;
        return this.getClass().getSimpleName().toLowerCase();
    }

    /**
     * Called when this Vehicle collides with the specified other Vehicle.
     *
     * @param theOther The other object.
     */
    @Override
    public final void collide(final Vehicle theOther) {
        if (this.deathTime > theOther.getDeathTime() && this.isAlive() && theOther.isAlive()) {
            this.isVehicleAlive = false;
            this.reviveCountDown = this.deathTime;
        }
    }

}
