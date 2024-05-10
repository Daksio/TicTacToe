import java.io.FileWriter;
import java.io.IOException;

public class Model {

    private View view;

    public void setView(View view) {
        this.view = view;
    }

    private String[][] board;
    private boolean gameOver;
    private int turns;
    private String currentPlayer;
    private final String playerX = "X";
    private final String playerO = "O";

    private int winsX = 0;
    private int winsO = 0;
    private int ties = 0;

    public Model() {
        board = new String[3][3];
        gameOver = false;
        turns = 0;
        currentPlayer = playerX;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = "";
            }
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
    }

    public void updateBoard(int row, int col) {
        board[row][col] = currentPlayer;
        turns++;
    }

    public void checkWinner() {
        for (int r = 0; r < 3; r++) {
            if (!board[r][0].isEmpty() && board[r][0].equals(board[r][1]) && board[r][0].equals(board[r][2])) {
                setWinner(board[r][0]);
                return;
            }
        }

        for (int c = 0; c < 3; c++) {
            if (!board[0][c].isEmpty() && board[0][c].equals(board[1][c]) && board[0][c].equals(board[2][c])) {
                setWinner(board[0][c]);
                return;
            }
        }

        if (!board[0][0].isEmpty() && board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])) {
            setWinner(board[0][0]);
            return;
        }
        if (!board[0][2].isEmpty() && board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])) {
            setWinner(board[0][2]);
            return;
        }

        if (turns == 9) {
            setTie();
        }
    }

    public void setWinner(String player) {
        gameOver = true;
        if (player.equals(playerX)) {
            winsX++;
        } else {
            winsO++;
        }
        updateResults();
        view.setText(player + " is the winner!");
    }

    public void setTie() {
        gameOver = true;
        ties++;
        updateResults();
        view.setText("It's a tie!");
    }

    public void updateResults() {
        try (FileWriter writer = new FileWriter("Result.txt")) {
            if (winsO == 1) {
                writer.write("В прошлый раз победил: \"O\"");
            } else if (winsX == 1) {
                writer.write("В прошлый раз победил: \"X\"");
            } else {
                writer.write("В прошлый раз была ничья");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
