package positions;

import game.Cell;

import java.util.List;

public interface Borders {

  public List<Cell> getCells();

  public int getPosition(int i);
}
