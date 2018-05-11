package com.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.tictactoe.Main.nextPlayer;
import static com.tictactoe.Main.threeInLine;

public class Computer extends Player{

  public Computer(String name, String symbol) {
    super(name, symbol);
  }

  public int getSpot(List<String> board, Player player1, Player player2, Player currentPlayer) {

    Player opponent = nextPlayer();

    System.out.print(currentPlayer.getName() + " moves:\n");

    List<Integer> availableSpaces = new ArrayList<Integer>();

    Integer foundBestMove = -1;

    for (int i = 0; i < board.size(); i++) {
      if (board.get(i) != player1.getSymbol() && board.get(i) != player2.getSymbol()) {
        availableSpaces.add(i);
      }
    }

    for (Integer spot: availableSpaces) {

      board.set(spot,opponent.getSymbol());


      if (threeInLine()) {
        foundBestMove = spot;
      }

      else {
        board.set(spot,currentPlayer.getSymbol());

        if (threeInLine()) {
          foundBestMove = spot;
        }
      }

      board.set(spot," ");
    }

    if (foundBestMove > -1 && foundBestMove < 9 ) {

      return foundBestMove;

    } else {

      int n = ThreadLocalRandom.current().nextInt(0, availableSpaces.size());
      return availableSpaces.get(n);
    }
  }

}