package players;

import com.Cli;
import game.Board;
import game.Cell;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class HumanTest {
  Player john = new Human("John", "J");
  Player tom = new Human("Tom", "T");
  Board board = new Board();
  int spot = 0;

  private ByteArrayOutputStream out;
  private PrintStream output;


  private Cli mockCli(String mockInput) {
    ByteArrayInputStream input = new ByteArrayInputStream(mockInput.getBytes());

    return new Cli(input, output);
  }


  @Before
  public void setUp() {
    out = new ByteArrayOutputStream();
    output = new PrintStream(out);
  }

  @Test
  public void TrimsSymbolIfHasMoreThan1Char() {
    Cli cli = mockCli("PO");
    john.changeSymbol(cli, tom);
    assertThat (john.getSymbol(), is("P"));
  }

  @Test
  public void CantChangeSymbolItsTheSameOneAsTheOpponent() {
    Cli cli = mockCli("T");
    john.changeSymbol(cli, tom);
    assertThat(out.toString(), containsString("The symbol you are trying to change is the same"));
    assertThat (john.getSymbol(), is("J"));
  }

  @Test
  public void ChangeSymbolItsDifferentThanTheOpponent() {
    Cli cli = mockCli("S");
    john.changeSymbol(cli, tom);
    assertThat (john.getSymbol(), is("S"));
  }

  @Test
  public void CantChangeNameItsTheSameOneAsTheOpponent() {
    Cli cli = mockCli("Tom");
    john.changeName(cli, tom);
    assertThat(out.toString(), containsString("The name you are trying to change is the same"));
    assertThat ((john.getName()), is("John"));
  }

  @Test
  public void ChangeNameItsDifferentThanTheOpponent() {
    Cli cli = mockCli("Sophie");
    john.changeName(cli, tom);
    assertThat ((john.getName()), is("Sophie"));
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void CantChangeSymbolItsSpace() {
    Cli cli = mockCli(" ");
    thrown.expect(NoSuchElementException.class);
    thrown.expectMessage(containsString("You can only input characters."));

    john.changeSymbol(cli, tom);
}

  @Test
  public void CantChangeNameItsSpace() {
    Cli cli = mockCli(" ");
    thrown.expect(NoSuchElementException.class);
    thrown.expectMessage(containsString("You can only input characters."));

    john.changeName(cli, tom);
 }

  @Test
  public void getSpotHumanValidated() {

    Cli cli = mockCli("2");

    spot = cli.askForIntegerOrHelpBetweenMinAndMax(1, 9) - 1;

    Cli cli1 = mockCli("2");

    assertThat(john.getSpot(board, john, tom, cli1), is(spot) );

  }

  @Test
  public void spotLetter() {
    Cli cli = mockCli("p\n2");
    john.getSpot(board, john, tom, cli);

    assertThat(out.toString(), containsString("You can only input integers"));
  }

  @Test
  public void spotGreaterThanMax() {
    Cli cli = mockCli("10\n1");
    john.getSpot(board, john, tom, cli);

    assertThat(out.toString(), containsString("You can only input an integer between"));
  }

}