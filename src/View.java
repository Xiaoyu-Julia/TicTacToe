public class View {
    // Show the game board without pawns
    public void showBoard(Cell[][] board) {
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
}
