package game;

import players.Player;

import java.util.ArrayList;
import java.util.List;

public class State{

  private List<Integer> corners = new ArrayList<>();
  private List<Cell> corner = new ArrayList<>();
  private List<Cell> edge = new ArrayList<>();
  private List<Integer> availableSpaces = new ArrayList<>();


  private long countMyCorners;
  private long countOppCorners;
  private long countOppEdges;

  private boolean oppCenter;
  private boolean myCenter;

  public State(Board board, Player current, Player opponent) {
    corners.add(0);
    corners.add(2);
    corners.add(6);
    corners.add(8);

    corner.add(board.getCell(0));//arraylist with values in the corners of the board
    corner.add(board.getCell(2));
    corner.add(board.getCell(6));
    corner.add(board.getCell(8));

    edge.add(board.getCell(1));//arraylist with values in the edges of the board
    edge.add(board.getCell(3));
    edge.add(board.getCell(5));
    edge.add(board.getCell(7));

    countMyCorners = corner.stream().filter(x -> x.getValue().equals(current.getSymbol())).count();//count how many symbols computer has placed in the corners
    countOppCorners = corner.stream().filter(x -> x.getValue().equals(opponent.getSymbol())).count();//count how many symbols your opponent has placed in the corners
    countOppEdges = edge.stream().filter(x -> x.getValue().equals(opponent.getSymbol())).count();//count how many symbols your opponent has placed in the edges

    oppCenter = board.getCell(4).getValue().equals(opponent.getSymbol());//true if opponent is in the center
    myCenter = board.getCell(4).getValue().equals(current.getSymbol());//true if computer is in the center

    for (int i = 0; i < board.getCapacity(); i++) {
      if (!board.getCell(i).getValue().equals(current.getSymbol()) && !board.getCell(i).getValue().equals(opponent.getSymbol())) {
        availableSpaces.add(i);
      }
    }

  }

  public List<Cell> getCorner() {
    return corner;
  }

  public List<Cell> getEdge() {
    return edge;
  }

  public List<Integer> getAvailableSpaces() {
    return availableSpaces;
  }

  public List<Integer> getCorners() {
    return corners;
  }

  public long getCountMyCorners() {
    return countMyCorners;
  }

  public long getCountOppCorners() {
    return countOppCorners;
  }

  public long getCountOppEdges() {
    return countOppEdges;
  }

  public boolean getOppCenter() {
    return oppCenter;
  }

  public boolean getMyCenter() {
    return myCenter;
  }

}

