package game;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BoardTest {


  @Test
  public void setNewValueOfACell() {
    Board board = new Board();

    assertThat((board.getCell(0).getValue()), is(" "));

    board.setCell(0, "P");

    assertThat((board.getCell(0).getValue()), is("P"));
  }



}
