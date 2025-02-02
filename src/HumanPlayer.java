
public class HumanPlayer extends Player {
    InteractionUser tictactoeUser;


    HumanPlayer(char symbol) {
        super(symbol); // access attribute défined in the parent class
        tictactoeUser = new InteractionUser();
    }


    @Override
    public Cell makeMove(Cell[][] board) {
        // row and column entries
        int row, col;
        row = tictactoeUser.getUserInput("line");
        col = tictactoeUser.getUserInput("column");

        return new Cell(row, col, symbol);

    }

}
