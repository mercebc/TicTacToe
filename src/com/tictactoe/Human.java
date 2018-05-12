package com.tictactoe;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Human extends Player {

  public Human(String name, String symbol) {
    super(name, symbol);
  }

  /** Update global variables "board" and "currentPlayer". */
  public static Scanner input = new Scanner(System.in); // the input Scanner

  public int getSpot(List<String> board, Player player1, Player player2, Player currentPlayer) {
    System.out.print(currentPlayer.getName() + ", please enter a number between 1 and 9:\n");
    int spot = 0;
    boolean validInput = false;
    do {
      try {
        if (input.hasNextInt()) {
          spot = input.nextInt();
        } else {
          throw new InputMismatchException("You can only input integers");
        }

        if (spot > 0 && spot < 10) {
           validInput = true;
           spot = spot - 1;
        } else {
           throw new IllegalArgumentException("You can only input an integer between 1 and 9");
        }

      }
      catch (InputMismatchException ex) {
        System.out.println(ex.getMessage());
        input.next();
        validInput = false;
      }

      catch (IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
        validInput = false;
      }

  } while(!validInput);

  return spot;

  }

}
