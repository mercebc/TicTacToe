package players;

import com.Cli;
import game.Board;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.contains;

public class ComputerTest {
  Board board;
  Player current = new Computer("Computer", "O");
  Player opponent = new Human("Simon", "X");
  Cli cli;

  private ByteArrayOutputStream out;
  private ByteArrayInputStream input;
  private PrintStream output;

  @Before
  public void setUp() {
    out = new ByteArrayOutputStream();
    input = new ByteArrayInputStream("testingComputer".getBytes());
    output = new PrintStream(out);

    cli = new Cli(input, output);
  }

//  @Test
//  public void RandomCorner() {
//
//    board = new Board();
//
//    List<Integer> corners = new ArrayList<>();
//    corners.add(0);
//    corners.add(2);
//    corners.add(6);
//    corners.add(8);
//
//    assertThat (corners, contains(current.getSpot(board, current, opponent, cli))); //random corner
//  }

  @Test
  public void FirstTurn_CurrentPlaceInCornerNextToTheOppEdge() {

    board = new Board();
    board.setCell(1,"X");

    assertThat (current.getSpot(board, current, opponent, cli), is(0));

  }

  @Test
  public void FirstTurn_CurrentPlaceInMiddleIfOppCorner() {

    board = new Board();
    board.setCell(0,"X");

    assertThat (current.getSpot(board, current, opponent, cli), is(4));

  }

  @Test
  public void FirstTurn_CurrentPlaceInRandomCornerIfOppMiddle() {

    board = new Board();
    board.setCell(4,"X");

    //assertThat (current.getSpot(board, current, opponent, cli), is(0,2,6,8)); //random corner

  }

  @Test
  public void SecondTurn_CurrentPlaceInMiddleIfOppEdge() {

    board = new Board();
    board.setCell(7,"X");
    board.setCell(0,"O");

    assertThat (current.getSpot(board, current, opponent, cli), is(4));

  }

  @Test
  public void SecondTurn_CurrentPlaceInFreeCornerIfOppCornerMeCorner() {

    board = new Board();
    board.setCell(8,"X");
    board.setCell(0,"O");

    assertThat (current.getSpot(board, current, opponent, cli), is(2));

  }

  @Test
  public void SecondTurn_CurrentPlaceInFreeCornerIfOppCornerMeOtherCorner() {

    board = new Board();
    board.setCell(2,"X");
    board.setCell(0,"O");

    assertThat (current.getSpot(board, current, opponent, cli), is(6));

  }

  @Test
  public void SecondTurn_CurrentPlaceInOppositeCornerIfOppMiddleMeCorner() {

    board = new Board();
    board.setCell(4,"X");
    board.setCell(8,"O");

    assertThat (current.getSpot(board, current, opponent, cli), is(0));

  }

  @Test
  public void ThirdTurn_CurrentPlaceInFreeEdgeIfOppTwoCorners() {

    board = new Board();
    board.setCell(0,"X");
    board.setCell(1,"O");
    board.setCell(2,"X");

    assertThat (current.getSpot(board, current, opponent, cli), is(3));

  }

  @Test
  public void ThirdTurn_CurrentPlaceInFreeCornerNextOppEdge() {

    board = new Board();
    board.setCell(0,"X");
    board.setCell(4,"O");
    board.setCell(7,"X");

    assertThat (current.getSpot(board, current, opponent, cli), is(6));

  }

  @Test
  public void ThirdTurn_CurrentPlaceInCenterIfFree() {//change name

    board = new Board();
    board.setCell(8,"X");
    board.setCell(2,"O");
    board.setCell(3,"X");

    assertThat (current.getSpot(board, current, opponent, cli), is(4));

  }

  @Test
  public void FourthTurn_CurrentPlaceOppositeCornerNoSymbolBetween() {//change name

    board = new Board();
    board.setCell(0,"O");
    board.setCell(4,"O");
    board.setCell(1,"X");
    board.setCell(8,"X");

    assertThat (current.getSpot(board, current, opponent, cli), is(6));

  }




}

//testGetSpot