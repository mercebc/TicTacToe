package com.tictactoe;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Tic-Tac-Toe: TWo-player console version.
 */
public class Main {
  // The game board
  public static List<String> board = new ArrayList<>();

  public static Scanner input = new Scanner(System.in); // the input Scanner

  public static Player player1;
  public static Player player2;
  public static Player currentPlayer; // the current player
  public static Player winner;

  /** The entry main method (the program starts here) */
  public static void main(String[] args) {
    // Initialize the players
    initGame();

  //MENU
    int choiceEntry = 0;

    while(choiceEntry > -1 && choiceEntry < 5) {
      boolean validEntry = false;
      boolean validInput = false;
      int maxvalue;

      do {
        if (player1 instanceof Computer) {
          System.out.println("Enter \"1\" to watch a computer battle, \"2\" to Choose game mode");
          maxvalue = 2;
        }else{
          System.out.println("Enter \"1\" to Play, \"2\" to Choose game mode, \"3\" to Change names or \"4\" to Change symbols");
          maxvalue = 4;
        }
        //Validate the input
        try {
          if (input.hasNextInt()) {//check that the input is an integer
            choiceEntry = input.nextInt();
            validEntry = true;
          } else {
            throw new InputMismatchException("You can only input integers");
          }

          if (choiceEntry < 1 || choiceEntry > maxvalue) {//check that the input is an integer between 1 and (2 or 4)
            throw new IllegalArgumentException("You can only input an integer between 1 and 4");
          }
        } catch (IllegalArgumentException ex) {//catch the exceptions
          System.out.println(ex.getMessage());
          validEntry = false; //to loop
        } catch (InputMismatchException ex) {
          System.out.println(ex.getMessage());
          input.next();//to lose the value as it is not valid
          validEntry = false;
        }
      }while (!validEntry);//keeps looping until validEntry is valid(true)

      do {
        switch (choiceEntry) {

          case 1:
            validInput = true;
            startNewGame();
            do {
              if (!threeInLine() && !tie()) {
                evalBoard();
              }
            } while (!threeInLine() && !tie()); // repeat if not game-over

            if(threeInLine()){
              System.out.println("Congratulations! The winner is " + winner.getName()); //announces the winner
            }
            else{
              System.out.println("Game over\n"); //there's no winner
            }
            choiceEntry = 0;//to show menu when game is over
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

              if (type == 1) {
                player1 = new Human("Player 1", "X");//creates a new Human player
                player2 = new Human("Player 2", "O");//creates a new Human player
                validInput = true;
              } else if (type == 2) {
                player1 = new Human("Player 1", "X");//creates a new Human player
                player2 = new Computer("Computer", "O");//creates a new Computer player
                validInput = true;
              } else if (type == 3) {
                player1 = new Computer("Computer1", "X");//creates a new Computer player
                player2 = new Computer("Computer2", "O");//creates a new Computer player
                validInput = true;
              }else {
                throw new IllegalArgumentException("You can only input an integer between 1 and 3");
              }

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

            } else if(player1 instanceof Computer && player2 instanceof Computer){
              System.out.println("You can't change the name of the Computer");//the code allows you to change it as it has a method for it, but I'm not implementing it in my game
              validInput = true;

            }else {//when playing against player, you can change both names
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

            } else if(player1 instanceof Computer && player2 instanceof Computer){
              System.out.println("You can't change the symbol of the Computer");//the code allows you to change it as it has a method for it, but I'm not implementing it in my game
              validInput = true;

            }else {
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

      } while (!validInput);
    }
  }


  /** Initializes the game with player against computer. This is the default game if players haven't been changed */
  public static void initGame() {
    player1 = new Human("Player 1", "X");
    player2 = new Computer("Computer", "O");
  }

  public static void startNewGame() {

    System.out.println("Enter \"1\" for " + player1.getName() + " to start or \"2\" for " + player2.getName() + " to start");//Player choose who starts the game
    int num;
    try {
      if (input.hasNextInt()) {
        num = input.nextInt();
      } else {
        throw new InputMismatchException("You can only input integers");
      }

      if (num == 1) {
        currentPlayer = player1;//currentplayer is the player who is currently playing and, in this case, who starts the game
      } else if (num == 2) {
        currentPlayer = player2;
      } else {
        throw new IllegalArgumentException("You can only input an integer between 1 and 2");
      }
    } catch (IllegalArgumentException ex) {
      System.out.println(ex.getMessage());
      startNewGame();//recursive call when there is an exception
    } catch (InputMismatchException ex) {
      System.out.println(ex.getMessage());
      input.next();
      startNewGame();
    }

      board.clear();//clear the board to be able to play more than once
      board.add(" ");//add spaced cells to the board, to not get null exceptions and to have a consistent design of the board
      board.add(" ");
      board.add(" ");
      board.add(" ");
      board.add(" ");
      board.add(" ");
      board.add(" ");
      board.add(" ");
      board.add(" ");

  }

  public static Player nextPlayer() {//change the player that is playing
    if (currentPlayer == player1) {
      return player2;
    } else {
      return player1;
    }
  }

  public static void evalBoard() {
    int spot = currentPlayer.getSpot(board, player1, player2, currentPlayer);//get the spot of the player, Human and Computer have different methods for this
    if (!board.get(spot).equals(player1.getSymbol()) && !board.get(spot).equals(player2.getSymbol())) {//check the spot is already taken
      board.set(spot,currentPlayer.getSymbol()); //set the symbol to the spot chosen (Human) or calculated (Computer)
      currentPlayer = nextPlayer(); //change players
      printBoard();
    } else {
      System.out.println("Enter a number that is not already used");
    }
  }

  /** Return true if the game was just won */
  public static boolean threeInLine() {//check line by line if there's three in line
    return
    check(0,1,2) ||
    check(3,4,5) ||
    check(6,7,8) ||

    check(0,3,6) ||
    check(1,4,7) ||
    check(2,5,8) ||

    check(0,4,8) ||
    check(2,4,6);

  }

  private static boolean check(int i, int j, int k) {
    List<String> line = new ArrayList<>();//new array of the line that is being checked

    line.add(board.get(i));//add to the array the symbol of empty cell that is in the position sent
    line.add(board.get(j));
    line.add(board.get(k));

    if(line.stream().allMatch(t -> (t.contains(player1.getSymbol())))){//if in a line all the values are the symbol of player 1
      winner = player1; //set player one as the winner
      return line.stream().distinct().limit(2).count() <= 1; //return true if all the values are the same (double check)
    }
    else if(line.stream().allMatch(t -> (t.contains(player2.getSymbol())))){
      winner = player2;
      return line.stream().distinct().limit(2).count() <= 1;
    }
    else {
      return false;
    }

  }

  /** Return true if it is a draw (no more empty cell) */

  public static boolean tie() {
    return
     board.stream()
        .allMatch(t -> (t.contains(player1.getSymbol()) || t.contains(player2.getSymbol())));//return true if all the cells are symbol of player1 or symbol of player 2.
  }

  /** Print the game board */
  public static void printBoard() {
    System.out.println(" " + board.get(0) + " | " + board.get(1) + " | " + board.get(2) + "\n===+===+===\n" + " " + board.get(3)
    + " | " + board.get(4) + " | " + board.get(5) + "\n===+===+===\n" + " " + board.get(6) + " | " + board.get(7) + " | " + board.get(8) + "\n"); // print all the board cells
  }

}
