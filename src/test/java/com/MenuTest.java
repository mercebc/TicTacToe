package com;

import game.Game;
import org.junit.Before;
import org.junit.Test;
import players.Computer;
import players.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;

public class MenuTest {

  private ByteArrayOutputStream out;
  private PrintStream output;

  private Cli mockCli(String mockInput) {
    ByteArrayInputStream input = new ByteArrayInputStream(mockInput.getBytes());

    return new Cli(input, output);
  }

  Game game = new Game();

  Player player2 = game.getPlayer2();
  Player currentPlayer = game.getCurrentPlayer();

  @Before
  public void setUp() {
    out = new ByteArrayOutputStream();
    output = new PrintStream(out);
  }

  @Test
  public void showMenuEnterHigherThenOk() {
    Cli cli = mockCli("\n9\n2\n1\n5");
    Menu menu = new Menu(game, cli);

    menu.showMenu();

    assertThat(out.toString(), containsString("You can only input an integer between"));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
    }

  @Test
  public void showMenuEnterCharThenOk() {
    Cli cli = mockCli("\np\n2\n1\n5");
    Menu menu = new Menu(game, cli);

    menu.showMenu();

    assertThat(out.toString(), containsString("You can only input integers"));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));}

  @Test
  public void showMenuComputerComputerAndEnterValidValueAndTie() {
    Cli cli = mockCli("2\n3\n1\n1\n1");
    Menu menu = new Menu(game, cli);
    game.createPlayers(3);

    menu.showMenu();

    assertThat(out.toString(), containsString("to watch a computer battle"));
    assertThat(out.toString(), containsString("Ohh there's no winner, it's a tie!"));

  }

  @Test
  public void PlayAgain() {
    Cli cli = mockCli("2\n3\n1\n1\n2\n1\n1\n1");
    Menu menu = new Menu(game, cli);
    game.createPlayers(3);

    menu.showMenu();

    assertThat(out.toString(), containsString("to watch a computer battle"));
    assertThat(out.toString(), containsString("Ohh there's no winner, it's a tie!"));
    assertThat(out.toString(), containsString("to Play again"));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void chooseComputerOpponentHigherThenOkThenExit() {
    Cli cli = mockCli("2\n5\n1\n5");
    Menu menu = new Menu(game, cli);

    menu.showMenu();

    assertThat(out.toString(), containsString("You can only input an integer between"));
    assertThat(out.toString(), containsString("You are playing against " + game.getPlayer2().getName()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void choosePlayerOpponentCharThenOkThenExit() {
    Cli cli = mockCli("2\nkp\n1\n5");
    Menu menu = new Menu(game, cli);

    menu.showMenu();

    assertThat(out.toString(), containsString("You can only input integers"));
    assertThat(out.toString(), containsString("You are playing against " + game.getPlayer2().getName()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void changeNameHumanHumanHigherThenSameThenOk() {
    Cli cli = mockCli("3\n3\n2\nPlayer1\n2\nAngela\n5");
    Menu menu = new Menu(game, cli);
    game.createPlayers(1);

    menu.showMenu();

    assertThat(out.toString(), containsString("to change Player2's name"));
    assertThat(out.toString(), containsString("You can only input an integer between"));
    assertThat(out.toString(), containsString("The name you are trying to change is the same one as your opponent's name"));
    assertThat(out.toString(), containsString("Name changed to " + game.getPlayer2().getName()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void changeNameHumanHumanCharThenSameThenOk() {
    Cli cli = mockCli("3\np\n1\nPlayer2\n1\nAmelia\n5");
    Menu menu = new Menu(game, cli);
    game.createPlayers(1);

    menu.showMenu();

    assertThat(out.toString(), containsString("to change Player1's name"));
    assertThat(out.toString(), containsString("You can only input integers"));
    assertThat(out.toString(), containsString("The name you are trying to change is the same one as your opponent's name"));
    assertThat(out.toString(), containsString("Name changed to " + game.getPlayer1().getName()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void changeSymbolHumanHumanHigherThenSameThenTrim() {
    Cli cli = mockCli("4\n6\n2\nX\n2\nApple\n5");
    Menu menu = new Menu(game, cli);
    game.createPlayers(1);

    menu.showMenu();

    assertThat(out.toString(), containsString("to change " + game.getPlayer2().getName() +"'s symbol"));
    assertThat(out.toString(), containsString("You can only input an integer between"));
    assertThat(out.toString(), containsString("The symbol you are trying to change is the same one as your opponent's symbol"));
    assertThat(out.toString(), containsString("Note: We have trimmed your Symbol as it can only contain one character"));
    assertThat(out.toString(), containsString("Symbol changed to " + game.getPlayer2().getSymbol()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void changeSymbolHumanHumanCharThenSameThenOk() {
    Cli cli = mockCli("4\nl\n1\nD\n1\nM\n5");
    Menu menu = new Menu(game, cli);
    game.createPlayers(1);

    menu.showMenu();

    assertThat(out.toString(), containsString("to change " + game.getPlayer1().getName() +"'s symbol"));
    assertThat(out.toString(), containsString("You can only input integers"));
    assertThat(out.toString(), containsString("The symbol you are trying to change is the same one as your opponent's symbol"));
    assertThat(out.toString(), containsString("Symbol changed to " + game.getPlayer1().getSymbol()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void changeSymbolHumanComputer() {
    Cli cli = mockCli("4\nX\n5");
    Menu menu = new Menu(game, cli);

    menu.showMenu();

    assertThat(out.toString(), containsString("Symbol changed to " + game.getPlayer1().getSymbol()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void changeSymbolHumanComputerSameThenOk() {
    Cli cli = mockCli("4\nL\nM\n5");
    Menu menu = new Menu(game, cli);

    menu.showMenu();

    assertThat(out.toString(), containsString("The symbol you are trying to change is the same one as your opponent's symbol"));
    assertThat(out.toString(), containsString("Symbol changed to " + game.getPlayer1().getSymbol()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }


  @Test
  public void changeNameHumanComputer() {
    Cli cli = mockCli("3\nPeter\n5");
    Menu menu = new Menu(game, cli);

    menu.showMenu();

    assertThat(out.toString(), containsString("Name changed to " + game.getPlayer1().getName()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void changeNameHumanComputerSameThenOk() {
    Cli cli = mockCli("3\nComputer\nMary\n5");
    Menu menu = new Menu(game, cli);

    menu.showMenu();

    assertThat(out.toString(), containsString("The name you are trying to change is the same one as your opponent's name"));
    assertThat(out.toString(), containsString("Name changed to " + game.getPlayer1().getName()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

}
