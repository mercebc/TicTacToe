package game;

import players.Player;
import positions.Center;
import positions.Corner;
import positions.Diagonal;
import positions.Edge;

import java.util.ArrayList;
import java.util.List;

public class State{

  private List<Integer> corners = new ArrayList<>();
  private Corner corner;
  private Edge edge;
  private Center center;
  private Diagonal diagonal;
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

    corner = new Corner(board);
    edge = new Edge(board);
    center = new Center();
    diagonal = new Diagonal();

    countMyCorners = corner.getCells().stream().filter(x -> x.belongsTo(current)).count();//count how many symbols computer has placed in the corners
    countOppCorners = corner.getCells().stream().filter(x -> x.belongsTo(opponent)).count();//count how many symbols your opponent has placed in the corners
    countOppEdges = edge.getCells().stream().filter(x -> x.belongsTo(opponent)).count();//count how many symbols your opponent has placed in the edges

    oppCenter = board.getCell(4).belongsTo(opponent);//true if opponent is in the center
    myCenter = board.getCell(4).belongsTo(current);//true if computer is in the center

    for (int i = 0; i < board.getCapacity(); i++) {
      if (!board.getCell(i).belongsTo(current) && !board.getCell(i).belongsTo(opponent)) {
        availableSpaces.add(i);
      }
    }

  }

  public Corner getCorner() {
    return corner;
  }

  public Edge getEdge() {
    return edge;
  }

  public Center getCenter() {
    return center;
  }

  public Diagonal getDiagonal() {
    return diagonal;
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

