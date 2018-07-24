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
  Board board = new Board(3);
  int spot = 0;

  private Player john;
  private Player tom;


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

    Cli cli = mockCli("UserInput");
    tom = new Human("Tom", "T", cli);
  }

  @Test
  public void TrimsSymbolIfHasMoreThan1Char() {
    Cli cli = mockCli("PO");
    john = new Human("John", "J" ,cli);
    john.changeSymbol(tom);
    assertThat (john.getSymbol(), is("P"));
  }

  @Test
  public void CantChangeSymbolItsTheSameOneAsTheOpponent() {
    Cli cli = mockCli("T");
    john = new Human("John", "J" ,cli);
    john.changeSymbol(tom);
    assertThat(out.toString(), containsString("The symbol you are trying to change is the same"));
    assertThat (john.getSymbol(), is("J"));
  }

  @Test
  public void ChangeSymbolItsDifferentThanTheOpponent() {
    Cli cli = mockCli("S");
    john = new Human("John", "J" ,cli);
    john.changeSymbol(tom);
    assertThat (john.getSymbol(), is("S"));
  }

  @Test
  public void CantChangeNameItsTheSameOneAsTheOpponent() {
    Cli cli = mockCli("Tom");
    john = new Human("John", "J" ,cli);
    john.changeName(tom);
    assertThat(out.toString(), containsString("The name you are trying to change is the same"));
    assertThat ((john.getName()), is("John"));
  }

  @Test
  public void ChangeNameItsDifferentThanTheOpponent() {
    Cli cli = mockCli("Sophie");
    john = new Human("John", "J" ,cli);
    john.changeName(tom);
    assertThat ((john.getName()), is("Sophie"));
  }

  @Test
  public void getSpotHumanValidated() {
    Cli cli = mockCli("2");
    spot = cli.askForIntegerOrHelpBetweenMinAndMax(1, 9, board) - 1;

    cli = mockCli("2");
    john = new Human("John", "J" ,cli);

    assertThat(john.getSpot(board, tom), is(spot) );

  }

  @Test
  public void spotLetter() {
    Cli cli = mockCli("p\np\n2");
    john = new Human("John", "J" ,cli);
    john.getSpot(board, tom);

    assertThat(out.toString(), containsString("You can only input integers"));
  }

  @Test
  public void spotGreaterThanMax() {
    Cli cli = mockCli("10\n1");
    john = new Human("John", "J" ,cli);
    john.getSpot(board, tom);

    assertThat(out.toString(), containsString("You can only input an integer between"));
  }


  @Test
  public void numberAlreadyUsed() {

    board.setCell(7,"J");//position 7 in the board equals to 8 position for the user input

    Cli cli = mockCli("8\n1");
    john = new Human("John", "J" ,cli);
    john.getSpot(board, tom);

    assertThat(out.toString(), containsString("Enter a number that is not already used"));
  }

}

