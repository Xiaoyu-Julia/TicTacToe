public abstract class BoardGame {
    protected static final char EMPTY = ' ';
    protected int boardSize;
    protected Board[][] board;
    Player firstPlayer;
    Player secondPlayer;
    Player currentPlayer;

    BoardGame(int boardSize) {
        this.boardSize = boardSize;

        // Initializing TicTacToe game board
        this.board = new Board[boardSize][boardSize];
        for (int row = 0; row< boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                board[row][col] = new Board(row, col, EMPTY);
            }
        }
    }

    public abstract void playTokenOnBoard(Player currentPlayer);
    public abstract void putTokenOnBoard(Board move);
    public abstract void play();
    public abstract void setSymbolToPlayerChoice ();
    public abstract boolean isCaseFull();
    public abstract boolean isOver();
}
