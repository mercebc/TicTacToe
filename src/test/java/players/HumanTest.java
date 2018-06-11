package players;

import com.Cli;
import game.Board;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HumanTest {
  Player john = new Human("John", "J");
  Player tom = new Human("Tom", "T");

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

  @Test
  public void getSpotHumanValidated() {

    Board board = new Board();
    Player player1 = new Human("Mary", "M");
    Player player2 = new Computer("Computer", "X");
    Player currentPlayer = player1;

    int spot = 0;

    Cli cli = mockCli("2");

    spot = cli.askForIntegerOrHelpBetweenMinAndMax(1, 9) - 1;

    Cli cli1 = mockCli("2");

    assertThat(currentPlayer.getSpot(board, player1, player2, currentPlayer, cli1), is(spot) );

  }

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