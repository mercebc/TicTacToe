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
  private HashMap<Integer, String> gameOptions;

  private Player player1;
  private Player player2;
  private Player currentPlayer;

  private Cli cli;

  public Game(Cli cli){

    this.board = new Board();
    this.cli = cli;

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

    whoStartsFirst();

    do {
      int spot = currentPlayer.getSpot(this.board, nextPlayer());//get the spot of the player, Human and Computer have different methods for this

      setSpot(spot);

      cli.printMessage(currentPlayer.getName() + " moves:\n");//notify the user who moves

      cli.printBoard(board);

      this.currentPlayer = nextPlayer(); //change players

    } while (gameIsNotOver());

    finalGameAnnouncements();

  }

  private boolean gameIsNotOver() {
    return !this.board.threeInLine(player1, player2) && !this.board.tie(player1, player2);
  }

  /** Initializes the game with player against computer. This is the default game if players haven't been changed */

  public void createPlayers(int key) {

    String option = gameOptions.get(key);

    player1 = playerFactory.player1(option, cli);
    player2 = playerFactory.player2(option, cli);

  }

  public void whoStartsFirst() {
    int num = 0;
    try{
      if(!(player1 instanceof Computer)){
        cli.printMessage("Enter \"1\" for " + player1.getName() + " to start or \"2\" for " + player2.getName() + " to start");//Player choose who starts the game

        num = cli.askForIntegerBetweenMinAndMax(1,2);
      }

      if (num == 1 || player1 instanceof Computer) {
        currentPlayer = player1;
      } else {
        currentPlayer = player2;
      }
    } catch (IllegalArgumentException ex) {
      cli.printMessage(ex.getMessage());
      whoStartsFirst();//recursive call when there is an exception

    } catch (InputMismatchException ex) {
      cli.printMessage(ex.getMessage());
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
  public boolean playAgain() {

    cli.printMessage("Enter \"1\" to Quit or \"2\" to Play again");//Player choose who starts the game

    boolean playAgain = false;

    try{

      int num = cli.askForIntegerBetweenMinAndMax(1,2);

      if (num == 1) {
        playAgain = false;
      } else {
        this.board.clearBoard();
        playAgain = true;
      }
    } catch (IllegalArgumentException ex) {
      cli.printMessage(ex.getMessage());
      playAgain();//recursive call when there is an exception

    } catch (InputMismatchException ex) {
      cli.printMessage(ex.getMessage());
      playAgain();
    }
    return playAgain;
  }

  public void quit() {
    cli.printMessage("Sorry to hear you are leaving us, see you soon!");
  }
}
