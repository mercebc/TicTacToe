package com;

import exceptions.SameNameException;
import exceptions.SameSpotException;
import exceptions.SameSymbolException;
import game.Board;
import players.Player;

import java.util.InputMismatchException;

public class Validate {

  public boolean notSameName(Player opponent, String name) throws SameNameException {
    if (!opponent.getName().equals(name)){
      return true;
    }else{
      throw new SameNameException("The name you are trying to change is the same one as your opponent's name: " + opponent.getName());
    }
  }

  public boolean notSameSymbol(Player opponent, String symbol) throws SameSymbolException {
    if (!opponent.getSymbol().equals(symbol)){
      return true;
    }else{
      throw new SameSymbolException("The symbol you are trying to change is the same one as your opponent's symbol: " + opponent.getSymbol());
    }
  }

  public boolean spot(Board board, int spot, Player player1, Player player2) throws SameSpotException{
    if (!board.getCell(spot).belongsTo(player1) && !board.getCell(spot).belongsTo(player2)){
      return true;
    }else{
      throw new SameSpotException("Enter a number that is not already used");
    }
  }

  public int betweenMinAndMax(int min, int max, int num) throws IllegalArgumentException{
    if (num >= min && num <= max){
      return num;
    } else {
      throw new IllegalArgumentException("You can only input an integer between " + min + " and " + max);
    }
  }

  public boolean isHelp(String help) throws InputMismatchException{
    if (help.equalsIgnoreCase("h")) {//if the input equals "h" a note is displayed to help the user understand how the game works
      return true;
    } else {//if it isn't a "h", throw an exception
      throw new InputMismatchException("You can only input integers or \"h\" for help.");
    }
  }

}
