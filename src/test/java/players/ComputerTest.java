package players;

import com.Cli;
import game.Board;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasItem;


public class ComputerTest {
  Board board;
  Player current = new Computer("Computer", "O");
  Player opponent;
  Cli cli;

  private ByteArrayOutputStream out;
  private ByteArrayInputStream input;
  private PrintStream output;

  List<Integer> corners;
  List<Integer> availableSpaces;

  @Before
  public void setUp() {
    out = new ByteArrayOutputStream();
    input = new ByteArrayInputStream("testingComputer".getBytes());
    output = new PrintStream(out);

    cli = new Cli(input, output);

    opponent = new Human("Simon", "X", cli);

    corners = new ArrayList<>();
    corners.add(0);
    corners.add(2);
    corners.add(6);
    corners.add(8);

    board = new Board(3);

  }

  @Test
  public void RandomCorner() {

    assertThat (corners, hasItem(current.getSpot(board, opponent)));
  }

  @Test
  public void FirstTurn_CurrentPlaceInCornerNextToTheOppEdge() {

    board.setCell(1,"X");

    assertThat (current.getSpot(board, opponent), is(0));

  }

  @Test
  public void FirstTurn_CurrentPlaceInMiddleIfOppCorner() {

    board.setCell(0,"X");

    assertThat (current.getSpot(board, opponent), is(4));

  }

  @Test
  public void FirstTurn_CurrentPlaceInRandomCornerIfOppMiddle() {

    board.setCell(4,"X");

    assertThat (corners, hasItem(current.getSpot(board, opponent)));
  }

  @Test
  public void SecondTurn_CurrentPlaceInMiddleIfOppEdge() {

    board.setCell(7,"X");
    board.setCell(0,"O");

    assertThat (current.getSpot(board, opponent), is(4));

  }

  @Test
  public void SecondTurn_CurrentPlaceInFreeCornerIfOppCornerMeCorner() {

    board.setCell(8,"X");
    board.setCell(0,"O");

    assertThat (current.getSpot(board, opponent), is(2));

  }

  @Test
  public void SecondTurn_CurrentPlaceInFreeCornerIfOppCornerMeOtherCorner() {

    board.setCell(2,"X");
    board.setCell(0,"O");

    assertThat (current.getSpot(board, opponent), is(6));

  }

  @Test
  public void SecondTurn_CurrentPlaceInOppositeCornerIfOppMiddleMeCorner() {

    board.setCell(4,"X");
    board.setCell(8,"O");

    assertThat (current.getSpot(board, opponent), is(0));

  }

  @Test
  public void ThirdTurn_CurrentPlaceInFreeEdgeIfOppTwoCorners() {

    board.setCell(0,"X");
    board.setCell(1,"O");
    board.setCell(2,"X");

    assertThat (current.getSpot(board, opponent), is(3));

  }

  @Test
  public void ThirdTurn_CurrentPlaceInFreeCornerNextOppEdge() {

    board.setCell(0,"X");
    board.setCell(4,"O");
    board.setCell(7,"X");

    assertThat (current.getSpot(board, opponent), is(6));

  }

  @Test
  public void ThirdTurn_CurrentPlaceInCenterIfFree() {//change name

    board.setCell(8,"X");
    board.setCell(2,"O");
    board.setCell(3,"X");

    assertThat (current.getSpot(board, opponent), is(4));

  }

  @Test
  public void  ThirdTurn_PlaceRandomAvailableSpaces() {

      board.setCell(1,"X");
      board.setCell(4,"X");
      board.setCell(6,"O");

    availableSpaces = new ArrayList<>();

    for (int i = 0; i < board.getCapacity(); i++) {
      if (!board.getCell(i).getValue().equals(current.getSymbol()) && !board.getCell(i).getValue().equals(opponent.getSymbol())) {
        availableSpaces.add(i);
      }
    }

    assertThat (availableSpaces, hasItem(current.getSpot(board, opponent))); //random available spaces
    }

  @Test
  public void FourthTurn_CurrentPlaceOppositeCornerNoSymbolBetween() {//change name

    board.setCell(0,"O");
    board.setCell(4,"O");
    board.setCell(1,"X");
    board.setCell(8,"X");

    assertThat (current.getSpot(board,opponent), is(6));

  }

  @Test
  public void FourthTurn_CurrentPlaceCornerIfTwoMyCorners() {

    board.setCell(0,"O");
    board.setCell(2,"O");
    board.setCell(1,"X");
    board.setCell(8,"X");

    assertThat (current.getSpot(board, opponent), is(6));

  }


  @Test
  public void FourthTurn_CurrentPlaceRandomElse() {

    board.setCell(0,"O");
    board.setCell(3,"O");
    board.setCell(1,"X");
    board.setCell(6,"X");

    availableSpaces = new ArrayList<>();

    for (int i = 0; i < board.getCapacity(); i++) {
      if (!board.getCell(i).getValue().equals(current.getSymbol()) && !board.getCell(i).getValue().equals(opponent.getSymbol())) {
        availableSpaces.add(i);
      }
    }

    assertThat (availableSpaces, hasItem(current.getSpot(board, opponent))); //random available spaces
  }


  @Test
  public void makeThreeInLine() {

    board.setCell(0,"O");
    board.setCell(7,"X");
    board.setCell(1,"O");
    board.setCell(5,"X");

    assertThat (current.getSpot(board, opponent), is(2));

  }

  @Test
  public void AvoidThreeInLine() {

    board.setCell(4,"O");
    board.setCell(5,"X");
    board.setCell(1,"O");
    board.setCell(3,"X");

    assertThat (current.getSpot(board, opponent), is(7));
  }

  @Test
  public void NearestSpotEmptyPositionOne() {

    board.setCell(1,"X");

    assertThat (current.getSpot(board, opponent), is(0));

  }

  @Test
  public void NearestSpotEmptyPositionThree() {

    board.setCell(3,"X");

    assertThat (current.getSpot(board, opponent), is(0));

  }

  @Test
  public void NearestSpotEmptyPositionFive() {

    board.setCell(5,"X");

    assertThat (current.getSpot(board, opponent), is(2));

  }

  @Test
  public void NearestSpotEmptyPositionSeven() {

    board.setCell(7,"X");

    assertThat (current.getSpot(board, opponent), is(6));

  }


}