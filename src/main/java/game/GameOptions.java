package game;

import java.util.HashMap;

public class GameOptions {

  private HashMap<Integer, String> gameOptions;

  public GameOptions(){

    this.gameOptions = new HashMap<Integer, String>();

    gameOptions.put(1, "HUMAN-HUMAN");
    gameOptions.put(2, "HUMAN-COMPUTER");
    gameOptions.put(3, "COMPUTER-COMPUTER");

  }

  public HashMap<Integer, String> getGameOptions() {
    return gameOptions;
  }
}
