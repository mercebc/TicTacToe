package com;

import game.Board;
import game.Game;
import players.Computer;
import players.PlayerFactory;

import java.util.InputMismatchException;

public class GameUI {

  private Board board;
  private PlayerFactory playerFactory;

  private Game game;
  private Cli cli;

  public GameUI(Game game, Cli cli){

    this.game = game;
    this.cli = cli;

    this.board = game.getBoard();
    this.playerFactory = game.getPlayerFactory();

    createPlayers(2);

  }

  public void initGame(){

    whoStartsFirst();

    do {
      int spot = game.getCurrentPlayer().getSpot(this.board, game.nextPlayer());//get the spot of the player, Human and Computer have different methods for this

      game.setSpot(spot);

      cli.printMessage(game.getCurrentPlayer().getName() + " moves:\n");//notify the user who moves

      cli.printBoard(board);

      game.setCurrentPlayer(game.nextPlayer()); //change players

    } while (game.gameIsNotOver());

    finalGameAnnouncements();

  }

  public void whoStartsFirst() {
    int num = 0;
    try{
      if(!(game.getPlayer1() instanceof Computer)){
        cli.printMessage("Enter \"1\" for " + game.getPlayer1().getName() + " to start or \"2\" for " + game.getPlayer2().getName() + " to start");//Player choose who starts the game

        num = cli.askForIntegerBetweenMinAndMax(1,2);
      }

      if (num == 1 || game.getPlayer1() instanceof Computer) {
        game.setCurrentPlayer(game.getPlayer1());
      } else {
        game.setCurrentPlayer(game.getPlayer2());
      }
    } catch (IllegalArgumentException ex) {
      cli.printMessage(ex.getMessage());
      whoStartsFirst();//recursive call when there is an exception

    } catch (InputMismatchException ex) {
      cli.printMessage(ex.getMessage());
      whoStartsFirst();
    }
  }

  /** Final Game Announcements */
  public void finalGameAnnouncements(){
    if(this.board.tie(game.getPlayer1(), game.getPlayer2())){
      cli.announceTie();
    }

    if(this.board.checkLinePlayer(game.getPlayer1())){
      cli.announceWinner(game.getPlayer1());
    }

    if(this.board.checkLinePlayer(game.getPlayer2())){
      cli.announceWinner(game.getPlayer2());
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


  public void createPlayers(int key) {

    String option = game.getGameOptions().getGameOptions().get(key);

    game.setPlayer1(playerFactory.player1(option, cli));
    game.setPlayer2(playerFactory.player2(option, cli));

  }
}
