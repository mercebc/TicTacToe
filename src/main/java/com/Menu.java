package com;

import game.Game;
import players.Computer;
import players.Player;

import java.util.InputMismatchException;


public class Menu {

  Game game;
  Cli cli;

  public Menu(Game game, Cli cli) {

    this.game = game;
    this.cli = cli;

  }

  private Player player2() {
    return this.game.getPlayer2();
  }

  private Player player1() {
    return this.game.getPlayer1();
  }

  public void flowGame(){

    int choiceEntry = showStartMenuAndGetOption();

    showOptionsMenu(choiceEntry);

  }


  public int showStartMenuAndGetOption(){

    int maxvalue;

      if (ComputerAgainstComputer()) {
        cli.printMessage("Enter \"1\" to watch a computer battle, \"2\" to Choose game mode or \"3\" to Exit");
        maxvalue = 3;
      } else {
        cli.printMessage("Enter \"1\" to Play, \"2\" to Choose game mode, \"3\" to Change names, \"4\" to Change symbols or \"5\" to Exit");
        maxvalue = 5;
      }

    return getChoiceEntry(maxvalue);

  }

  private int getChoiceEntry(int maxvalue) {
    try {

      return cli.askForIntegerBetweenMinAndMax(1,maxvalue);

    } catch (IllegalArgumentException ex) {//catch the exceptions
      cli.printMessage(ex.getMessage());
      return showStartMenuAndGetOption(); //to loop

    } catch (InputMismatchException ex) {
      cli.printMessage(ex.getMessage());
      return showStartMenuAndGetOption();
    }

  }


  public void showOptionsMenu(int choiceEntry){

     switch (choiceEntry) {

        case 1:

          this.game.initGame();

          if(this.game.playAgain()){
            flowGame();
          }else{
            game.quit();
          }

          break;

        case 2:

          choosePlayers(choiceEntry);
          flowGame();

          break;

        case 3:

          if (ComputerAgainstComputer()) {
            game.quit();
          }else{
            changePlayersName(choiceEntry);
            flowGame();
          }

          break;

        case 4:

          changePlayersSymbol(choiceEntry);
          flowGame();

          break;

       case 5:

          game.quit();

          break;

      }
  }

  private void changePlayersSymbol(int choiceEntry) {

    if (playingAgainstComputer()) {//only change human player symbol
      if(!player1().changeSymbol(player2())){
        changePlayersSymbol(choiceEntry);
      };

    } else {
      cli.printMessage("Enter \"1\" to change " + player1().getName() + "'s symbol or \"2\" to change " + player2().getName() + "'s symbol");

      try {
        int num = cli.askForIntegerBetweenMinAndMax(1, 2);

        if (num == 1) {
          if(player1().changeSymbol(player2())){
          }else{
            changePlayersSymbol(choiceEntry);
          };
        } else {
          if(player2().changeSymbol(player1())){
          }else{
            changePlayersSymbol(choiceEntry);
          };
        }

      } catch (IllegalArgumentException ex) {
        cli.printMessage(ex.getMessage());
        changePlayersSymbol(choiceEntry);

      } catch (InputMismatchException ex) {
        cli.printMessage(ex.getMessage());
        changePlayersSymbol(choiceEntry);
      }
    }

  }

  private void changePlayersName(int choiceEntry) {

    if (playingAgainstComputer()) {//only change human player name
      if(!player1().changeName(player2())){
        changePlayersName(choiceEntry);
      };

    } else {//when playing against player, you can change both names
      cli.printMessage("Enter \"1\" to change " + player1().getName() + "'s name or \"2\" to change " + player2().getName() + "'s name");

      try {
        int num = cli.askForIntegerBetweenMinAndMax(1, 2);

        if (num == 1) {
          if(player1().changeName(player2())){
          }else{
            changePlayersName(choiceEntry);
          };
        } else {
          if(player2().changeName(player1())){
          }else{
            changePlayersName(choiceEntry);
          };

        }

      } catch (IllegalArgumentException ex) {
        cli.printMessage(ex.getMessage());
        changePlayersName(choiceEntry);

      } catch (InputMismatchException ex) {
        cli.printMessage(ex.getMessage());
        changePlayersName(choiceEntry);
      }
    }

  }

  private void choosePlayers(int choiceEntry) {
    System.out.println("Enter \"1\" to play against another Player or \"2\" to play against the Computer or \"3\" to watch Computer playing against Computer");

    try {

      int type = cli.askForIntegerBetweenMinAndMax(1,3);

      this.game.createPlayers(type);

      if (type == 1 || type == 2) {
        cli.printMessage("You are playing against " + player2().getName());//notifies the player
      }

    } catch (IllegalArgumentException ex) {
      cli.printMessage(ex.getMessage());
      choosePlayers(choiceEntry);

    } catch (InputMismatchException ex) {
      cli.printMessage(ex.getMessage());
      choosePlayers(choiceEntry);
    }

  }


  private boolean ComputerAgainstComputer() {
    return player1() instanceof Computer;
  }

  private boolean playingAgainstComputer() {
    return (player2() instanceof Computer) && !(ComputerAgainstComputer());
  }


}
