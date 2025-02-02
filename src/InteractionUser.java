import java.util.Scanner;

public class InteractionUser {
    private Scanner inputUser;
    private Scanner inputPlayerChoice;

    
    public InteractionUser() {
        this.inputUser = new Scanner(System.in);
        this.inputPlayerChoice = new Scanner(System.in);
    }

    /**
     * A function to get the user's choice of line and column
     * @param str Choose line or column
     */
    public int getUserInput(String str) {
        int row;
        System.out.print("Choose a cell to put the pawn in the game board. Please choose one " + str + " : ");
        while (!inputUser.hasNextInt()) {
            System.out.print("This is not a number, please try again: ");
            inputUser.nextLine(); // vidage saisie incorrect
        }
        row = inputUser.nextInt();
        return row;
    }

    // A function to choose players : two humans players, tow artificial players, or one human player and one artificial player
    public int getPlayerChoice(String str) {
        System.out.println("You can enter 1 for an artificial Player, or 2 for a human Player.");
        System.out.println("Choose the " + str + " player:");
        return inputPlayerChoice.nextInt();
    }
}
