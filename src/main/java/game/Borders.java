package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Borders {
  HashMap<Integer, Integer> position;
  private List<Cell> cells;

  public Borders(Board board){

  }

  public List<Cell> getCells() {
    return cells;
  }

  public int getPosition(int i){
    return position.get(i);
  }
}
