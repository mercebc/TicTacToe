package game;

import com.Cli;
import org.junit.Test;
import players.Human;

import players.Player;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LineTest {

  private final Cell empty = new Cell();

  //Double braces
  private final Cell j = new Cell() {//anonymous subclass. Dont have a constructor
    {//initialization of the instance of the subclass. Almost the same as the constructor
      this.setValue("J");
    }

  };
  private final Cell o = new Cell(){
    {
      this.setValue("O");
    }
  };

  Cli cli = new Cli(System.in, System.out);
  Player john = new Human("John", "J", cli);

  @Test
  public void anEmptyLineHasntWon(){

    ArrayList<Cell> myCells = new ArrayList<Cell>() {{
      add(empty);
      add(empty);
      add(empty);
    }};

    Line line = new Line(myCells);

    assertThat(line.allLine(john), is(false));
  }

  @Test
  public void playerWinsALine() {

    ArrayList<Cell> myCells = new ArrayList<Cell>() {{
      add(j);
      add(j);
      add(j);
    }};

    Line line = new Line(myCells);

    assertThat(line.allLine(john), is(true));
  }

  @Test
  public void playerLoseALine() {
    ArrayList<Cell> myCells = new ArrayList<Cell>() {{
      add(o);
      add(o);
      add(o);
    }};

    Line line = new Line(myCells);

    assertThat(line.allLine(john), is(false));
  }

  @Test
  public void playerDontHaveALineYet() {
    ArrayList<Cell> myCells = new ArrayList<Cell>() {{
      add(o);
      add(j);
      add(empty);
    }};

    Line line = new Line(myCells);

    assertThat(line.allLine(john), is(false));
  }

  @Test
  public void ClaimingTheLastCellWinsTheLine() {
    Cell cell = new Cell();
    ArrayList<Cell> myCells = new ArrayList<Cell>() {{
      add(cell);
      add(j);
      add(j);
    }};

    Line line = new Line(myCells);

    assertThat(line.allLine(john), is(false));

    cell.setValue("J");

    assertThat(line.allLine(john), is(true));
  }

  @Test
  public void CellsSymbolBelongsToPlayer() {

    assertThat(j.belongsTo(john), is(true));
  }

  @Test
  public void CellsSymbolDoesntBelongToPlayer() {

    assertThat(o.belongsTo(john), is(false));
  }


}
