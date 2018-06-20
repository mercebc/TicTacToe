package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Edge extends Borders{

  private HashMap<Integer, Integer> position;
  private List<Cell> cells;

  public Edge(Board board){
    super(board);

    cells = new ArrayList<>();
    position = new HashMap<Integer, Integer>();

    cells.add(board.getCell(1));//arraylist with values in the edges of the board
    cells.add(board.getCell(3));
    cells.add(board.getCell(5));
    cells.add(board.getCell(7));

    position.put(0, 1);
    position.put(1, 3);
    position.put(2, 5);
    position.put(3, 7);

  }

  public List<Cell> getCells() {
    return cells;
  }

  public int getPosition(int i){
    return position.get(i);
  }

}
