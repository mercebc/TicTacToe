package game;

import com.Cli;
import players.Computer;
import players.PlayerFactory;
import players.Player;

import java.util.HashMap;
import java.util.InputMismatchException;


public class Game {

  private Board board;
  private PlayerFactory playerFactory;
  private GameOptions gameOptions;

  private Player player1;
  private Player player2;
  private Player currentPlayer;

  public Game(){

    this.board = new Board(3);

    this.playerFactory = new PlayerFactory();

    this.gameOptions = new GameOptions();

  }

  public Player getPlayer1() {
    return player1;
  }

  public Player getPlayer2() {
    return player2;
  }

  public void setPlayer1(Player player1){ this.player1 = player1;}

  public void setPlayer2(Player player2){ this.player2 = player2;}

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public void setCurrentPlayer(Player currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public GameOptions getGameOptions(){ return gameOptions; }

  public Board getBoard() { return board; }

  public PlayerFactory getPlayerFactory() { return playerFactory; }

  public boolean gameIsNotOver() {
    return !this.board.threeInLine(player1, player2) && !this.board.tie(player1, player2);
  }

  /** Change the player that is playing */

  public Player nextPlayer() {
    if (currentPlayer == player1) {
      return player2;
    } else {
      return player1;
    }
  }

  /** Set the spot that the player has chosen */

  public void setSpot(int spot){
    this.board.setCell(spot,this.currentPlayer.getSymbol());
  }

}
