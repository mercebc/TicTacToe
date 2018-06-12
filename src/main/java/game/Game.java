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

  public void setCurrentPlayer(Player currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public Board getBoard() {
    return board;
  }


  public void initGame(){

    System.out.println("Enter \"1\" for " + player1.getName() + " to start or \"2\" for " + player2.getName() + " to start");//Player choose who starts the game

    whoStartsFirst(cli);

    do {
      int spot = getSpot();

      setSpot(spot);

      this.currentPlayer = nextPlayer(); //change players

      cli.printBoard(board);

    } while (!this.board.threeInLine(player1, player2) && !this.board.tie(player1, player2)); // repeat if not game-over

    finalGameAnnouncements();

    System.out.println("Enter \"1\" to Quit or \"2\" to Play again");//Player choose who starts the game

    playAgain(cli);
  }

  /** Initializes the game with player against computer. This is the default game if players haven't been changed */

  public void createPlayers(int key) {

    String option = gameOptions.get(key);

    player1 = playerFactory.player1(option);
    player2 = playerFactory.player2(option);

  }

  public void whoStartsFirst(Cli cli) {

    int inputStream = cli.askForIntegerBetweenMinAndMax(1,2);

    try{
      if (inputStream == 1) {
        currentPlayer = player1;
      } else if (inputStream == 2) {
        currentPlayer = player2;
      }
    } catch (IllegalArgumentException ex) {
      System.out.println(ex.getMessage());
      whoStartsFirst(cli);//recursive call when there is an exception

    } catch (InputMismatchException ex) {
      System.out.println(ex.getMessage());
      whoStartsFirst(cli);
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

    return currentPlayer.getSpot(this.board, this.player1, this.player2, this.cli);//get the spot of the player, Human and Computer have different methods for this

  }

  /** Set the spot that the player has chosen */
  public void setSpot(int spot){

    this.board.setCell(spot,this.currentPlayer.getSymbol());

  }

  /** Final Game Announcements */
  public void finalGameAnnouncements(){
    if(this.board.tie(player1, player2)){
      cli.announceTie();
    }

    if(this.board.checkLinePlayer(player1)){
      cli.announceWinner(player1);
    }

    if(this.board.checkLinePlayer(player2)){
      cli.announceWinner(player2);
    }
  }

  /** Player want to play again */
  public boolean playAgain(Cli cli) {

    Integer inputStream = cli.askForIntegerBetweenMinAndMax(1,2);

    try{

      if (inputStream == 1) {
        return false;
      } else if (inputStream == 2) {
        return true;
      }
    } catch (IllegalArgumentException ex) {
      System.out.println(ex.getMessage());
      playAgain(cli);//recursive call when there is an exception

    } catch (InputMismatchException ex) {
      System.out.println(ex.getMessage());
      playAgain(cli);
    }

    return false;
  }

}
