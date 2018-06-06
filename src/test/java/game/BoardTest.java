package game;

import org.junit.Test;
import players.Computer;
import players.Human;
import players.HumanTest;
import players.Player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BoardTest {

  Board board = new Board();

  Player peter = new Human("Peter", "P");
  Player computer = new Computer("Computer", "X");

  @Test
  public void setNewValueOfACell() {

    assertThat((board.getCell(0).getValue()), is(" "));

    board.setCell(0, "P");

    assertThat((board.getCell(0).getValue()), is("P"));
  }

  @Test
  public void checkThatPlayerHasALineHorizontal() {

    board.setCell(0, "P");
    board.setCell(1, "P");
    board.setCell(2, "P");

    assertThat((board.checkLinePlayer(peter)), is(true));

  }

  @Test
  public void checkThatPlayerHasALineVertical() {

    board.setCell(1, "P");
    board.setCell(4, "P");
    board.setCell(7, "P");

    assertThat((board.checkLinePlayer(peter)), is(true));

  }

  @Test
  public void checkThatPlayerHasALineDiagonal() {

    board.setCell(2, "P");
    board.setCell(4, "P");
    board.setCell(6, "P");

    assertThat((board.checkLinePlayer(peter)), is(true));

  }

  @Test
  public void checkThatPlayerHasntALine() {

    board.setCell(0, "P");
    board.setCell(2, "P");

    assertThat((board.checkLinePlayer(peter)), is(false));

  }

  @Test
  public void ThreeInLineHorizontalPlayer1() {

    board.setCell(0, "P");
    board.setCell(1, "P");
    board.setCell(2, "P");

    assertThat((board.threeInLine(peter, computer)), is(true));

  }

  @Test
  public void ThreeInLineVerticalPlayer1() {

    board.setCell(0, "P");
    board.setCell(3, "P");
    board.setCell(6, "P");

    assertThat((board.threeInLine(peter, computer)), is(true));

  }

  @Test
  public void ThreeInLineDiagonalPlayer2() {

    board.setCell(0, "X");
    board.setCell(4, "X");
    board.setCell(8, "X");

    assertThat((board.threeInLine(peter, computer)), is(true));

  }

  @Test
  public void FilledWithThreeInLine() {

    board.setCell(0, "X");
    board.setCell(1, "P");
    board.setCell(2, "X");

    board.setCell(3, "P");
    board.setCell(4, "X");
    board.setCell(5, "P");

    board.setCell(6, "X");
    board.setCell(7, "P");
    board.setCell(8, "X");

    assertThat((board.tie(peter, computer)), is(false));

  }

  @Test
  public void FilledTie() {

    board.setCell(0, "X");
    board.setCell(1, "P");
    board.setCell(2, "P");

    board.setCell(3, "P");
    board.setCell(4, "X");
    board.setCell(5, "X");

    board.setCell(6, "X");
    board.setCell(7, "P");
    board.setCell(8, "P");

    assertThat((board.tie(peter, computer)), is(true));

  }

}
