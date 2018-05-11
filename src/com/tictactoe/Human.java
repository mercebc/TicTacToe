package com.tictactoe;

import java.util.List;
import java.util.Scanner;

public class Human extends Player {

  public Human(String name, String symbol) {
    super(name, symbol);
  }

  /** Update global variables "board" and "currentPlayer". */
  public static Scanner input = new Scanner(System.in); // the input Scanner

  public int getSpot(List<String> board, Player player1, Player player2, Player currentPlayer) {
    System.out.print(currentPlayer.getName()+ ", please enter a number between 0 and 8:\n");
    int spot = input.nextInt();
    return spot;
  }

  public void chooseName() {
    System.out.print("Enter the player name:\n");
    super.setName(input.next());
  }

  public void chooseSymbol() {
    System.out.print("Enter the player symbol:\n");
    super.setSymbol(input.next());
  }



}
