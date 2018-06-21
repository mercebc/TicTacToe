package game;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EdgeTest {

  private HashMap<Integer, Integer> position;
  private List<Cell> cells;
  private Board board;

  private Edge edge;

  @Before
  public void setUp() throws Exception {

    board = new Board();

    board.setCell(1,"X");
    board.setCell(3,"O");

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

    edge = new Edge(board);

  }

  @Test
  public void getEdgePositions() {
    assertThat(edge.getPosition(0), is(1));
    assertThat(edge.getPosition(1), is(3));
    assertThat(edge.getPosition(2), is(5));
    assertThat(edge.getPosition(3), is(7));
  }


  @Test
  public void getListCellsEdge() {
    assertThat(edge.getCells().get(0).getValue(), is("X"));
    assertThat(edge.getCells().get(1).getValue(), is("O"));
    assertThat(edge.getCells().get(2).getValue(), is(" "));
    assertThat(edge.getCells().get(3).getValue(), is(" "));
  }

}
