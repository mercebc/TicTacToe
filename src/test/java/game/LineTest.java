package game;

import org.junit.Test;
import players.Human;
import players.Player;
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
  Player john = new Human("John", "J");

  @Test
  public void anEmptyLineHasntWon(){
    Line line = new Line(empty, empty, empty);

    assertThat(line.allLine(john), is(false));
  }

  @Test
  public void playerWinsALine() {
    Line line = new Line(j, j, j);

    assertThat(line.allLine(john), is(true));
  }

  @Test
  public void playerLoseALine() {
    Line line = new Line(o, o, o);

    assertThat(line.allLine(john), is(false));
  }

  @Test
  public void playerDontHaveALineYet() {
    Line line = new Line(o, j, empty);

    assertThat(line.allLine(john), is(false));
  }

  @Test
  public void ClaimingTheLastCellWinsTheLine() {
    Cell cell = new Cell();
    Line line = new Line(cell, j,j);

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
