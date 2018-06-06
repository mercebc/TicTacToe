package com;

import game.Game;
import players.Computer;
import players.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cli {

  Scanner input = new Scanner(System.in); // the input Scanner

  public int askForIntegerBetweenMinAndMax(int min, int max){
    int num;

    if (input.hasNextInt()) {
      num = input.nextInt();
    } else {
      throw new InputMismatchException("You can only input integers");
    }

    if (num >= min && num <= max) {
      return num;
    } else {
      throw new IllegalArgumentException("You can only input an integer between " + min + " and " + max);
    }

  }


  public int askForIntegerOrHelpBetweenMinAndMax(int min, int max){
    int spot = 0;
    String help;

    if (input.hasNextInt()) {
      spot = input.nextInt();//assigns to spot the inserted value. Only if it's a number

      if (spot < min && spot > max) {
        return spot;
      } else {
        throw new IllegalArgumentException("You can only input an integer between" + min + " and " + max);
      }

    } else if (input.hasNext()) {

      help = input.next();//assigns to help the inserted value. If it isn't a number.

      if (help.equals("h")) {//if the input equals "h" a note is displayed to help the user understand how the game works

        System.out.println("The following numbers are the position your symbol will be placed.");
        System.out.println(" 1 | 2 | 3 " + "\n===+===+===\n" + " 4 | 5 | 6 " + "\n===+===+===\n" + " 7 | 8 | 9 ");

        return spot;

      } else {//if it isn't a "h", throw an exception
        throw new InputMismatchException("You can only input integers or \"h\" for help.");
      }

    } else {
      throw new InputMismatchException("You can only input integers or \"h\" for help.");
    }

  }


}