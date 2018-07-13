package com;

import game.Board;
import players.Player;

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

}
