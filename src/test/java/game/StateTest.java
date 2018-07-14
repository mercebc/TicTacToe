package game;

import com.Cli;
import org.junit.Before;
import org.junit.Test;
import players.Computer;
import players.Human;
import players.Player;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StateTest {

  Board board = new Board();
  State state;

  Cli cli = new Cli(System.in, System.out);

  Player current = new Computer("Computer", "X");
  Player opponent = new Human("Jenny", "O", cli);

  private List<Integer> corners = new ArrayList<>();
  private List<Integer> availableSpaces = new ArrayList<>();

  private long countMyCorners;
  private long countOppCorners;
  private long countOppEdges;

  private boolean oppCenter;
  private boolean myCenter;

  @Before
  public void setUp() {

    board.setCell(0, "X");
    board.setCell(1, "O");
    board.setCell(3, "O");
    board.setCell(4, "O");
    board.setCell(7, "X");

    state = new State(board, current, opponent);

  }

  @Test
  public void currentHasOneSymbolCorner1(){
    assertThat(state.getCountMyCorners(), is(1L));
  }

  @Test
  public void currentHasOneSymbolCorner(){
    assertThat(state.getCountMyCorners(), is(1L));
  }

  @Test
  public void opponentHasNoneSymbolCorner() {
    assertThat(state.getCountOppCorners(), is(0L));
  }

  @Test
  public void opponentHasTwoSymbolEdge() {
    assertThat(state.getCountOppEdges(), is(2L));
  }

  @Test
  public void opponentHaveCenter() {
    assertThat(state.getOppCenter(), is(true));
  }

  @Test
  public void currentDoesntHasCenter() {
    assertThat(state.getMyCenter(), is(false));
  }

  @Test
  public void getListCellsEmpty() {
    assertThat(state.getAvailableSpaces().get(0), is(2));
    assertThat(state.getAvailableSpaces().get(1), is(5));
    assertThat(state.getAvailableSpaces().get(2), is(6));
    assertThat(state.getAvailableSpaces().get(3), is(8));
  }

  @Test
  public void getCornerPositions() {
    assertThat(state.getCorners().get(0), is(0));
    assertThat(state.getCorners().get(1), is(2));
    assertThat(state.getCorners().get(2), is(6));
    assertThat(state.getCorners().get(3), is(8));
  }

}

