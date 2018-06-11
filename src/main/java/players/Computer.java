package players;

import com.Cli;
import game.Board;
import game.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.toIntExact;

public class Computer extends Player{

  public Computer(String name, String symbol) {
    super(name, symbol);//calls parent class constructor
  }

  //overwrite the method in player

  public int getSpot(Board board, Player player1, Player player2, Player currentPlayer, Cli cli) {

    List<Integer> availableSpaces = new ArrayList<Integer>();
    Player opponent;
    Integer foundBestMove = -1;


    for (int i = 0; i < board.getCapacity(); i++) {
      if (!board.getCell(i).equals(player1.getSymbol()) && !board.getCell(i).equals(player2.getSymbol())) {
        availableSpaces.add(i);
      }
    }

    if (currentPlayer == player1) {
      opponent = player2;
    } else {
      opponent = player1;
    }

    List<Integer> corners = new ArrayList<Integer>();
    corners.add(0);
    corners.add(2);
    corners.add(6);
    corners.add(8);

    Cell center = board.getCell(4);

    //create an arraylist with values in the corners of the board
    List<Cell> corner = new ArrayList<>();
    corner.add(board.getCell(0));
    corner.add(board.getCell(2));
    corner.add(board.getCell(6));
    corner.add(board.getCell(8));

    //create an arraylist with values in the edges of the board
    List<Cell> edge = new ArrayList<>();
    edge.add(board.getCell(1));
    edge.add(board.getCell(3));
    edge.add(board.getCell(5));
    edge.add(board.getCell(7));

    long myCorners = corner.stream().filter(x -> x.equals(this.getSymbol())).count();//count how many symbols computer has placed in the corners

    long oppCorners = corner.stream().filter(x -> x.equals(opponent.getSymbol())).count();//count how many symbols your opponent has placed in the corners

    long oppEdges = edge.stream().filter(x -> x.equals(opponent.getSymbol())).count();//count how many symbols your opponent has placed in the edges


    boolean oppCenter = center.equals(opponent.getSymbol());//true if opponent is in the center

    boolean myCenter = center.equals(this.getSymbol());//true if computer is in the center

    System.out.print(currentPlayer.getName() + " moves:\n");//notify the user that is the Computer turn


    foundBestMove = getBestMove(foundBestMove, availableSpaces, board,  player1,  player2,  opponent,  currentPlayer);

    if (foundBestMove > -1 && foundBestMove < board.getCapacity() ) {

      return foundBestMove;//return spot if it's between 0-8 (Found)

    } else {
      //get the turn of the game
      long turn = board.getCells().stream().filter(x -> x.equals(player1.getSymbol()) || x.equals(player2.getSymbol())).count();

      switch (toIntExact(turn)){
        case 0://computer moves first
          return getRandomNum(corners);//get random corner

        case 1:
          if(oppEdges==1){//put it in corner next to the edge
            return getSpotNextToList(opponent, edge, corner, corner, edge, availableSpaces);
          }else if(oppCorners==1){
            return 4;
          }else if (oppCenter){
            return getRandomNum(corners);
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
          }else if (oppCenter){//put in diagonal corner
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
          }else if(oppCorners==1 && oppEdges==1 && myCenter){//put it in corner next to the edge of the opponent
            return getSpotNextToList(opponent, edge, corner, corner, edge, availableSpaces);
          }else if(!myCenter && !oppCenter){//put it in center if it's free
            return 4;
          }
          else{
            return getRandomNum(availableSpaces);
          }

        case 4:
          if(myCorners == 1 && myCenter){//opposite corner with no opponent symbol between
            for (int i = 0; i < corner.size(); i++) {
              if (corner.get(i).equals(this.getSymbol())) {
                int num =  getSpotNextToList(opponent, corner, edge, corner, edge, availableSpaces);

                return getNextSpot(num, opponent,edge, corner, corner, edge, availableSpaces);

              }
            }
            return getRandomNum(availableSpaces);

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

  private int getBestMove(int foundBestMove, List<Integer> availableSpaces,Board board, Player player1, Player player2, Player opponent, Player currentPlayer){

    for (Integer spot: availableSpaces) {//loop through the array created

      board.setCell(spot,opponent.getSymbol());
      //temporary set the spots available with the opponent player's symbol

      if (board.threeInLine(player1,player2)) {//check if the opponent would do three in line
        foundBestMove = spot; //if so, get that spot to avoid that
      }

      else {
        board.setCell(spot,currentPlayer.getSymbol()); //temporary set the spots available with Computer's symbol

        if (board.threeInLine(player1,player2)) {//check if Computer would do three in line
          foundBestMove = spot;//if so, take that spot to win
        }
      }

      board.setCell(spot," ");//leave the board as it was
    }

    return foundBestMove;
  }


  private int getRandomNum(List<Integer> availableSpaces) {
    int n = ThreadLocalRandom.current().nextInt(0, availableSpaces.size());
    return availableSpaces.get(n);
  }

//gets the spot near the spot i of the list
  private int getSpotNextToList(Player opponent, List<Cell> listBelong, List<Cell> listNear, List<Cell> corner, List<Cell> edge, List<Integer> availableSpaces) {
    for (int i = 0; i < listBelong.size(); i++) {
      if (listBelong.get(i).equals(opponent.getSymbol())) {
        return getNextSpot(i, opponent,listNear, listBelong, corner, edge, availableSpaces);
      }
    }
    return getRandomNum(availableSpaces);
  }


//gets the spot near the spot i
  private int getNextSpot(int i, Player opponent, List<Cell> currentList, List<Cell> otherList, List<Cell> corner, List<Cell> edge, List<Integer> availableSpaces){
    switch (i) {
      case 0:
        for (int j = 0; j < 2; j++) {
          if (!otherList.get(j).equals(this.getSymbol()) && !otherList.get(j).equals(opponent.getSymbol())) {
            return getSpotMapping(i, currentList, corner, edge);
          }
        }
      case 1:
        for (int j = 0; j < 3; j = j + 2) {
          if (!otherList.get(j).equals(this.getSymbol()) && !otherList.get(j).equals(opponent.getSymbol())) {
            return getSpotMapping(i, currentList, corner, edge);
          }
        }
      case 2:
        for (int j = 1; j < 4; j = j + 2) {
          if (!otherList.get(j).equals(this.getSymbol()) && !otherList.get(j).equals(opponent.getSymbol())) {
            return getSpotMapping(i, currentList, corner, edge);
          }
        }
      case 3:
        for (int j = 2; j < 4; j++) {
          if (!otherList.get(j).equals(this.getSymbol()) && !otherList.get(j).equals(opponent.getSymbol())) {
            return getSpotMapping(i, currentList, corner, edge);
          }
        }
      default:
          return getRandomNum(availableSpaces);
    }

  }

  //get the mapping with the lists of corner and edge and the spots of the board
  private int getSpotMapping(int i,List<Cell> mappingList, List<Cell> corner, List<Cell> edge) {

    HashMap<Integer, Integer> mapCorner = new HashMap<Integer, Integer>();

    mapCorner.put(0, 0);
    mapCorner.put(1, 2);
    mapCorner.put(2, 6);
    mapCorner.put(3, 8);

    HashMap<Integer, Integer> mapEdge = new HashMap<Integer, Integer>();

    mapEdge.put(0, 1);
    mapEdge.put(1, 3);
    mapEdge.put(2, 5);
    mapEdge.put(3, 7);


    if (Arrays.deepEquals(mappingList.toArray(), corner.toArray())) {
      return mapCorner.get(i);

    } else if (Arrays.deepEquals(mappingList.toArray(), edge.toArray())) {
      return mapEdge.get(i);
    }
    else{
      return -1;
    }
  }

}