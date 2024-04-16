package src.Model;

public class Cell {
    private String position;
    private char value;
    private char fortName;

    public Cell(String position, char value, char fortName) {
        this.position = position;
        this.value = value;
        this.fortName = fortName;
    }

    public String getPosition() {
        return position;
    }

    public char getValue() {
        return value;
    }

    public char getFortName() {
        return fortName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public void setCellFortName(char fortName) {
        this.fortName = fortName;
    }
}
