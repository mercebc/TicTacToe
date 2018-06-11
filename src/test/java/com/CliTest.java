package com;

import org.junit.Before;
import org.junit.Test;
import players.Human;
import players.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;

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
    String output = out.toString();
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

}
