package com.tictactoe;

import java.util.List;

public abstract class Player {
  private String name;
  private String symbol;

  public Player(String name, String symbol) {
    this.name = name;
    this.symbol = symbol;
  }

  public String getName() {
    return name;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public int getSpot(List<String> board, Player player1, Player player2, Player currentPlayer) {
    int spot = 0;
    return spot;
  }

}
