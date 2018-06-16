package com;

import game.Game;
import players.Computer;
import players.Player;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

  Game game;
  Cli cli;

  public Menu(Game game, Cli cli) {

    this.game = game;
    this.cli = cli;

  }


  public int showMenu(){

    int maxvalue;
    int choiceEntry = -1;

      if (game.getPlayer1() instanceof Computer) {
        cli.printMessage("Enter \"1\" to watch a computer battle, \"2\" to Choose game mode or \"3\" to Exit");
        maxvalue = 3;
      } else {
        cli.printMessage("Enter \"1\" to Play, \"2\" to Choose game mode, \"3\" to Change names, \"4\" to Change symbols or \"5\" to Exit");
        maxvalue = 5;
      }
      //Validate the input
      try {
        choiceEntry = cli.askForIntegerBetweenMinAndMax(1,maxvalue);

        showOptions(choiceEntry, cli);

      } catch (IllegalArgumentException ex) {//catch the exceptions
        cli.printMessage(ex.getMessage());
        showMenu(); //to loop

      } catch (InputMismatchException ex) {
        cli.printMessage(ex.getMessage());
        showMenu();
      }
    return choiceEntry;
  }


  public void showOptions(int choiceEntry, Cli cli){

     switch (choiceEntry) {

        case 1:

          this.game.initGame(cli);

          if(this.game.playAgain(cli)){
            showMenu();
          }else{
            game.quit(cli);
          };

          break;


        case 2:

          System.out.println("Enter \"1\" to play against another Player or \"2\" to play against the Computer or \"3\" to watch Computer playing against Computer");

          try {

            int type = cli.askForIntegerBetweenMinAndMax(1,3);

            this.game.createPlayers(type);

            if (type == 1 || type == 2) {
              cli.printMessage("You are playing against " + game.getPlayer2().getName());//notifies the player
            }

            showMenu();

          } catch (IllegalArgumentException ex) {
            cli.printMessage(ex.getMessage());
            showOptions(choiceEntry, cli);

          } catch (InputMismatchException ex) {
            cli.printMessage(ex.getMessage());
            showOptions(choiceEntry, cli);
          }

          break;


        case 3:

          if (game.getPlayer2() instanceof Computer && !(game.getPlayer1() instanceof Computer)) {//when human against the Computer, won't show to change name for Computer.
            //when playing computer against computer, wont show to change name for Computers.
            if(game.getPlayer1().changeName(cli, game.getPlayer2())){
              showMenu();
            }else{
              showOptions(choiceEntry, cli);
            };

          } else {//when playing against player, you can change both names
            cli.printMessage("Enter \"1\" to change " + game.getPlayer1().getName() + "'s name or \"2\" to change " + game.getPlayer2().getName() + "'s name");

            try {
              int num = cli.askForIntegerBetweenMinAndMax(1, 2);

              if (num == 1) {
                if(game.getPlayer1().changeName(cli, game.getPlayer2())){
                  showMenu();
                }else{
                  showOptions(choiceEntry, cli);
                };
              } else {
                if(game.getPlayer2().changeName(cli, game.getPlayer1())){
                  showMenu();
                }else{
                  showOptions(choiceEntry, cli);
                };

              }

            } catch (IllegalArgumentException ex) {
              cli.printMessage(ex.getMessage());
              showOptions(choiceEntry, cli);

            } catch (InputMismatchException ex) {
              cli.printMessage(ex.getMessage());
              showOptions(choiceEntry, cli);
            }
          }

          break;

        case 4:
            if (game.getPlayer2() instanceof Computer && !(game.getPlayer1() instanceof Computer)) {
              game.getPlayer1().changeSymbol(cli, game.getPlayer2());

              showMenu();

            } else {
              cli.printMessage("Enter \"1\" to change " + game.getPlayer1().getName() + "'s symbol or \"2\" to change " + game.getPlayer2().getName() + "'s symbol");

              try {
                int num = cli.askForIntegerBetweenMinAndMax(1, 2);

                if (num == 1) {
                  if(game.getPlayer1().changeSymbol(cli, game.getPlayer2())){
                    showMenu();
                  }else{
                    showOptions(choiceEntry, cli);
                  };
                } else {
                  if(game.getPlayer2().changeSymbol(cli, game.getPlayer1())){
                    showMenu();
                  }else{
                    showOptions(choiceEntry, cli);
                  };
                }

              } catch (IllegalArgumentException ex) {
                cli.printMessage(ex.getMessage());
                showOptions(choiceEntry, cli);

              } catch (InputMismatchException ex) {
                cli.printMessage(ex.getMessage());
                showOptions(choiceEntry, cli);
              }
            }

          break;

       case 5:
          game.quit(cli);
          break;

      }
  }



}
