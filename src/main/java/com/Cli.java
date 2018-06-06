package com;

import game.Game;
import players.Computer;
import players.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cli {

    Game game;
    Player player1;
    Player player2;
    Player currentPlayer;

    Scanner input = new Scanner(System.in); // the input Scanner

    int choiceEntry = 0;
    boolean validInput = false;

  public Cli(Game game) {

    this.game = game;
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
        if (input.hasNextInt()) {//check that the input is an integer
          choiceEntry = input.nextInt();
          validEntry = true;
          showOptions(choiceEntry);
        } else {
          throw new InputMismatchException("You can only input integers");
        }

        if (choiceEntry < 1 || choiceEntry > maxvalue) {//check that the input is an integer between 1 and (2 or 4)
          throw new IllegalArgumentException("You can only input an integer between 1 and " + maxvalue);
        }
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

          if(this.game.playAgain()){
            showOptions(1);
          };

          break;


        case 2:
          System.out.println("Enter \"1\" to play against another Player or \"2\" to play against the Computer or \"3\" to watch Computer playing against Computer");
          try {
            int type;
            if (input.hasNextInt()) {
              type = input.nextInt();
            } else {
              throw new InputMismatchException("You can only input integers");
            }

            this.game.createPlayers(type);

            if (type == 1 || type == 2) {
              System.out.println("You are playing against " + player2.getName());//notifies the player
            }

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
            int num;
            try {
              if (input.hasNextInt()) {
                num = input.nextInt();
              } else {
                throw new InputMismatchException("You can only input integers");
              }

              if (num == 1) {
                validInput = player1.changeName(player2);//Returns true if has been successfully executed
              } else if (num == 2) {
                validInput = player2.changeName(player1);
              } else {
                throw new IllegalArgumentException("You can only input an integer between 1 and 2");
              }
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
            int num;
            try {
              if (input.hasNextInt()) {
                num = input.nextInt();
              } else {
                throw new InputMismatchException("You can only input integers");
              }

              if (num == 1) {
                validInput = player1.changeSymbol(player2);
              } else if (num == 2) {
                validInput = player2.changeSymbol(player1);
              } else {
                throw new IllegalArgumentException("You can only input an integer between 1 and 2");
              }
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

  public int askForIntegerBetweenMinAndMax(int min, int max){
    int num;

    if (input.hasNextInt()) {
      num = input.nextInt();
    } else {
      throw new InputMismatchException("You can only input integers");
    }

    if (num < min && num > max) {
      return num;
    } else {
      throw new IllegalArgumentException("You can only input an integer between" + min + " and " + max);
    }

  }


  public int askForIntegerOrHelpBetweenMinAndMax(int min, int max){
    int spot = 0;
    String help;

    if (input.hasNextInt()) {
      spot = input.nextInt();//assigns to spot the inserted value. Only if it's a number

      if (spot < min && spot > max) {
        return spot;
      } else {
        throw new IllegalArgumentException("You can only input an integer between" + min + " and " + max);
      }

    } else if (input.hasNext()) {

      help = input.next();//assigns to help the inserted value. If it isn't a number.

      if (help.equals("h")) {//if the input equals "h" a note is displayed to help the user understand how the game works

        System.out.println("The following numbers are the position your symbol will be placed.");
        System.out.println(" 1 | 2 | 3 " + "\n===+===+===\n" + " 4 | 5 | 6 " + "\n===+===+===\n" + " 7 | 8 | 9 ");

        return spot;

      } else {//if it isn't a "h", throw an exception
        throw new InputMismatchException("You can only input integers or \"h\" for help.");
      }

    } else {
      throw new InputMismatchException("You can only input integers or \"h\" for help.");
    }

  }


}