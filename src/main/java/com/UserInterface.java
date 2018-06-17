package com;

import game.Board;
import players.Player;


public interface UserInterface {

  int askForIntegerBetweenMinAndMax(int min, int max);

  int askForIntegerOrHelpBetweenMinAndMax(int min, int max);

  String askForString();

  /** Announce winner */
  void announceWinner(Player winner);

  /** Announce ties */
  void announceTie();

  /** Print the game board */
  void printBoard(Board board) ;


  /** Messages */
  void printMessage(String message);

}
