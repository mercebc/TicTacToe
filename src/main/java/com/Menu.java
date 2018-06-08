package com;

import game.Game;
import players.Computer;
import players.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

  Game game;
  Cli cli;

  Player player1;
  Player player2;
  Player currentPlayer;

  Scanner input = new Scanner(System.in);

  int choiceEntry = 0;
  boolean validInput = false;

  public Menu(Game game, Cli cli) {

    this.game = game;
    this.cli = cli;
    this.player1 = game.getPlayer1();
    this.player2 = game.getPlayer2();
    this.currentPlayer = game.getCurrentPlayer();
  }

  public void showMenu(){

    boolean validEntry = false;
    int maxvalue;

    do {
      if (player1 instanceof Computer) {
        System.out.println("Enter \"1\" to watch a computer battle, \"2\" to Choose game mode");
        maxvalue = 2;
      } else {
        System.out.println("Enter \"1\" to Play, \"2\" to Choose game mode, \"3\" to Change names or \"4\" to Change symbols");
        maxvalue = 4;
      }
      //Validate the input
      try {

        showOptions(cli.askForIntegerBetweenMinAndMax(1,maxvalue));
        validEntry = true;

      } catch (IllegalArgumentException ex) {//catch the exceptions
        System.out.println(ex.getMessage());
        validEntry = false; //to loop

      } catch (InputMismatchException ex) {
        System.out.println(ex.getMessage());
        input.next();//to lose the value as it is not valid
        validEntry = false;
      }
    } while (!validEntry);//keeps looping until validEntry is valid(true)

  }


  public void showOptions(int choiceEntry){
    do {
      switch (choiceEntry) {

        case 1:
          validInput = true;

          this.game.initGame();

          if(this.game.playAgain(cli.askForIntegerBetweenMinAndMax(1,2))){
            showOptions(1);
          };

          break;


        case 2:
          System.out.println("Enter \"1\" to play against another Player or \"2\" to play against the Computer or \"3\" to watch Computer playing against Computer");
          try {

            int type = cli.askForIntegerBetweenMinAndMax(1,3);

            this.game.createPlayers(type);

            if (type == 1 || type == 2) {
              System.out.println("You are playing against " + player2.getName());//notifies the player
            }

            validInput = true;

            showMenu();

          } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            validInput = false;

          } catch (InputMismatchException ex) {
            System.out.println(ex.getMessage());
            validInput = false;
            input.next();
          }

          break;

        case 3:
          if (player2 instanceof Computer && !(player1 instanceof Computer)) {//when playing against the Computer, won't show to change name for Computer.
            //when playing computer against computer, wont show to change name for Computers.
            validInput = player1.changeName(player2);

          } else if (player1 instanceof Computer && player2 instanceof Computer) {
            System.out.println("You can't change the name of the Computer");//the code allows you to change it as it has a method for it, but I'm not implementing it in my game
            validInput = true;

          } else {//when playing against player, you can change both names
            System.out.println("Enter \"1\" to change " + player1.getName() + "'s name or \"2\" to change " + player2.getName() + "'s name");

            try {
              int num = cli.askForIntegerBetweenMinAndMax(1,2);

              if (num == 1) {
                validInput = player1.changeName(player2);//Returns true if has been successfully executed
              } else {
                validInput = player2.changeName(player1);
              }

              showMenu();

            } catch (IllegalArgumentException ex) {
              System.out.println(ex.getMessage());
              validInput = false;

            } catch (InputMismatchException ex) {
              System.out.println(ex.getMessage());
              validInput = false;
              input.next();
            }

          }
          break;

        case 4:
          if (player2 instanceof Computer) {
            validInput = player1.changeSymbol(player2);

          } else if (player1 instanceof Computer && player2 instanceof Computer) {
            System.out.println("You can't change the symbol of the Computer");//the code allows you to change it as it has a method for it, but I'm not implementing it in my game
            validInput = true;

          } else {
            System.out.println("Enter \"1\" to change " + player1.getName() + "'s symbol or \"2\" to change " + player2.getName() + "'s symbol");

            try {
              int num = cli.askForIntegerBetweenMinAndMax(1,2);

              if (num == 1) {
                validInput = player1.changeSymbol(player2);
              } else {
                validInput = player2.changeSymbol(player1);
              }

              showMenu();

            } catch (IllegalArgumentException ex) {
              System.out.println(ex.getMessage());
              validInput = false;

            } catch (InputMismatchException ex) {
              System.out.println(ex.getMessage());
              validInput = false;
              input.next();
            }
          }
          break;

      }

    } while (!validInput) ;

  }



}
