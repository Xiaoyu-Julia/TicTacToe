public class Cell {
    private char value = TicTacToe.EMPTY;
    int row;
    int col;

    public Cell(int row, int col, char value) {
        this.row = row;
        this.col = col;
        this.value = value;

    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value =  value;
    }

    public boolean isOccupied() {
        return value != ' ';
    }
}
