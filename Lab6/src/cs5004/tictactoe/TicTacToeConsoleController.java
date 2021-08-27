package cs5004.tictactoe;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class represent the controller model.
 */
public class TicTacToeConsoleController implements TicTacToeController {
  private final Readable in;
  private final Appendable out;

  /**
   * Construct tic tac toe object.
   * @param in1 object.
   * @param out1 object.
   */
  public TicTacToeConsoleController(Readable in1, Appendable out1) {
    if (in1 == null || out1 == null) {
      throw new IllegalArgumentException("Can't be null");
    }
    this.in = in1;
    this.out = out1;
  }

  @Override
  public void playGame(TicTacToe m) {
    if (m == null) {
      throw new IllegalArgumentException("Tictactoe null");
    }
    Scanner scan = new Scanner(in);
    try { // everytime you use appendable, need to do a try/block clause
      out.append(m.toString()).append("\n");
      // Print out calling move for and then who's turn it is
      out.append("Enter a move for ").append(m.getTurn().toString()).append(":\n");
      Integer rowInput = null; // int can't be null, but Inter can be null
      Integer columnInput = null;
      String token = ""; // what we are splitting the string by

      while (!m.isGameOver()) {
        token = scan.next(); // will find and put next token in scanner
        if (token.equalsIgnoreCase("q")) {
          break;
        }
        try { // handle individual tokens
          int var = Integer.parseInt(token);
          if (rowInput == null) {
            rowInput = var; // represents the parsed token
          } else {
            columnInput = var;
            m.move(rowInput - 1, columnInput - 1); // starting from 0 so need to subtract 1

            if (m.isGameOver()) {
              out.append(m.toString()).append("\n"); // print out state of game when game is over
              out.append("Game is over! ");
              if (m.getWinner() != null) {
                out.append(m.getWinner().toString() + "wins!\n");
              } else {
                out.append("Tie game.\n");
              }
              break; // break whether winner or tie
            }
            out.append(m.toString()).append("\n");
            out.append("Enter a move for " + m.getTurn().toString()).append(":\n");
            rowInput = columnInput = null; // reset every turn b/c receiving new values
          }

        } catch (NumberFormatException e) {
          out.append("Invalid number" + token + "\n");
        } catch (IllegalArgumentException e) {
          out.append("Invalid move" + rowInput + "," + columnInput + "\n");
          rowInput = columnInput = null;
        }
      }
      if (!m.isGameOver() && token.equalsIgnoreCase("q")) { // game isn't over and user entered quit
        out.append("Game quit! Ending game state:\n" + m.toString() + "\n");
      } else if (!m.isGameOver()) { // not enough inputs left
        throw new IllegalStateException("No more inputs.");
      }
    } catch (IOException e) {
      //scan.close();
      throw new IllegalStateException("Failure to append.");
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("Failure to read from readable.");
    }
    scan.close();
  }
}
