package game;

import com.Cli;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import players.Computer;
import players.Human;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsString;

public class GameTest {

  private ByteArrayOutputStream out;
  private PrintStream output;

  Cli cli = new Cli(System.in, output);
  Game game;

  private Cli mockCli(String mockInput) {
    ByteArrayInputStream input = new ByteArrayInputStream(mockInput.getBytes());

    return new Cli(input, output);
  }

  @Before
  public void setUp() {
    out = new ByteArrayOutputStream();
    output = new PrintStream(out);

    cli = mockCli("UserInput");
    game = new Game(cli);
  }


  @Test
  public void Player1StartsFirst() {
    cli = mockCli("1");
    game = new Game(cli);
    game.whoStartsFirst();

    assertThat(game.getPlayer1(), is (game.getCurrentPlayer()));
  }

  @Test
  public void Player2StartsFirst() {
    Cli cli = mockCli("2");
    Game game = new Game(cli);
    game.whoStartsFirst();

    assertThat(game.getPlayer2(), is (game.getCurrentPlayer()));
  }

  @Test
  public void NextPlayerIfPlayer1sTurn() {
    cli = mockCli("1");
    game = new Game(cli);
    game.whoStartsFirst();

    assertThat(game.nextPlayer(), is (game.getPlayer2()));

  }

  @Test
  public void NextPlayerIfPlayer2sTurn() {
    cli = mockCli("1");
    game = new Game(cli);
    game.whoStartsFirst();

    game.setCurrentPlayer(game.nextPlayer());

    assertThat(game.nextPlayer(), is (game.getPlayer1()));

  }

  @Test
  public void SetASpotInACell() {

    game.setCurrentPlayer(game.getPlayer1());

    game.setSpot(1);

    assertThat(game.getBoard().getCell(1).getValue(), is (game.getCurrentPlayer().getSymbol()));

  }

  @Test
  public void PlayerChoosesToQuit() {
    cli = mockCli("1");
    game = new Game(cli);
    assertThat(game.playAgain(), is (false));
  }

  @Test
  public void PlayerChoosesToPlayAgain() {
    cli = mockCli("2");
    game = new Game(cli);
    assertThat(game.playAgain(), is (true));
  }

  @Test
  public void PlayersOptionHumanHuman() {
    game.createPlayers(1);

    assertThat(game.getPlayer1(), instanceOf(Human.class));
    assertThat(game.getPlayer2(), instanceOf(Human.class));
  }

  @Test
  public void PlayersOptionHumanComputer() {
    game.createPlayers(2);

    assertThat(game.getPlayer1(), instanceOf(Human.class));
    assertThat(game.getPlayer2(), instanceOf(Computer.class));
  }

  @Test
  public void PlayersOptionComputerComputer() {
    game.createPlayers(3);

    assertThat(game.getPlayer1(), instanceOf(Computer.class));
    assertThat(game.getPlayer2(), instanceOf(Computer.class));
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void InputUserLetterWhenAskPlayerStartFirstThenPlayer1() {
    cli = mockCli("a\n1");
    game = new Game(cli);
    game.whoStartsFirst();

    assertThat(out.toString(), containsString("You can only input integers"));
    assertThat(game.getPlayer1(), is (game.getCurrentPlayer()));

  }

  @Test
  public void InputUserNumHigherThanMaxWhenAskPlayerStartFirstThenPlayer2() {
    cli = mockCli("5\n2");
    game = new Game(cli);
    game.whoStartsFirst();

    assertThat(out.toString(), containsString("You can only input an integer between"));
    assertThat(game.getPlayer2(), is (game.getCurrentPlayer()));

  }

  @Test
  public void InputUserCharWhenAskQuitOrPlayThenQuit() {
    cli = mockCli("!\n1");
    game = new Game(cli);
    game.playAgain();

    assertThat(out.toString(), containsString("You can only input integers"));
  }

  @Test
  public void InputUserNumHigherThanMaxWhenAskQuitOrPlayThenStartAgain() {
    cli = mockCli("3\n2");
    game = new Game(cli);
    game.playAgain();

    assertThat(out.toString(), containsString("You can only input an integer between"));
  }

  @Test
  public void FinalAnnouncemntsIsTie() {
    cli = mockCli("UserInput");
    game = new Game(cli);

    game.getBoard().setCell(0, game.getPlayer1().getSymbol());
    game.getBoard().setCell(1, game.getPlayer2().getSymbol());
    game.getBoard().setCell(2, game.getPlayer2().getSymbol());

    game.getBoard().setCell(3, game.getPlayer2().getSymbol());
    game.getBoard().setCell(4, game.getPlayer1().getSymbol());
    game.getBoard().setCell(5, game.getPlayer1().getSymbol());

    game.getBoard().setCell(6, game.getPlayer1().getSymbol());
    game.getBoard().setCell(7, game.getPlayer2().getSymbol());
    game.getBoard().setCell(8, game.getPlayer2().getSymbol());

    game.finalGameAnnouncements();

    assertThat(out.toString(), containsString("Ohh there's no winner, it's a tie!"));
  }

  @Test
  public void FinalAnnouncementsPlayer1Wins() {

    cli = mockCli("UserInput");
    game = new Game(cli);

    game.getBoard().setCell(0, game.getPlayer1().getSymbol());
    game.getBoard().setCell(1, game.getPlayer2().getSymbol());
    game.getBoard().setCell(2, " ");

    game.getBoard().setCell(3, game.getPlayer1().getSymbol());
    game.getBoard().setCell(4, game.getPlayer2().getSymbol());
    game.getBoard().setCell(5, game.getPlayer1().getSymbol());

    game.getBoard().setCell(6, game.getPlayer1().getSymbol());
    game.getBoard().setCell(7, " ");
    game.getBoard().setCell(8, game.getPlayer2().getSymbol());

    game.finalGameAnnouncements();

    assertThat(out.toString(), containsString(game.getPlayer1().getName()));
  }

  @Test
  public void FinalAnnouncementsPlayer2Wins() {

    cli = mockCli("UserInput");
    game = new Game(cli);

    game.getBoard().setCell(0, game.getPlayer1().getSymbol());
    game.getBoard().setCell(1, game.getPlayer2().getSymbol());
    game.getBoard().setCell(2, game.getPlayer2().getSymbol());

    game.getBoard().setCell(3, game.getPlayer2().getSymbol());
    game.getBoard().setCell(4, game.getPlayer1().getSymbol());
    game.getBoard().setCell(5, game.getPlayer1().getSymbol());

    game.getBoard().setCell(6, game.getPlayer2().getSymbol());
    game.getBoard().setCell(7, game.getPlayer2().getSymbol());
    game.getBoard().setCell(8, game.getPlayer2().getSymbol());

    game.finalGameAnnouncements();

    assertThat(out.toString(), containsString(game.getPlayer2().getName()));
  }

}
