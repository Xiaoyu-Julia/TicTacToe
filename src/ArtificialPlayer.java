import java.util.Random;

public class ArtificialPlayer extends Player {

    ArtificialPlayer(char symbol) {
        super(symbol);
    }

    @Override

    public Board makeMove(Board[][] board) {
        //Random random = new Random();
        int randomRow = new Random().nextInt(0, board.length);
        int randomCol = new Random().nextInt(0, board[0].length);

        return new Board(randomRow, randomCol, symbol);
    }
}
