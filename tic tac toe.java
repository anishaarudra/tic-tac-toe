import java.util.Scanner;

class TicTacToe {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    boolean playAgain; 

    // Use a do-while loop to play the game at least once
    do {
      // --- Initialize game state for a NEW game ---
      char[][] board = new char[3][3];
      for (int row = 0; row < board.length; row++) {
        for (int col = 0; col < board[row].length; col++) {
          board[row][col] = ' ';
        }
      }

      char player = 'X';
      boolean gameOver = false;
      int moves = 0; // To track moves for a draw
      // --- End of initialization ---

      // This is your original game loop
      while (!gameOver) {
        printBoard(board);
        System.out.println("Player " + player + ", enter row and col (0-2):"); // Clearer prompt

        int row, col;
        // Basic input validation
        try {
            row = sc.nextInt();
            col = sc.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter numbers only.");
            sc.next(); // Clear the bad input from the scanner
            continue;  // Ask for input again
        }


        // Check if move is valid (within bounds AND on an empty spot)
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
          board[row][col] = player; // Place the move
          moves++; // Increment move counter

          gameOver = haveWon(board, player);
          if (gameOver) {
            System.out.println(player + " Player has Won!");
          } else if (moves == 9) { // Check for a draw
            System.out.println("It's a draw!");
            gameOver = true; // End the game
          } else {
            // Switch player
            player = (player == 'X') ? 'O' : 'X';
          }
        } else {
          System.out.println("Invalid move. Try Again!");
        }
      }

      // --- Game Over ---
      System.out.println("Final Board:");
      printBoard(board); // Print the final board state

      // Ask the user to play again
      System.out.println("Do you want to play again? (yes/no)");
      String response = sc.next();
      playAgain = response.equalsIgnoreCase("yes");

    } while (playAgain); // Repeat the loop if the user says "yes"

    System.out.println("Thanks for playing!");
    sc.close(); // Close the scanner when completely done
  }

  public static boolean haveWon(char[][] board, char player) {
    // check for the row
    for (int row = 0; row < board.length; row++) {
      if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
        return true;
      }
    }
    // now check for the column
    for (int col = 0; col < board.length; col++) {
      if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
        return true;
      }
    }
    // check for the diagonal as well
    if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
      return true;
    }
    if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
      return true;
    }
    return false;
  }

  public static void printBoard(char[][] board) {
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        System.out.print(board[row][col] + " | ");
      }
      System.out.println();
    }
  }
}