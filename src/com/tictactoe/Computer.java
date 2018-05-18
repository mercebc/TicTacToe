package com.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.tictactoe.Main.nextPlayer;
import static com.tictactoe.Main.threeInLine;
import static java.lang.Math.toIntExact;

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
      if (!board.get(i).equals(player1.getSymbol()) && !board.get(i).equals(player2.getSymbol())) {
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
      long turn = board.stream().filter(x -> x.equals(player1.getSymbol()) || x.equals(player2.getSymbol())).count();

      String center = board.get(4);
      //create an arraylist with values in the corners of the board
      List<String> corner = new ArrayList<String>();
      corner.add(board.get(0));
      corner.add(board.get(2));
      corner.add(board.get(6));
      corner.add(board.get(8));

      List<String> edge = new ArrayList<String>();
      edge.add(board.get(1));
      edge.add(board.get(3));
      edge.add(board.get(5));
      edge.add(board.get(7));

      //boolean corners = corner.stream().anyMatch(x -> x.equals(player1.getSymbol()) || x.equals(player2.getSymbol()));//check 4 corners if there are any symbols
      long myCorners = corner.stream().filter(x -> x.equals(this.getSymbol())).count();//count how many symbols you have placed in the corners
      long oppCorners = corner.stream().filter(x -> x.equals(opponent.getSymbol())).count();//count how many symbols your opponent has placed in the corners
      //boolean edges = edge.stream().anyMatch(x -> x.equals(player1.getSymbol()) || x.equals(player2.getSymbol()));//check 4 edges if there are any symbols
      //long myEdges = edge.stream().filter(x -> x.equals(this.getSymbol())).count();//count how many symbols you have placed in the corners
      long oppEdges = edge.stream().filter(x -> x.equals(opponent.getSymbol())).count();//count how many symbols your opponent has placed in the corners

      boolean OppCenter = center.equals(opponent.getSymbol());
      boolean myCenter = center.equals(this.getSymbol());

      switch (toIntExact(turn)){
        case 0:
          return 6;

        case 1:
          if(oppEdges==1){//put it in corner next to the edge
            return getSpotNextToList(opponent, edge, corner, corner, edge);
          }else if(oppCorners==1){
            return 4;
          }else if (OppCenter){
            return 6;
          }

        case 2:
          if(oppEdges==1){
            return 4;
          }else if(oppCorners==1){ //put in any other free corner
            for (int i = 0; i < corner.size(); i++) {
              if (!corner.get(i).equals(this.getSymbol()) && !corner.get(i).equals(opponent.getSymbol())) {
                return getSpotMapping(i, corner, corner, edge);
              }
            }
          }else if (OppCenter){//put in diagonal corner
            for (int i = 0; i < corner.size(); i++) {
              if (corner.get(i).equals(this.getSymbol())) {
                switch (i){
                  case 0:
                    return 8;
                  case 1:
                    return 6;
                  case 2:
                    return 2;
                  case 3:
                    return 0;
                }
              }
            }
          }

        case 3:
          if(oppCorners==2){ //put in any other free edge
            for (int i = 0; i < edge.size(); i++) {
              if (!edge.get(i).equals(this.getSymbol()) && !edge.get(i).equals(opponent.getSymbol())) {
                return getSpotMapping(i, edge, corner, edge);
              }
            }
          }else if(oppCorners==1 && oppEdges==1){//put it in corner next to the edge
            return getSpotNextToList(opponent, edge, corner, corner, edge);
          }else{
            return getRandomNum(availableSpaces);
          }

        case 4:
          if(myCorners == 1 && myCenter){//opposite corner with no opponent symbol between
            for (int i = 0; i < corner.size(); i++) {
              if (corner.get(i).equals(this.getSymbol())) {
                int num =  getSpotNextToList(opponent, corner, edge, corner, edge);

                return getNextSpot(num, opponent,edge, corner, corner, edge);

              }
            }
            return -1;

          }else if(myCorners==2){ //put in any other free corner
            for (int i = 0; i < corner.size(); i++) {
              if (!corner.get(i).equals(this.getSymbol()) && !corner.get(i).equals(opponent.getSymbol())) {
                  return getSpotMapping(i, corner, corner, edge);
              }
            }
          }else{
          return getRandomNum(availableSpaces);
          }

        default:
          return getRandomNum(availableSpaces);

      }
    }
  }


  private int getRandomNum(List<Integer> availableSpaces) {
    int n = ThreadLocalRandom.current().nextInt(0, availableSpaces.size());
    return availableSpaces.get(n);
  }

  private int getSpotNextToList(Player opponent, List<String> listBelong, List<String> listNear, List<String> corner, List<String> edge) {
    for (int i = 0; i < listBelong.size(); i++) {
      if (listBelong.get(i).equals(opponent.getSymbol())) {
        return getNextSpot(i, opponent,listBelong, listNear, corner, edge);
      }
    }
    return -1;
  }



  private int getNextSpot(int i, Player opponent, List<String> listBelong, List<String> listNear, List<String> corner, List<String> edge){
    switch (i) {
      case 0:
        for (int j = 0; j < 2; j++) {
          if (!listNear.get(j).equals(this.getSymbol()) && !listNear.get(j).equals(opponent.getSymbol())) {
            return getSpotMapping(i, listBelong, corner, edge);
          }
        }
      case 1:
        for (int j = 0; j < 3; j = j + 2) {
          if (!listNear.get(j).equals(this.getSymbol()) && !listNear.get(j).equals(opponent.getSymbol())) {
            return getSpotMapping(i, listBelong, corner, edge);
          }
        }
      case 2:
        for (int j = 1; j < 4; j = j + 2) {
          if (!listNear.get(j).equals(this.getSymbol()) && !listNear.get(j).equals(opponent.getSymbol())) {
            return getSpotMapping(i, listBelong, corner, edge);
          }
        }
      case 3:
        for (int j = 2; j < 4; j++) {
          if (!listNear.get(j).equals(this.getSymbol()) && !listNear.get(j).equals(opponent.getSymbol())) {
            return getSpotMapping(i, listBelong, corner, edge);
          }
        }
        default:
          return -1;
    }

  }

  private int getSpotMapping(int i,List<String> listBelong, List<String> corner, List<String> edge) {
    if (Arrays.deepEquals(listBelong.toArray(), corner.toArray())) {
      switch (i) {
        case 0:
          return 0;
        case 1:
          return 2;
        case 2:
          return 6;
        case 3:
          return 8;
        default:
          return -1;
      }
    } else if (Arrays.deepEquals(listBelong.toArray(), edge.toArray())) {
      switch (i) {
        case 0:
          return 1;
        case 1:
          return 3;
        case 2:
          return 5;
        case 3:
          return 7;
        default:
          return -1;
      }
    }
    else{
      return -1;
    }
  }

}