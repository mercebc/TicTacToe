package game;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CornerTest {

  private HashMap<Integer, Integer> position;
  private List<Cell> cells;
  private Board board;

  private Corner corner;

  @Before
  public void setUp() throws Exception {

    board = new Board();

    board.setCell(2,"X");
    board.setCell(8,"O");

    cells = new ArrayList<>();
    position = new HashMap<Integer, Integer>();

    cells.add(board.getCell(0));//arraylist with values in the edges of the board
    cells.add(board.getCell(2));
    cells.add(board.getCell(6));
    cells.add(board.getCell(8));

    position.put(0, 0);
    position.put(1, 2);
    position.put(2, 6);
    position.put(3, 8);

    corner = new Corner(board);

  }

  @Test
  public void getCornerPositions() {
    assertThat(corner.getPosition(0), is(0));
    assertThat(corner.getPosition(1), is(2));
    assertThat(corner.getPosition(2), is(6));
    assertThat(corner.getPosition(3), is(8));
  }


  @Test
  public void getListCellscCorner() {
    assertThat(corner.getCells().get(0).getValue(), is(" "));
    assertThat(corner.getCells().get(1).getValue(), is("X"));
    assertThat(corner.getCells().get(2).getValue(), is(" "));
    assertThat(corner.getCells().get(3).getValue(), is("O"));
  }

}
