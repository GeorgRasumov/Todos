package com.georg.todos;

public class ToDo {

    public static enum Location {
            TODAY,TOMORROW,TOTAL,DONE;
    }

    public ToDo(){

    }

    private int position;
    private boolean done = false;
    private Location location;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
