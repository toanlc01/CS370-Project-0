import java.util.ArrayList;
import java.util.List;

public class TicTacToeBoard {
    private final int size;
    private final Character[][] board;

    public TicTacToeBoard(){
        this.size = 3;
        this.board = new Character[size][size];

        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++){
                this.board[i][j] = ' ';
            }
    }

    public int getSize(){
        return size;
    }

    public void printBoard(){
        String separator = "+---".repeat(3) + '+';

        System.out.println(separator);

        for(int i = 0; i < size; i++){
            StringBuilder line = new StringBuilder("| ");
            for(int j = 0; j < size; j++){
                line.append(board[i][j]);
                line.append(" | ");
            }

            System.out.println(line);
            System.out.println(separator);
        }
    }

    private void checkBounds(int row, int column){
        final char c = board[row][column];
    }

    public boolean isOpen(int row, int column){
        checkBounds(row, column);
        return board[row][column] == ' ';
    }

    public void markSpace(int row, int column, char mark) throws SpaceTakenException {
        checkBounds(row, column);

        if(!isOpen(row, column)){
            throw new SpaceTakenException();
        }

        board[row][column] = mark;
    }

    public boolean isTied(){
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++){
                if(isOpen(i, j)) return false;
            }

        return true;
    }

    public boolean isWonBy(char mark){
        for(int i = 0; i < size; i++){
            if(match(board[i], mark)) return true;
        }

        for(int j = 0; j < size; j++){
            List<Character> column = new ArrayList<>();

            for(int i = 0; i < size; i++){
                column.add(board[i][j]);
            }

            Character[] arr = new Character[column.size()];
            if(match(column.toArray(arr), mark)) return true;
        }

        List<Character> down = new ArrayList<>();
        List<Character> up = new ArrayList<>();

        for(int i = 0; i < size; i++){
            down.add(board[i][i]);
            up.add(board[size - 1 - i][i]);
        }

        Character[] downArr = new Character[down.size()];
        Character[] upArr = new Character[up.size()];

        if(match(down.toArray(downArr), mark) ||
            match(up.toArray(upArr), mark))
            return true;

        return false;
    }

    public boolean match(Character[] spaces, char mark){
        for(char ch : spaces){
            if(ch != mark) return false;
        }

        return true;
    }
}
