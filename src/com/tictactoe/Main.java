package com.tictactoe;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Tic-Tac-Toe: TWo-player console version.
 */
public class Main {
  // The game board and the game status
  public static List<String> board = new ArrayList<>();

  public static Scanner input = new Scanner(System.in); // the input Scanner

  public static Player player1;
  public static Player player2;
  public static Player currentPlayer; // the current player

  /** The entry main method (the program starts here) */
  public static void main(String[] args) {
    // Initialize the players
    initGame();

  //MENU
    int choiceEntry = 0;
    int type = 0;

    while(choiceEntry > -1 && choiceEntry < 5) {

      System.out.println("Enter \"1\" to Play, \"2\" to Choose opponent, \"3\" to Change names or \"4\" to Change symbols");

      try {
        if (input.hasNextInt()) {
          choiceEntry = input.nextInt();
        } else {
          throw new InputMismatchException("You can only input integers");
        }

        if (choiceEntry < 1 || choiceEntry > 4) {
          throw new IllegalArgumentException("You can only input an integer between 1 and 4");
        }
      } catch (IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
        choiceEntry = 0;
      } catch (InputMismatchException ex) {
        System.out.println(ex.getMessage());
        input.next();
      }

      boolean validInput = false;
      do {
        switch (choiceEntry) {

          case 1:
            startNewGame();
            do {
              if (!threeInLine() && !tie()) {
                evalBoard();
              }
            } while (!threeInLine() && !tie()); // repeat if not game-over
            System.out.print("Game over\n");
            break;


          case 2:
            System.out.println("Enter \"1\" to play against another Player or \"2\" to play against the Computer");
            try {
              if (input.hasNextInt()) {
                type = input.nextInt();
              } else {
                throw new InputMismatchException("You can only input integers");
              }

              if (type == 1) {
                player2 = new Human("Player 2", "O");
                validInput = true;
              } else if (type == 2) {
                player2 = new Computer("Computer", "O");
                validInput = true;
              } else {
                throw new IllegalArgumentException("You can only input an integer between 1 and 2");
              }
              System.out.println("You are playing against " + player2.getName());
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
            if (player2 instanceof Computer) {
              changeName(player1, player2);
              validInput = true;
            } else {
              System.out.println("Enter \"1\" to change " + player1.getName() + "'s name or \"2\" to change " + player2.getName() + "'s name");
              int num;
              try {
                if (input.hasNextInt()) {
                  num = input.nextInt();
                } else {
                  throw new InputMismatchException("You can only input integers");
                }

                if (num == 1) {
                  validInput = changeName(player1, player2);
                } else if (num == 2) {
                  validInput = changeName(player2, player1);
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
              changeSymbol(player1, player2);
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
                  validInput = changeSymbol(player1, player2);
                } else if (num == 2) {
                  validInput = changeSymbol(player2, player1);
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


  private static boolean changeName(Player player1, Player player2) {
    System.out.println("Enter new name for " + player1.getName());
    if (input.hasNext()) {
      String name = input.next();

      if (!(player2.getName().equals(name))) {
        player1.setName(name);
        System.out.println("Name changed to " + player1.getName());
        return true;
      }else{
        System.out.println("The name you are trying to change is the same one as your opponent's name: " + player2.getName());
        return false;
      }
    }
    return false;
  }

  private static boolean changeSymbol(Player player1, Player player2) {
    System.out.println("Enter new symbol for " + player1.getName());
    if (input.hasNext()) {
      String symbol = input.next();

      if (!(player2.getSymbol().equals(symbol))) {
        player1.setSymbol(symbol);
        System.out.println("Symbol changed to " + player1.getSymbol());
        return true;
      }else{
        System.out.println("The symbol you are trying to change is the same one as your opponent's symbol: " + player2.getName());
        return false;
      }
    }
    return false;
  }

  /** Initializes the game */
  public static void initGame() {
    player1 = new Human("Player 1", "X");
    player2 = new Computer("Computer", "O");
  }

  public static void startNewGame() {

    System.out.println("Enter \"1\" for " + player1.getName() + " to start or \"2\" for " + player2.getName() + " to start");
    if (input.hasNextInt()) {
      int num = input.nextInt();

      if (num == 1) {
        currentPlayer = player1;
      } else {
        currentPlayer = player2;
      }
      board.clear();
      board.add(" ");
      board.add(" ");
      board.add(" ");
      board.add(" ");
      board.add(" ");
      board.add(" ");
      board.add(" ");
      board.add(" ");
      board.add(" ");
    }
  }

  public static Player nextPlayer() {
    if (currentPlayer == player1) {
      return player2;
    } else {
      return player1;
    }
  }

  public static void evalBoard() {
    int spot = currentPlayer.getSpot(board, player1, player2, currentPlayer);
    if (board.get(spot) != player1.getSymbol() && board.get(spot) != player2.getSymbol()) {
      board.set(spot,currentPlayer.getSymbol());
      currentPlayer = nextPlayer();
      printBoard();
    } else {
      System.out.println("Enter a number that is not already used");
    }
  }

  /** Return true if the game was just won */
  public static boolean threeInLine() {
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
    List<String> temp = new ArrayList<>();

    temp.add(board.get(i));
    temp.add(board.get(j));
    temp.add(board.get(k));

    if(temp.stream().allMatch(t -> (t.contains(player1.getSymbol()) || t.contains(player2.getSymbol())))){
      return temp.stream().distinct().limit(2).count() <= 1;
    }
    else {
      return false;
    }

  }

  /** Return true if it is a draw (no more empty cell) */

  public static boolean tie() {
    return
     board.stream()
        .allMatch(t -> (t.contains(player1.getSymbol()) || t.contains(player2.getSymbol())));
  }

  /** Print the game board */
  public static void printBoard() {
    System.out.println(" " + board.get(0) + " | " + board.get(1) + " | " + board.get(2) + "\n===+===+===\n" + " " + board.get(3)
    + " | " + board.get(4) + " | " + board.get(5) + "\n===+===+===\n" + " " + board.get(6) + " | " + board.get(7) + " | " + board.get(8) + "\n"); // print all the board cells
  }

}
