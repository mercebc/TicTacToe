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
    assertThat (john.changeSymbol("Tom", tom), is(false));
  }

  @Test
  public void ChangeNameItsDifferentThanTheOpponent() {
    assertThat (john.changeSymbol("Sophie", tom), is(true));
  }


}

//testValidateSpot

//testGetSpot