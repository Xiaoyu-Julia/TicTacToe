public abstract class Player {

    protected char symbol;

    Player(char symbol) {
        this.symbol = symbol;
    };

    public abstract Board makeMove(Board[][] board);

}

