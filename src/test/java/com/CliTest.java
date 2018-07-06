package com;

import game.Board;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import players.Human;
import players.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class CliTest {

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
  public void AnnounceTieIfTieGame() {
    Cli cli = mockCli("UserInput");
    cli.announceTie();
    assertThat(out.toString(), containsString("Ohh there's no winner, it's a tie!"));
  }

  @Test
  public void AnnounceIfCheckLine() {
    Player john = new Human("John", "J");

    Cli cli = mockCli("UserInput");
    cli.announceWinner(john);
    assertThat(out.toString(), containsString("Congratulations! The winner is " + john.getName()));
  }

  @Test
  public void betweenNumbers() {

    Cli cli = mockCli("5");

    assertThat(cli.askForIntegerBetweenMinAndMax(1, 7), is(5));

  }

  @Test
  public void betweenNumbersOrHelpisNumber() {

    Cli cli = mockCli("6");

    assertThat(cli.askForIntegerOrHelpBetweenMinAndMax(1, 7), is(6));

  }

  @Test
  public void betweenNumbersOrHelpisHelp() {

    Cli cli = mockCli("h");

    cli.askForIntegerOrHelpBetweenMinAndMax(1, 7);

    assertThat(out.toString(), containsString("The following numbers are the position your symbol will be placed."));
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void UserInpuALetter() {
    Cli cli = mockCli("p");
    thrown.expect(InputMismatchException.class);
    thrown.expectMessage(containsString("You can only input integers"));

    cli.askForIntegerBetweenMinAndMax(1, 7);

  }

  @Test
  public void userInputHigherThanMax() {
    Cli cli = mockCli("9");
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(containsString("You can only input an integer between"));

    cli.askForIntegerBetweenMinAndMax(1, 7);

  }

  @Test
  public void userInputALetterDifferentThanH() {
    Cli cli = mockCli("p");
    thrown.expect(InputMismatchException.class);
    thrown.expectMessage(containsString("You can only input integers or"));

    cli.askForIntegerOrHelpBetweenMinAndMax(1, 4);

  }

  @Test
  public void userInputLowerThanMin() {
    Cli cli = mockCli("1");
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(containsString("You can only input an integer between"));

    cli.askForIntegerOrHelpBetweenMinAndMax(2, 4);
  }

  @Test
  public void printBoard() {
    Board board = new Board();
    Cli cli = mockCli("UserInput");
    board.setCell(2,"X");
    board.setCell(6,"X");
    board.setCell(8,"X");

    board.setCell(3,"O");
    board.setCell(5,"O");

    cli.printBoard(board);

    assertThat(out.toString(), containsString("   |   | X"+
                                                      "\n===+===+===\n"+
                                                        " O |   | O"+
                                                      "\n===+===+===\n"+
                                                        " X |   | X\n")
    );


  }

  @Test
  public void PrintsMessage() {
    Cli cli = mockCli("UserInput");
    cli.printMessage("Printed");

    assertThat(out.toString(), containsString("Printed"));
  }
}
