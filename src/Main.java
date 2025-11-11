import java.util.Scanner;

public class Main {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String player = "X";
        boolean playAgain;

        do {
            clearBoard();
            int moveCount = 0;
            boolean gameOver = false;

            while (!gameOver) {
                display();
                int row = SafeInput.getRangedInt(in, "Player " + player + ", enter row", 1, ROWS) - 1;
                int col = SafeInput.getRangedInt(in, "Player " + player + ", enter column", 1, COLS) - 1;

                while (!isValidMove(row, col)) {
                    System.out.println("Cell already taken. Try again.");
                    row = SafeInput.getRangedInt(in, "Player " + player + ", enter row", 1, ROWS) - 1;
                    col = SafeInput.getRangedInt(in, "Player " + player + ", enter column", 1, COLS) - 1;
                }

                board[row][col] = player;
                moveCount++;

                if (moveCount >= 5 && isWin(player)) {
                    display();
                    System.out.println("Player " + player + " wins!");
                    gameOver = true;
                } else if (moveCount == 9 && isTie()) {
                    display();
                    System.out.println("It's a tie!");
                    gameOver = true;
                } else {
                    player = player.equals("X") ? "O" : "X";
                }
            }

            playAgain = SafeInput.getYNConfirm(in, "Play again?");
        } while (playAgain);

        in.close();
    }

    private static void clearBoard() {
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++)
                board[i][j] = " ";
    }

    private static void display() {
        System.out.println("-------------");
        for (int i = 0; i < ROWS; i++) {
            System.out.print("| ");
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROWS; i++)
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player))
                return true;
        return false;
    }

    private static boolean isColWin(String player) {
        for (int j = 0; j < COLS; j++)
            if (board[0][j].equals(player) && board[1][j].equals(player) && board[2][j].equals(player))
                return true;
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++)
                if (board[i][j].equals(" "))
                    return false;
        return true;
    }
}
