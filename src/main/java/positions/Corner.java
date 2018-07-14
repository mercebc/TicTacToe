package positions;

import game.Board;
import game.Cell;
import positions.Borders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Corner implements Borders {

  private HashMap<Integer, Integer> position;
  private List<Cell> cells;

  public Corner(Board board){

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
