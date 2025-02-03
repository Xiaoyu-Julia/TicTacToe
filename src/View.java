public class View {
    // Show the game board without pawns
    public void showBoard(Board[][] board) {
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

    public void showErrorMessageForEntryControl(){
        System.out.print("There is a pawn here or you have chosen a square that is off the board. Please change your choice: line [0, 2], column [0, 2].");
    }

    public void showIndicationMessageForUserInput(String str){
        System.out.print("Choose a cell to put the pawn in the game board. Please choose one " + str + " : ");
    }

    public void showIndicationMessageForPlayerChoice(String str){
        System.out.println("You can enter 1 for an artificial Player, or 2 for a human Player.");
        System.out.println("Choose the " + str + " player:");
    }

    public void showErrorMessageForUserInputEtPlayerChoice(){
        System.out.print("This is not a number, please try again: ");
    }


    public void showWinner(){
        System.out.println("The game is over. There is a winner.");
    }

    public void showTieMatch() {
        System.out.println("The game is over. There is a tie.");
    }

}
