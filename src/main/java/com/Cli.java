package com;

import game.Board;
import game.Game;
import players.Computer;
import players.Player;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cli {

  private PrintStream output;
  private Scanner input;

  public Cli(InputStream inputStream, PrintStream output) {
    this.output = output;
    this.input = new Scanner(inputStream);
  }

  public int askForIntegerBetweenMinAndMax(int min, int max){
    int num;


    if (input.hasNextInt()) {
      num = input.nextInt();
    } else {
      input.next();
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

      if (spot >= min && spot <= max) {
        return spot;
      } else {
        throw new IllegalArgumentException("You can only input an integer between " + min + " and " + max);
      }

    } else if (input.hasNext()) {

      help = input.next();//assigns to help the inserted value. If it isn't a number.

      if (help.equals("h")) {//if the input equals "h" a note is displayed to help the user understand how the game works

        output.println("The following numbers are the position your symbol will be placed.");
        output.println(" 1 | 2 | 3 " + "\n===+===+===\n" + " 4 | 5 | 6 " + "\n===+===+===\n" + " 7 | 8 | 9 ");

        return spot;

      } else {//if it isn't a "h", throw an exception
        throw new InputMismatchException("You can only input integers or \"h\" for help.");
      }

    } else {
      input.next();
      throw new InputMismatchException("You can only input integers or \"h\" for help.");
    }

  }

  public String askForString(){
    if (input.hasNext()) {
      return input.next();
    }else {
        throw new InputMismatchException("You can only input characters.");
      }
  }

  /** Announce winner */
  public void announceWinner(Player winner) {
    output.println("Congratulations! The winner is " + winner.getName());
  }

  /** Announce ties */
  public void announceTie() {
    output.println("Ohh there's no winner, it's a tie!");
  }

  /** Print the game board */
  public void printBoard(Board board) {
    output.println(" " + board.getCell(0).getValue() + " | " + board.getCell(1).getValue() + " | " + board.getCell(2).getValue() + "\n===+===+===\n" + " " + board.getCell(3).getValue()
        + " | " + board.getCell(4).getValue() + " | " + board.getCell(5).getValue() + "\n===+===+===\n" + " " + board.getCell(6).getValue() + " | " + board.getCell(7).getValue() + " | " + board.getCell(8).getValue() + "\n"); // print all the board cells
  }


}