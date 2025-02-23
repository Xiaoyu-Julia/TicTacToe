
public class HumanPlayer extends Player {
    InteractionUserController tictactoeUser;


    HumanPlayer(char symbol) {
        super(symbol); // access attribute défined in the parent class
        tictactoeUser = new InteractionUserController();
    }


    @Override
    public Board makeMove(Board[][] board) {
        // row and column entries
        int row, col;
        row = tictactoeUser.getUserInput("line");
        col = tictactoeUser.getUserInput("column");

        return new Board(row, col, symbol);

    }

}
