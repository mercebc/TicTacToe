package com.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.tictactoe.Main.nextPlayer;
import static com.tictactoe.Main.threeInLine;

public class Computer extends Player{

  public Computer(String name, String symbol) {
    super(name, symbol);//calls parent class constructor
  }

  //overwrite the method in player

  public int getSpot(List<String> board, Player player1, Player player2, Player currentPlayer) {

    Player opponent = nextPlayer();

    System.out.print(currentPlayer.getName() + " moves:\n");//notify the user that is the Computer turn
    Integer foundBestMove = -1;

    //create an arraylist with the spaces available
    List<Integer> availableSpaces = new ArrayList<Integer>();
    for (int i = 0; i < board.size(); i++) {
      if (board.get(i) != player1.getSymbol() && board.get(i) != player2.getSymbol()) {
        availableSpaces.add(i);
      }
    }

    for (Integer spot: availableSpaces) {//loop through the array created

      board.set(spot,opponent.getSymbol());
      //temporary set the spots available with the opponent player's symbol

      if (threeInLine()) {//check if the opponent would do three in line
        foundBestMove = spot; //if so, get that spot to avoid that
      }

      else {
        board.set(spot,currentPlayer.getSymbol()); //temporary set the spots available with Computer's symbol

        if (threeInLine()) {//check if Computer would do three in line
          foundBestMove = spot;//if so, take that spot to win
        }
      }

      board.set(spot," ");//leave the board as it was
    }

    if (foundBestMove > -1 && foundBestMove < 9 ) {

      return foundBestMove;//return spot if it's between 0-8 (Found)

    } else {
      String center = board.get(5);
      //create an arraylist with values in the corners of the board
      List<String> corner = new ArrayList<String>();
      corner.add(board.get(1));
      corner.add(board.get(3));
      corner.add(board.get(7));
      corner.add(board.get(9));

      List<String> edge = new ArrayList<String>();
      edge.add(board.get(2));
      edge.add(board.get(4));
      edge.add(board.get(6));
      edge.add(board.get(8));

      boolean corners = corner.stream().anyMatch(x -> x.equals(player1.getSymbol()) || x.equals(player2.getSymbol()));//check 4 corners if there are any symbols
      long youCorners = corner.stream().filter(x -> x.equals(this.getSymbol())).distinct().count();//count how many symbols you have placed in the corners
      long oppCorners = corner.stream().filter(x -> x.equals(opponent.getSymbol())).distinct().count();//count how many symbols your opponent has placed in the corners

      boolean edges = edge.stream().anyMatch(x -> x.equals(player1.getSymbol()) || x.equals(player2.getSymbol()));//check 4 edges if there are any symbols


      if(!corners && !edges){
        return 7;
      }else if(oppCorners==1 && !edges){
        return 5;
      }

      if(youCorners==1 && !center.equals(opponent.getSymbol())){
          if(board.get(1).equals(opponent.getSymbol())){
            return 9;
        }else{
          return 1;
        }
      }


      if(edges && !corners) {

      }


      int n = ThreadLocalRandom.current().nextInt(0, availableSpaces.size());
      return availableSpaces.get(n);
    }
  }

}