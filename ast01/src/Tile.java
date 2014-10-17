public class Tile {
    private int value, position, manhattan;

    public Tile(int value, int position) {
        this.value = value;
        this.position = position;
        this.manhattan = calculateManhattanDistance();
    }

    public int getValue() {
        return value;
    }

    public int getManhattan() {
        return manhattan;
    }

    public void setPosition(int position) {
        this.position = position;
        this.manhattan = calculateManhattanDistance();
    }


    private int calculateManhattanDistance() {
        int result;

        int valRow = this.value / 3;
        int valCol = this.value % 3;
        int posRow = this.position / 3;
        int posCol = this.position % 3;

        result = Math.abs(valRow - posRow) + Math.abs(valCol - posCol);

        return result;
    }


    public boolean isInPosition() {
        return (value == position);
    }

    public boolean isNotInPosition() {
        return (value != position);
    }


    @Override
    public boolean equals(Object obj) {
        return (obj.getClass() == this.getClass()
                && this.value == ((Tile) obj).getValue());
    }

    @Override
    public String toString() {
        return "" + this.value;
    }
}
