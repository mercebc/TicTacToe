package players;

import com.Cli;
import game.Board;

import java.util.Scanner;

public abstract class Player {//Abstract class. Cannot be instantiated.
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

  public int getSpot(Board board, Player player1, Player player2, Player currentPlayer, Cli cli) {
    int spot = 0;
    return spot;
  }

  public boolean changeName(String InputStream, Player player2) { return false; }

  public boolean changeSymbol(String InputStream, Player player2) { return false; }


}
