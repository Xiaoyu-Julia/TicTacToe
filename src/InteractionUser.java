import java.util.Scanner;

public class InteractionUser {
    private Scanner inputUser;
    private Scanner inputPlayerChoice;
    public View view;
    //private Scanner inputUserChoiceSymbol;

    
    public InteractionUser() {
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
