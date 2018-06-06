package game;

import com.Cli;
import players.PlayerFactory;
import players.Player;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Game {

  private Board board;
  private Cli cli;
  private PlayerFactory playerFactory;
  private HashMap<Integer, String> gameOptions;

  private Player player1;
  private Player player2;
  private Player currentPlayer;

  public static Scanner input = new Scanner(System.in);

  public Game(Cli cli){

    this.cli = cli;
    this.board = new Board();

    this.playerFactory = new PlayerFactory();
    this.gameOptions = new HashMap<Integer, String>();
    gameOptions.put(1, "HUMAN-HUMAN");
    gameOptions.put(2, "HUMAN-COMPUTER");
    gameOptions.put(3, "COMPUTER-COMPUTER");
    createPlayers(2);
  }

  public Player getPlayer1() {
    return player1;
  }

  public Player getPlayer2() {
    return player2;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }



  public void initGame(){

    whoStartsFirst();

    do {
      int spot = getSpot();

      setSpot(spot);

      this.currentPlayer = nextPlayer(); //change players

      printBoard();

    } while (!this.board.threeInLine(player1, player2) && !this.board.tie(player1, player2)); // repeat if not game-over

    finalGameAnnouncements();

  }

  /** Initializes the game with player against computer. This is the default game if players haven't been changed */

  public void createPlayers(int key) {

    String option = gameOptions.get(key);

    player1 = playerFactory.player1(option);
    player2 = playerFactory.player2(option);

  }

  public void whoStartsFirst() {

    System.out.println("Enter \"1\" for " + player1.getName() + " to start or \"2\" for " + player2.getName() + " to start");//Player choose who starts the game

    try{
      if (cli.askForIntegerBetweenMinAndMax(1,2) == 1) {
        currentPlayer = player1;
      } else if (cli.askForIntegerBetweenMinAndMax(1,2) == 2) {
        currentPlayer = player2;
      }
    } catch (IllegalArgumentException ex) {
      System.out.println(ex.getMessage());
      whoStartsFirst();//recursive call when there is an exception

    } catch (InputMismatchException ex) {
      System.out.println(ex.getMessage());
      input.next();
      whoStartsFirst();
    }

  }

  /** Change the player that is playing */
  public Player nextPlayer() {
    if (currentPlayer == player1) {
      return player2;
    } else {
      return player1;
    }
  }

  /** Get the spot of the player that is playing */
  public int getSpot(){

    return currentPlayer.getSpot(this.board, this.player1, this.player2, this.currentPlayer);//get the spot of the player, Human and Computer have different methods for this

  }

  /** Set the spot that the player has chosen */
  public void setSpot(int spot){

    this.board.setCell(spot,this.currentPlayer.getSymbol());

  }

  /** Print the game board */
  public void printBoard() {
    System.out.println(" " + this.board.getCell(0) + " | " + this.board.getCell(1) + " | " + this.board.getCell(2) + "\n===+===+===\n" + " " + this.board.getCell(3)
    + " | " + this.board.getCell(4) + " | " + this.board.getCell(5) + "\n===+===+===\n" + " " + this.board.getCell(6) + " | " + this.board.getCell(7) + " | " + this.board.getCell(8) + "\n"); // print all the board cells
  }

  /** Announce winner */
  public void announceWinner(Player winner) {
    System.out.println("Congratulations! The winner is " + winner.getName());
  }

  /** Announce ties */
  public void announceTie() {
    System.out.println("Ohh there's no winner, it's a tie!");
  }

  /** Final Game Announcements */
  public void finalGameAnnouncements(){
    if(this.board.tie(player1, player2)){
      announceTie();
    }

    if(this.board.checkLinePlayer(player1)){
      announceWinner(player1);
    }

    if(this.board.checkLinePlayer(player2)){
      announceWinner(player2);
    }
  }

  /** Ask if player want to play again */
  public boolean playAgain() {

    System.out.println("Enter \"1\" to Quit or \"2\" to Play again");//Player choose who starts the game

    try{
      if (cli.askForIntegerBetweenMinAndMax(1,2) == 1) {
        return false;
      } else if (cli.askForIntegerBetweenMinAndMax(1,2) == 2) {
        return true;
      }
    } catch (IllegalArgumentException ex) {
      System.out.println(ex.getMessage());
      playAgain();//recursive call when there is an exception

    } catch (InputMismatchException ex) {
      System.out.println(ex.getMessage());
      input.next();
      playAgain();
    }

    return false;
  }

}
