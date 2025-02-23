import java.util.Scanner;
import static javax.swing.text.html.parser.DTDConstants.EMPTY;

public class InteractionUserController {
    private Scanner inputUser;
    private Scanner inputPlayerChoice;
    public View view;
    //private Scanner inputUserChoiceSymbol;

    public InteractionUserController() {
        this.inputUser = new Scanner(System.in);
        this.inputPlayerChoice = new Scanner(System.in);
        //this.inputUserChoiceSymbol = new Scanner(System.in);
        this.view = new View();
    }

    /**
     * A function to get the user's choice of line and column
     * @param str Choose line or column
     */
    public int getUserInput(String str) {
        int row;
        view.showIndicationMessageForUserInput(str);
        while (!inputUser.hasNextInt()) {
            view.showErrorMessageForUserInputEtPlayerChoice();
            inputUser.nextLine(); // vidage saisie incorrect
        }
        row = inputUser.nextInt();
        return row;
    }

    // A function to choose players : two humans players, tow artificial players, or one human player and one artificial player
    public int getPlayerChoice(String str) {
        view.showIndicationMessageForPlayerChoice(str);
        while (!inputPlayerChoice.hasNextInt()) {
            view.showErrorMessageForUserInputEtPlayerChoice();
            inputPlayerChoice.nextLine(); // vidage saisie incorrect
        }
        return inputPlayerChoice.nextInt();
    }

   /* public char getUserChoiceSymbol() {
        System.out.println("Choose your pawn in the game board (X ou O) for the first player:");
        return inputUserChoiceSymbol.next().toUpperCase().charAt(0);
    }*/

    public void closeScanner() {
        inputUser.close();
        inputPlayerChoice.close();
    }

//    public void playTokenOnBoard(Player currentPlayer, Board[][] board) {
//
//        Board move = currentPlayer.makeMove(board);
//
//        // entry control, to verify that the line and the column chosen by the current player are authorized
//        if ((move.row >= 0) && (move.row < board.length) // row bounds check
//                && (move.col >= 0) && (move.col < board.length) // col bounds check
//                && (board[move.row][move.col].getValue() == EMPTY)) {
//            //System.out.print(move.row + ", " + move.col + "\n");
//            putTokenOnBoard(move, board); // Call the function to put the pawn
//
//            return;
//        }
//        view.showErrorMessageForEntryControl();
//        playTokenOnBoard(currentPlayer, board);
//
//    }

//    public void putTokenOnBoard(Board move, Board[][] board) {
//        board[move.row][move.col].setValue(move.getValue());
//    }
//
//    public void setSymbolToPlayerChoice (Player p1, Player p2) {
//        int firstPlayerChoice = getPlayerChoice("first");
//        int secondPlayerChoice = getPlayerChoice("second");
//
//        // choose the type of first player and set a symbol
//        if (firstPlayerChoice == 1) {
//            p1 = new ArtificialPlayer('O');
//        } else {
//            p1 = new HumanPlayer('O');
//        }
//
//        if (secondPlayerChoice == 2) {
//            p2 = new HumanPlayer('X');
//        } else {
//            p2 = new ArtificialPlayer('X');
//        }
//    }
//
//    public Player setSymbolToFirstPlayer (Player p1) {
//        Player firstPlayer;
//        int firstPlayerChoice = getPlayerChoice("first");
//
//        // choose the type of first player and set a symbol
//        if (firstPlayerChoice == 1) {
//            firstPlayer = new ArtificialPlayer('O');
//        } else {
//            firstPlayer = new HumanPlayer('O');
//        }
//
//        return firstPlayer;
//    }
//
//    public Player setSymbolToSecondPlayer (Player p2) {
//        Player secondPlayer;
//        int secondPlayerChoice = getPlayerChoice("second");
//
//        // choose the type of first player and set a symbol
//        if (secondPlayerChoice == 2) {
//            secondPlayer = new HumanPlayer('X');
//        } else {
//            secondPlayer = new ArtificialPlayer('X');
//        }
//
//        return secondPlayer;
//    }

}