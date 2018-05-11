package com.tictactoe;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static com.tictactoe.Main.gameIsOver;

public class Computer extends Player{

  public Computer(String name, String symbol) {
    super(name, symbol);
  }

  public int getSpot(String[] board, Player player1, Player player2, Player currentPlayer) {

    System.out.print(currentPlayer.getName() + " moves:\n");

    ArrayList<String> availableSpaces = new ArrayList<String>();
    boolean foundBestMove = false;
    int spot = 100;
    for (String s: board) {
      if (s != player1.getSymbol() && s != player2.getSymbol()) {
        availableSpaces.add(s);
      }
    }
    for (String as: availableSpaces) {
      spot = Integer.parseInt(as);
      board[spot] = this.getSymbol();
      if (gameIsOver()) {
        foundBestMove = true;
        board[spot] = as;
        return spot;
      } else {
        board[spot] = this.getSymbol();
        if (gameIsOver()) {
          foundBestMove = true;
          board[spot] = as;
          return spot;
        } else {
          board[spot] = as;
        }
      }
    }
    if (foundBestMove) {
      return spot;
    } else {
      int n = ThreadLocalRandom.current().nextInt(0, availableSpaces.size());
      return Integer.parseInt(availableSpaces.get(n));
    }
  }

}
