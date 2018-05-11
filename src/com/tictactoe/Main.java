package com.tictactoe;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Tic-Tac-Toe: TWo-player console version.
 */
public class Main {
  // The game board and the game status
  public static String[] board = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
  public static int currentState;  // the current state of the game

  public static Scanner input = new Scanner(System.in); // the input Scanner

  public static Scanner scanchoice = new Scanner(System.in);

  public static Player player1;
  public static Player player2;
  public static Player currentPlayer; // the current player

  /** The entry main method (the program starts here) */
  public static void main(String[] args) {
    // Initialize the game-board and current status
    initGame();
  //MENU
    int choiceEntry = 0;
    int type = 0;

    while(choiceEntry > -1 || choiceEntry < 5){

    System.out.println("Enter \"1\" to Play, \"2\" to Choose opponent, \"3\" to Change names or \"4\" to Change symbols");
    if(scanchoice.hasNextInt())
      choiceEntry = scanchoice.nextInt();

     switch(choiceEntry) {

       case 1:
         do {
           if (!gameIsOver() && !tie()) {
             evalBoard();
           }
         } while (!gameIsOver() && !tie()); // repeat if not game-over
         System.out.print("Game over\n");
         break;


       case 2:
         System.out.println("Enter \"1\" to play against another Player or \"2\" to play against the Computer");
         if (input.hasNextInt()) {
           type = input.nextInt();

           if (type == 1) {
             player2 = new Human("Player 2", "O");
           } else {
             player2 = new Computer("Computer", "O");
           }
         }
         break;

       case 3:
         if (player2 instanceof Computer) {
           System.out.println("Enter new name for "+ player1.getName());
           if (input.hasNext()) {
             String name = input.next();
             player1.setName(name);
           }
         } else {
           System.out.println("Enter \"1\" to change " + player1.getName() + "'s name or \"2\" to change " + player2.getName() + "'s name");
           if (input.hasNextInt()) {
             int num = input.nextInt();

             if (num == 1) {
               System.out.println("Enter new name for " + player1.getName());
               if (input.hasNext() && !(input.equals(player2.getName()))) {
                 String name = input.next();
                 player1.setName(name);

               }
             } else {
               System.out.println("Enter new name for " + player2.getName());
               if (input.hasNext() && !(input.equals(player1.getName()))) {
                 String name = input.next();
                 player2.setName(name);
               }
             }
           }
         }
         break;

       case 4:
         if (player2 instanceof Computer) {
           System.out.println("Enter new symbol for\"" + player1.getName());
           if (input.hasNext()) {
             String symbol = input.next();
             player1.setSymbol(symbol);
           }
         } else {
           System.out.println("Enter \"1\" to change " + player1.getName() + "'s symbol or \"2\" to change " + player2.getName() + "'s symbol");
           if (input.hasNextInt()) {
             int num = input.nextInt();

             if (num == 1) {
               System.out.println("Enter new symbol for " + player1.getName());
               if (input.hasNext() && !(input.equals(player2.getSymbol()))) {
                 String symbol = input.next();
                 player1.setSymbol(symbol);

               }
             } else {
               System.out.println("Enter new symbol for " + player2.getName());
               if (input.hasNext() && !(input.equals(player1.getSymbol()))) {
                 String symbol = input.next();
                 player2.setSymbol(symbol);
               }
             }
           }
         }
         break;

     }
  }


  }

  /** Initializes the game */
  public static void initGame() {
    player1 = new Human("Player 1", "X");
    player2 = new Computer("Computer", "O");
    currentPlayer = player1;  // cross plays first
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
    if (board[spot] != player1.getSymbol() && board[spot] != player2.getSymbol()) {
      board[spot] = currentPlayer.getSymbol();
      currentPlayer = nextPlayer();
      printBoard();
    }
  }

  /** Return true if the game was just won */
  public static boolean gameIsOver() {
    return board[0] == board[1] && board[1] == board[2] ||
        board[3] == board[4] && board[4] == board[5] ||
        board[6] == board[7] && board[7] == board[8] ||
        board[0] == board[3] && board[3] == board[6] ||
        board[1] == board[4] && board[4] == board[7] ||
        board[2] == board[5] && board[5] == board[8] ||
        board[0] == board[4] && board[4] == board[8] ||
        board[2] == board[4] && board[4] == board[6];
  }

  /** Return true if it is a draw (no more empty cell) */
  // TODO: maybe there is an easeir way to check this
  public static boolean tie() {
    return board[0] != "0" &&
        board[1] != "1" &&
        board[2] != "2" &&
        board[3] != "3" &&
        board[4] != "4" &&
        board[5] != "5" &&
        board[6] != "6" &&
        board[7] != "7" &&
        board[8] != "8";
  }

  /** Print the game board */
  public static void printBoard() {
    System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2] + "\n===+===+===\n" + " " + board[3] + " | " + board[4] + " | " + board[5] + "\n===+===+===\n" + " " + board[6] + " | " + board[7] + " | " + board[8] + "\n"); // print all the board cells
  }

}
