package com;

import game.Board;
import players.Player;

import java.util.InputMismatchException;

public class Validate {

  public boolean notSameName(Player opponent, String name) {
    return (!(opponent.getName().equals(name)));
  }

  public boolean notSameSymbol(Player opponent, String symbol) {
    return (!(opponent.getSymbol().equals(symbol)));
  }

  public boolean spot(Board board, int spot, Player player1, Player player2){
    return (!board.getCell(spot).belongsTo(player1) && !board.getCell(spot).belongsTo(player2));
  }

  public int betweenMinAndMax(int min, int max, int num){
    if (num >= min && num <= max){
      return num;
    } else {
      throw new IllegalArgumentException("You can only input an integer between " + min + " and " + max);
    }
  }

  public boolean isHelp(String help){
    if (help.equalsIgnoreCase("h")) {//if the input equals "h" a note is displayed to help the user understand how the game works
      return true;
    } else {//if it isn't a "h", throw an exception
      throw new InputMismatchException("You can only input integers or \"h\" for help.");
    }
  }

}
