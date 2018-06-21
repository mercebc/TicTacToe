package players;

import com.Cli;
import game.Board;
import game.Turn;
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
  Player opponent = new Human("Simon", "X");
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

    corners = new ArrayList<>();
    corners.add(0);
    corners.add(2);
    corners.add(6);
    corners.add(8);

  }

  @Test
  public void RandomCorner() {

    board = new Board();

    assertThat (corners, hasItem(current.getSpot(board, current, opponent, cli)));
  }

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

    assertThat (corners, hasItem(current.getSpot(board, current, opponent, cli)));
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
  public void  ThirdTurn_PlaceRandomAvailableSpaces() {

    board = new Board();
      board.setCell(1,"X");
      board.setCell(4,"X");
      board.setCell(6,"O");

    availableSpaces = new ArrayList<>();

    for (int i = 0; i < board.getCapacity(); i++) {
      if (!board.getCell(i).getValue().equals(current.getSymbol()) && !board.getCell(i).getValue().equals(opponent.getSymbol())) {
        availableSpaces.add(i);
      }
    }

    assertThat (availableSpaces, hasItem(current.getSpot(board, current, opponent, cli))); //random available spaces
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

  @Test
  public void FourthTurn_CurrentPlaceCornerIfTwoMyCorners() {

    board = new Board();
    board.setCell(0,"O");
    board.setCell(2,"O");
    board.setCell(1,"X");
    board.setCell(8,"X");

    assertThat (current.getSpot(board, current, opponent, cli), is(6));

  }


  @Test
  public void FourthTurn_CurrentPlaceRandomElse() {

    board = new Board();
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

    assertThat (availableSpaces, hasItem(current.getSpot(board, current, opponent, cli))); //random available spaces
  }



}