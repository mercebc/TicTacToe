package com;

import game.Board;
import game.Game;
import players.Computer;
import players.Player;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Cli implements UserInterface {

  private PrintStream output;
  private Scanner input;
  private Validate validate = new Validate();

  public Cli(InputStream inputStream, PrintStream output) {
    this.output = output;
    this.input = new Scanner(inputStream);
  }

  public int askForIntegerBetweenMinAndMax(int min, int max){

    if (input.hasNextInt()) {
      int num = input.nextInt();
      input.skip(".*");
      return validate.betweenMinAndMax(min, max, num);

    } else {
      input.next();
      input.skip(".*");
      throw new InputMismatchException("You can only input integers");
    }

  }

  public int askForIntegerOrHelpBetweenMinAndMax(int min, int max){

    if (input.hasNextInt()) {
      int spot = input.nextInt();//assigns to spot the inserted value. Only if it's a number
      input.skip(".*");
      return validate.betweenMinAndMax(min, max, spot);

    } else {
      String help = input.next();//assigns to help the inserted value. If it isn't a number.
      input.skip(".*");

      if(validate.isHelp(help)){
        printHelp();
        return askForIntegerOrHelpBetweenMinAndMax(min, max);

      } else {//if it isn't a "h", throw an exception
        throw new InputMismatchException("You can only input integers or \"h\" for help.");
      }
    }
  }

  public String askForString(){
    String word = input.next();//take only the first word
    input.skip(".*");//ignore the rest

    return word;
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

  public void printHelp(){
    output.println("The following numbers are the position your symbol will be placed.");
    output.println(" 1 | 2 | 3 " + "\n===+===+===\n" + " 4 | 5 | 6 " + "\n===+===+===\n" + " 7 | 8 | 9 ");
    output.println("Type the number you wish to place your symbol on the board:");
  }

  /** Messages */
  public void printMessage(String message) {
    output.println(message);
  }

}