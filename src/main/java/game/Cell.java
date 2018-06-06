package game;

import players.Player;

public class Cell {
  private String value = " ";

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  boolean belongsTo(Player player) {
    return value.equals(player.getSymbol());
  }
}
