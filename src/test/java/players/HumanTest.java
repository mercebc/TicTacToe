package players;

import com.Cli;
import game.Board;
import game.Cell;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

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
    assertThat (john.changeSymbol(cli, tom), is(false));
  }

  @Test
  public void ChangeSymbolItsDifferentThanTheOpponent() {
    Cli cli = mockCli("S");
    assertThat (john.changeSymbol(cli, tom), is(true));
  }

  @Test
  public void CantChangeNameItsTheSameOneAsTheOpponent() {
    Cli cli = mockCli("Tom");
    assertThat (john.changeName(cli, tom), is(false));
    assertThat ((john.getName()), is("John"));
  }

  @Test
  public void ChangeNameItsDifferentThanTheOpponent() {
    Cli cli = mockCli("Sophie");
    assertThat (john.changeName(cli, tom), is(true));
    assertThat ((john.getName()), is("Sophie"));
  }

  @Test
  public void getSpotHumanValidated() {

    Cli cli = mockCli("2");

    spot = cli.askForIntegerOrHelpBetweenMinAndMax(1, 9) - 1;

    Cli cli1 = mockCli("2");

    assertThat(john.getSpot(board, john, tom, cli1), is(spot) );

  }

//
//  @Test
//  public void SpotHumanNotValidated() {
//
//    board.setCell(2,"T");
//
//    Cli cli = mockCli("3");
//
//    john.getSpot(board, john, tom, cli);
//
//    assertThat(out.toString(), containsString("Enter a number that is not already used"));
//
//    Cli breakLoop = mockCli("2");
//
//  }

//  public int getSpot(Board board, Player player1, Player player2, Player currentPlayer) {
//
//    int spot = 0;
//    boolean validInput = false;
//
//    do {
//
//      String help = "";
//
//      System.out.print(currentPlayer.getName() + ", please enter a number between 1 and 9 to allocate your symbol. Enter \"h\" for help.\n");
//
//      try {
//
//        spot = cli.askForIntegerOrHelpBetweenMinAndMax(1, 9) - 1;
//
//        if(spot > 0) {
//          if (validateSpot(board, spot, player1, player2)) {
//            validInput = true;//input value is true as its an int 1-9
//          } else {
//            validInput = false;
//          }
//        }
//      }
//      catch (InputMismatchException ex) {
//        System.out.println(ex.getMessage());
//        validInput = false;
//      }
//
//      catch (IllegalArgumentException ex) {
//        System.out.println(ex.getMessage());
//        validInput = false;
//      }
//
//    } while(!validInput);
//
//    return spot;
//
//  }








}

//testValidateSpot

//testGetSpot