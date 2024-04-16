package src.Model;

import java.util.ArrayList;

public class Fort {
    private char name;
    private boolean isDestroyed;
    private int undamagedCells;
    private int points;
    private int lastPointScored;

    public Fort(char name, boolean isDestroyed, int undamagedCells, int points, int lastPointScored) {
        this.name = name;
        this.isDestroyed = isDestroyed;
        this.undamagedCells = undamagedCells;
        this.points = points;
        this.lastPointScored = lastPointScored;
    }

    public char getName() {
        return name;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public int getUndamagedCells() {
        return undamagedCells;
    }

    public int getPoints() {
        return points;
    }

    public int getLastPointScored() {
        return lastPointScored;
    }

    public void setLastPointScored(int lastPointScored) {
        this.lastPointScored = lastPointScored;
    }

    public void setName(char name) {
        this.name = name;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    public void setUndamagedCells(int undamagedCells) {
        if(undamagedCells == 0){
            this.setDestroyed(true);
        }
        this.undamagedCells = undamagedCells;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
