public abstract class Player {

    protected char symbol;

    Player(char symbol) {
        this.symbol = symbol;
    };

    public abstract Cell makeMove(Cell[][] board);

}

