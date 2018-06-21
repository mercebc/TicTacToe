package players;

import com.Cli;
import game.Board;
import game.Borders;
import game.Mapping;
import game.State;
import game.Turn;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.toIntExact;

public class Computer extends Player{

  public Computer(String name, String symbol) {
    super(name, symbol);//calls parent class constructor
  }

  //overwrite the method in player


  public int getSpot(Board board, Player player1, Player player2, Cli cli) {

    Player opponent;
    Integer foundBestMove = -1;

    if (this == player1) {
      opponent = player2;
    } else {
      opponent = player1;
    }

    Turn turn = new Turn(board, this, opponent);
    State state = turn.getBoardState();
    Mapping map = turn.getMapping();


    cli.printMessage(this.getName() + " moves:\n");//notify the user that is the Computer turn

    foundBestMove = getBestMove(foundBestMove, state.getAvailableSpaces(), board,  opponent,  this);

    if (foundBestMove > -1 && foundBestMove < board.getCapacity() ) {

      return foundBestMove;//return spot if it's between 0-8 (Found)

    } else {

      switch (toIntExact(turn.getTurn())){

        case 0://computer moves first
          return getRandomNum(state.getCorners());//get random corner

        case 1:
          if(state.getCountOppEdges()==1){//put it in corner next to the edge
            for (int i = 0; i < state.getEdge().getCells().size(); i++) {
              if (state.getEdge().getCells().get(i).getValue().equals(opponent.getSymbol())) {
                int num = nearSpotEmpty(i, state.getAvailableSpaces(), state.getCorner());
                return state.getCorner().getPosition(num);
              }
            }
           }else if(state.getCountOppCorners()==1){
            return map.getMappingCenter();

          }else if (state.getOppCenter()){
            return getRandomNum(state.getCorners());
          }
          break;

        case 2:
          if(state.getCountOppEdges()==1){
            return map.getMappingCenter();
          }
          else if(state.getCountOppCorners()==1){ //put in any other free corner
            for (int i = 0; i < state.getCorner().getCells().size(); i++) {
              if (state.getAvailableSpaces().contains(state.getCorner().getPosition(i))) {
                return state.getCorner().getPosition(i);
              }
            }
          }
          else if (state.getOppCenter()){//put in diagonal corner
            for (int i = 0; i < state.getCorner().getCells().size(); i++) {
              if (state.getCorner().getCells().get(i).getValue().equals(this.getSymbol())) {
                return map.getMappingDiagonal(i);
              }
            }
          }
          break;

        case 3:
          if(state.getCountOppCorners()==2){ //put in any other free edge
            for (int i = 0; i < state.getEdge().getCells().size(); i++) {
              if (state.getAvailableSpaces().contains(state.getEdge().getPosition(i))) {
                return state.getEdge().getPosition(i);
              }
            }
          }else if(state.getCountOppCorners()==1 && state.getCountOppEdges()==1 && state.getMyCenter()){//put it in corner next to the edge of the opponent
            for (int i = 0; i < state.getEdge().getCells().size(); i++) {
              if (state.getEdge().getCells().get(i).getValue().equals(opponent.getSymbol())) {
                return state.getCorner().getPosition(nearSpotEmpty(i, state.getAvailableSpaces(), state.getCorner()));//
              }
            }

          }else if(!state.getMyCenter() && !state.getOppCenter()){//put it in center if it's free
            return map.getMappingCenter();
          }
          break;

        case 4:
          if(state.getCountMyCorners() == 1 && state.getMyCenter()){//opposite corner with no opponent symbol between
            for (int i = 0; i < state.getCorner().getCells().size(); i++) {
              if (state.getCorner().getCells().get(i).getValue().equals(this.getSymbol())) {

                int num = nearSpotEmpty(i, state.getAvailableSpaces(), state.getCorner());
                return state.getCorner().getPosition(nearSpotEmpty(num, state.getAvailableSpaces(), state.getCorner()));

              }
            }
          }else if(state.getCountMyCorners()==2){ //put in any other free corner
            for (int i = 0; i < state.getCorner().getCells().size(); i++) {
              if (state.getAvailableSpaces().contains(state.getCorner().getPosition(i))) {
                return state.getCorner().getPosition(i);
              }
            }
          }
          break;

        default:
          return getRandomNum(state.getAvailableSpaces());

      }
    }
    return getRandomNum(state.getAvailableSpaces());
  }

  private int getBestMove(int foundBestMove, List<Integer> availableSpaces,Board board, Player opponent, Player currentPlayer){

    for (Integer spot: availableSpaces) {//loop through the array created

      board.setCell(spot,opponent.getSymbol());
      //temporary set the spots available with the opponent player's symbol

      if (board.threeInLine(currentPlayer,opponent)) {//check if the opponent would do three in line
        foundBestMove = spot; //if so, get that spot to avoid that
      }

      else {
        board.setCell(spot,currentPlayer.getSymbol()); //temporary set the spots available with Computer's symbol

        if (board.threeInLine(currentPlayer,opponent)) {//check if Computer would do three in line
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

  private int nearSpotEmpty(int i, List<Integer> availableSpaces, Borders list){
    switch (i) {
      case 0:
        for (int j = 0; j < 2; j++) {
          if (availableSpaces.contains(list.getPosition(j))) {
            return j;
          }
        }
      case 1:
        for (int j = 0; j < 3; j = j + 2) {
          if (availableSpaces.contains(list.getPosition(j))) {
            return j;
          }
        }
      case 2:
        for (int j = 1; j < 4; j = j + 2) {
          if (availableSpaces.contains(list.getPosition(j))) {
            return j;
          }
        }
      case 3:
        for (int j = 2; j < 4; j++) {
          if (availableSpaces.contains(list.getPosition(j))) {
            return j;
          }
        }
      default:
        return getRandomNum(availableSpaces);
    }
  }

}