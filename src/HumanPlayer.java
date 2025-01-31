import java.util.Scanner;

public class HumanPlayer extends Player {

    HumanPlayer(char symbol) {
        super(symbol);
    }


    @Override
    public Cell makeMove(Cell[][] board) {

        int row, col;
        row = getUserInput("line");
        col = getUserInput("column");

        return new Cell(row, col, symbol);

    }

    /**
     * A function to get the user's choice of line and column
     *
     * @param str Choose line or column
     */
    private int getUserInput(String str) {
        Scanner input = new Scanner(System.in);
        int row;
        System.out.print("Please choose one " + str + " : ");
        while (!input.hasNextInt()) {
            System.out.print("This is not a number, please try again: ");
            input.nextLine(); // vidage saisie incorrect
        }
        row = input.nextInt();
        return row;
    }

    public char getSymbol() {
        return symbol;
    }
}
