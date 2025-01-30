import java.util.Objects;

public class TicTacToe {
    public static final char EMPTY = ' ';
    //declare variables and create the instances using the new operator
    int size = 3;
    Cell[][] board = new Cell[size][size];
    Player player1 = new HumainPlayer('O');
    Player player2 = new HumainPlayer('X');

    /**
     * TicTacToe game board
     * Initializing each cell in the constructor (or Init)
     */
    public TicTacToe() {
        for (int row = 0;  row< size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = new Cell(row, col, EMPTY);
            }
        }
    }

    /**
     * Update the board display after each player has put his pawn on the game board
     */
    /*public void display(Player currentPlayer) {
        playTokenOnBoard(currentPlayer);
        showBoard();
    }*/

    // Show the game board without pawns
    private void showBoard() {
        System.out.println("---".repeat(board.length));
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                System.out.print("| " + board[row][col].getValue());
            }
            System.out.println("|");
           // System.out.println("-".repeat(13));
            System.out.println("---".repeat(board.length));
        }
    }

    /**
     * A function to verify that the line and the column chosen by the current player are authorized :
     */
    private void playTokenOnBoard(Player currentPlayer) {
        // row and column entries

        Cell move = currentPlayer.makeMove(board);

        // entry control
        if (move.row >= 0 && move.row < board.length // row bounds check
                && move.col >= 0 && move.col < board.length // col bounds check
                && board[move.row][move.col].getValue() == EMPTY) {
            System.out.print("1/ " + move.row + ", " + move.col + "\n");
            putTokenOnBoard(move); // call the function setOwner and put the pawn on the game board according to the line and column chosen by the current player

            return;
        }

        System.out.print("There is a pawn here or you have chosen a square that is off the board. Please change your choice: line [0, 2], column [0, 2].");
        playTokenOnBoard(currentPlayer);

    }

    /**
     * A function : put the pawn on the game board according to the line and column chosen by the current player
     */
    public void putTokenOnBoard(Cell move) {
        //board[move.row][move.col].setValue(move.getValue());
        board[move.row][move.col] = move;
    }

    // play the game
    public void play() {
        showBoard();
        Player currentPlayer = player1;

        do {
            playTokenOnBoard(currentPlayer);
            showBoard();

            currentPlayer = (currentPlayer == player1) ? player2 : player1;

        } while (!hasWinner() && !isCaseFull());

        boolean startGame = isOver();
        if (startGame) {
            System.out.println("The game is over.");
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
     * @param testBoard
     * @param nbSymbol  number of winning symbols needed
     * @return the result of the check is stored in the variable isWinner
     */
   private boolean isWinner(Cell[][] testBoard, int nbSymbol) {
        boolean isWinner = false;
        int row = 0;
        int nbLines = testBoard.length;
        while (!isWinner && row < nbLines) {
            // initialize a counter
            int count = 1;
            int col = 1;
            Cell[] line = testBoard[row];
            while (col < line.length && line[col] != null && !isWinner) {
                // compare the 2 consecutive cell int the game board and check if they have the same pawn
                if (/*line[col].getValue() != EMPTY ||*/ !Objects.equals(line[col].getValue(), line[col - 1].getValue())) {
                    count = 1;
                } else {
                    count++;
                }
                if (count == nbSymbol && line[col].isOccupied()) {
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
        Cell[][] testBoard = new Cell[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                testBoard[col][row] = board[row][col];
            }
        }
        return isWinner(testBoard, nbSymbol);
    }

    //Check if there is a winner in the rising diagonal
    private boolean isWinnerDiagonalUp(int nbSymbol) {
        Cell[][] testBoard = new Cell[2 * size - 1][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
//                if (i == j) {
//                    testBoard[i][j] = board[i][j];
//                }
                if (row + col < size) testBoard[row][col] = this.board[size - 1 - col][row + col];
                //else testBoard[row][col] = new Cell(row, col, testBoard[row][col].getValue());
                else testBoard[row][col] = new Cell(row, col, EMPTY);
            }
        }
//        for(int col =1; col <size; col++){
//            for(int row =0; row < size; row++){
//                if(col-row>=0) testBoard[row][col] = board[col-row][row];
//                else testBoard[row][col] = new Cell();
//            }
//        }
        for (int row = 0; row < size - 1; row++) {
            for (int col = 0; col < size - 1; col++) {
                if (row - col >= 0) testBoard[row + size][col] = this.board[row - col][col];
                //else testBoard[size + row][col] = new Cell(row, col, testBoard[row][col].getValue());
                else testBoard[size + row][col] = new Cell(row, col, EMPTY);
            }
        }
        return isWinner(testBoard, nbSymbol);
    }

    //Check if there is a winner in the descending diagonal
    private boolean isWinnerDiagonalDown(int nbSymbol) {
        Cell[][] testBoard = new Cell[2 * size - 1][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
//                if (row == col) {
//                    testBoard[row][col] = board[row][col];
//                }
                if (row + col < size) testBoard[row][col] = board[col][col + row];
               // else testBoard[row][col] = new Cell(row, col, testBoard[row][col].getValue());
                else testBoard[row][col] = new Cell(row, col, EMPTY);
            }
        }
        for (int row = 1; row < size - 1; row++) {
            for (int col = 0; col < size; col++) {
                if (row + col < size) testBoard[row + size][col] = board[row + col][col];
                //else testBoard[row][col] = new Cell(row, col, testBoard[row][col].getValue());
                else testBoard[row][col] = new Cell(row, col, EMPTY);
            }
        }
//        for(int j =1; j < size; j++){
//            for(int i =0; i < size; i++){
//                if(i+j < size) testBoard[i][j] = board[i+j][j];
//                else testBoard[i][j] = new Cell(i, j , testBoard[i][j].value());
//            }
//        }
        return isWinner(testBoard, nbSymbol);
    }

    /**
     * Condition to stop the game when there is a winner
     */
   private boolean hasWinner() {
        return isWinnerLine(3) || isWinnerColumn(3) || isWinnerDiagonalUp(3) || isWinnerDiagonalDown(3);
    }

    /*public boolean isWinner() {
        boolean hasWinner = false;
        int nbLines = board.length;
        int nbSymbols = 3; // The number of symbols is needed in a row to win the game.

        for (int i = 0; i < nbLines; i++) {
            Cell[] line = board[i]; // Get the current row

            int count = 1; // Count from the first cell
            for (int j = 1; j < line.length; j++) {
                if (line[j].isOccupied() && Objects.equals(line[j].getRepCell(), line[j - 1].getRepCell())) {
                    count++; // Increment count if the current cell matches the previous one
                } else {

                    count = 1;
                }

                if (count == nbSymbols) {
                    hasWinner = true; // We found a winner
                    break; // No need to check further in this row
                }
            }

            if (hasWinner) {
                break; // Exit the function if a winner has been found
            }
        }

        return hasWinner;
    }*/

//            for (int j = 0; j < board.length; j++)
//                if ((Objects.equals(board[0][j].getRepCell(), player1.getRepPlayer())) && (Objects.equals(board[1][j].getRepCell(), player1.getRepPlayer())) && Objects.equals(board[2][j].getRepCell(), player1.getRepPlayer())){
//                    return true;
//                }else if ((Objects.equals(board[0][j].getRepCell(), player2.getRepPlayer())) && (Objects.equals(board[1][j].getRepCell(), player2.getRepPlayer())) && Objects.equals(board[2][j].getRepCell(), player2.getRepPlayer())){
//                    return true;
//                }
//        }
//        return false;
//    }

   /* public boolean checkColumn() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++)
                if ((Objects.equals(board[i][0].getRepCell(), player1.getRepPlayer())) && (Objects.equals(board[i][1].getRepCell(), player1.getRepPlayer())) && Objects.equals(board[i][2].getRepCell(), player1.getRepPlayer())){
                    return true;
                }else if ((Objects.equals(board[i][0].getRepCell(), player2.getRepPlayer())) && (Objects.equals(board[i][1].getRepCell(), player2.getRepPlayer())) && Objects.equals(board[i][2].getRepCell(), player2.getRepPlayer())){
                    return true;
                }
        }
        return false;
    }

    public boolean checkDiagonalDown(){
        if(Objects.equals(board[0][0].getRepCell(), player1.getRepPlayer()) && Objects.equals(board[1][1].getRepCell(), player1.getRepPlayer()) && Objects.equals(board[2][2].getRepCell(), player1.getRepPlayer())) {
           return true;
        } else if (Objects.equals(board[0][0].getRepCell(), player2.getRepPlayer()) && Objects.equals(board[1][1].getRepCell(), player2.getRepPlayer()) && Objects.equals(board[2][2].getRepCell(), player2.getRepPlayer())) {
            return true;
        }
        return false;

    }

    public boolean checkDiagonalUp(){
        if(Objects.equals(board[0][2].getRepCell(), player1.getRepPlayer()) && Objects.equals(board[1][1].getRepCell(), player1.getRepPlayer()) && Objects.equals(board[2][0].getRepCell(), player1.getRepPlayer())) {
            return true;
        } else if (Objects.equals(board[0][2].getRepCell(), player2.getRepPlayer()) && Objects.equals(board[1][1].getRepCell(), player2.getRepPlayer()) && Objects.equals(board[2][0].getRepCell(), player2.getRepPlayer())) {
            return true;
        }
        return false;
    }*/

    /**
     * Condition to stop the game when there is a winner or the game board is full
     */
    public boolean isOver() {
        boolean isOver = false;
        if (hasWinner() || isCaseFull()) {
            isOver = true;
        }
        return isOver;

    }
}