package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Corner extends Borders {

  private HashMap<Integer, Integer> position;
  private List<Cell> cells;

  public Corner(Board board){
    super(board);

    cells = new ArrayList<>();
    position = new HashMap<Integer, Integer>();

    cells.add(board.getCell(0));//arraylist with values in the corners of the board
    cells.add(board.getCell(2));
    cells.add(board.getCell(6));
    cells.add(board.getCell(8));

    position.put(0, 0);
    position.put(1, 2);
    position.put(2, 6);
    position.put(3, 8);

  }

  public List<Cell> getCells() {
    return cells;
  }

  public int getPosition(int i){
    return position.get(i);
  }

}
