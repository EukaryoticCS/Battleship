package Model;

public class Ship {

    String shipName;
    char shipLetter;
    int shipLength;

    public Ship(String shipName, char shipLetter, int shipLength) {
        this.shipName = shipName;
        this.shipLetter = shipLetter;
        this.shipLength = shipLength;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public char getShipLetter() {
        return shipLetter;
    }

    public void setShipLetter(char shipLetter) {
        this.shipLetter = shipLetter;
    }

    public int getShipLength() {
        return shipLength;
    }

    public void setShipLength(int shipLength) {
        this.shipLength = shipLength;
    }

}
