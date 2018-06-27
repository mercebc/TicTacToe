package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface Borders {
  HashMap<Integer, Integer> position = null;
  List<Cell> cells = null;


  public List<Cell> getCells();

  public int getPosition(int i);
}
