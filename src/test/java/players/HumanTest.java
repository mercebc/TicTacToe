package players;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HumanTest {
  Player john = new Human("John", "J");
  Player tom = new Human("Tom", "T");

  @Test
  public void TrimsSymbolIfHasMoreThan1Char() {
    john.changeSymbol("PO", tom);
    assertThat (john.getSymbol(), is("P"));
  }

  @Test
  public void CantChangeSymbolItsTheSameOneAsTheOpponent() {
    assertThat (john.changeSymbol("T", tom), is(false));
  }

  @Test
  public void ChangeSymbolItsDifferentThanTheOpponent() {
    assertThat (john.changeSymbol("S", tom), is(true));
  }

  @Test
  public void CantChangeNameItsTheSameOneAsTheOpponent() {
    assertThat (john.changeName("Tom", tom), is(false));
    assertThat ((john.getName()), is("John"));
  }

  @Test
  public void ChangeNameItsDifferentThanTheOpponent() {
    assertThat (john.changeName("Sophie", tom), is(true));
    assertThat ((john.getName()), is("Sophie"));
  }


}

//testValidateSpot

//testGetSpot