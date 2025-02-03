import java.util.Objects;

public class TicTacToe {
    private InteractionUser tictactoeUser;
    private View view;
    public static final char EMPTY = ' ';
    //declare variables and create the instances using the new operator
    int boardSize = 3;
    private Board[][] board;
    Player firstPlayer;
    Player secondPlayer;
    Player currentPlayer;

    public TicTacToe() {
        tictactoeUser = new InteractionUser();
        view = new View();

        // Initializing TicTacToe game board
        board = new Board[boardSize][boardSize];
        for (int row = 0; row< boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                board[row][col] = new Board(row, col, EMPTY);
            }
        }
    }

    private void playTokenOnBoard(Player currentPlayer) {


        Board move = currentPlayer.makeMove(board);

        // entry control, to verify that the line and the column chosen by the current player are authorized
        if (move.row >= 0 && move.row < board.length // row bounds check
                && move.col >= 0 && move.col < board.length // col bounds check
                && board[move.row][move.col].getValue() == EMPTY) {
            //System.out.print(move.row + ", " + move.col + "\n");
            putTokenOnBoard(move); // Call the function to put the pawn

            return;
        }
        view.showErrorMessageForEntryControl();
        playTokenOnBoard(currentPlayer);

    }

    /**
     * A function : put the pawn on the game board according to the line and column chosen by the current player
     */
    public void putTokenOnBoard(Board move) {
        board[move.row][move.col].setValue(move.getValue());
    }

    // play the game
    public void play() {
        view.showBoard(board);
        setSymbolToPlayerChoice();

        /* change the player */
        currentPlayer = secondPlayer;

        do {
            playTokenOnBoard(currentPlayer);
            view.showBoard(board);

            currentPlayer = (currentPlayer == firstPlayer) ? secondPlayer : firstPlayer;

        } while (!hasWinner() && !isCaseFull());

        boolean statusGame = isOver();

    }

    public void setSymbolToPlayerChoice () {
        int firstPlayerChoice = tictactoeUser.getPlayerChoice("first");
        int secondPlayerChoice = tictactoeUser.getPlayerChoice("second");

        // choose the type of first player and set a symbol
        if (firstPlayerChoice == 1) {
            firstPlayer = new ArtificialPlayer('O');
        }else{
            firstPlayer = new HumanPlayer('0');
        }

        if (secondPlayerChoice == 2) {
            secondPlayer = new HumanPlayer('x');
        }else{
            secondPlayer = new ArtificialPlayer('X');
        }

    }

    /**
     * Check if the game board is all filled by the pawns
     */
    public boolean isCaseFull() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (!board[row][col].isOccupied()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check if there is a winner in the row, in the column and in the diagonal
     *
     * @param nbSymbol  number of winning symbols needed
     * @return the result of the check is stored in the variable isWinner
     */
    private boolean isWinner(Board[][] testBoard, int nbSymbol) {
        boolean isWinner = false;
        int row = 0;
        int nbLines = testBoard.length;
        while (!isWinner && row < nbLines) {
            // initialize a counter
            int countSameSymbol = 1;
            int col = 1;
            Board[] line = testBoard[row];
            while (col < line.length && line[col] != null && !isWinner) {
                // compare the 2 consecutive cell int the game board and check if they have the same pawn
                if (!Objects.equals(line[col].getValue(), line[col - 1].getValue())) {
                    countSameSymbol = 1;
                } else {
                    countSameSymbol++;
                }
                if (countSameSymbol == nbSymbol && line[col].isOccupied()) {
                    isWinner = true;
                }
                col++;
            }
            row++;
        }
        return isWinner;

    }

    // Check if there is a winner in the row
    private boolean isWinnerLine(int nbSymbol) {
        return isWinner(board, nbSymbol);
    }

    // Check if there is a winner in the column
    private boolean isWinnerColumn(int nbSymbol) {
        Board[][] testBoard = new Board[boardSize][boardSize];
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                testBoard[col][row] = board[row][col];
            }
        }
        return isWinner(testBoard, nbSymbol);
    }

    //Check if there is a winner in the rising diagonal
    private boolean isWinnerDiagonalUp(int nbSymbol) {
        Board[][] testBoard = new Board[2 * boardSize - 1][boardSize];
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {

                if (row + col < boardSize) testBoard[row][col] = this.board[boardSize - 1 - col][row + col];
                else testBoard[row][col] = new Board(row, col, EMPTY);
            }
        }

        for (int row = 0; row < boardSize - 1; row++) {
            for (int col = 0; col < boardSize - 1; col++) {
                if (row - col >= 0) testBoard[row + boardSize][col] = this.board[row - col][col];
                else testBoard[boardSize + row][col] = new Board(row, col, EMPTY);
            }
        }
        return isWinner(testBoard, nbSymbol);
    }

    //Check if there is a winner in the descending diagonal
    private boolean isWinnerDiagonalDown(int nbSymbol) {
        Board[][] testBoard = new Board[2 * boardSize - 1][boardSize];
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (row + col < boardSize) testBoard[row][col] = board[col][col + row];
                else testBoard[row][col] = new Board(row, col, EMPTY);
            }
        }
        for (int row = 1; row < boardSize - 1; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (row + col < boardSize) testBoard[row + boardSize][col] = board[row + col][col];
                else testBoard[row][col] = new Board(row, col, EMPTY);
            }
        }
        return isWinner(testBoard, nbSymbol);
    }

    /**
     * Condition to stop the game when there is a winner
     */
    private boolean hasWinner() {
        return isWinnerLine(3) || isWinnerColumn(3) || isWinnerDiagonalUp(3) || isWinnerDiagonalDown(3);
    }

    /**
     * A function to check if there is a winner or the game board is full.
     */
    public boolean isOver() {
        boolean isOver = false;
        if (hasWinner()) {
            isOver = true;
            view.showWinner();
        } else if (isCaseFull()) {
            isOver = true;
            view.showTieMatch();

        }
        return isOver;

    }
}