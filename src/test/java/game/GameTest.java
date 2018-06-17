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


  private Cli mockCli(String mockInput) {
    ByteArrayInputStream input = new ByteArrayInputStream(mockInput.getBytes());

    return new Cli(input, output);
  }

  @Before
  public void setUp() {
    out = new ByteArrayOutputStream();
    output = new PrintStream(out);
  }

  Cli cli = new Cli(System.in, output);
  Game game = new Game();


  @Test
  public void Player1StartsFirst() {
    Cli cli = mockCli("1");
    game.whoStartsFirst(cli);

    assertThat(game.getPlayer1(), is (game.getCurrentPlayer()));
  }

  @Test
  public void Player2StartsFirst() {
    Cli cli = mockCli("2");
    game.whoStartsFirst(cli);

    assertThat(game.getPlayer2(), is (game.getCurrentPlayer()));
  }

  @Test
  public void NextPlayerIfPlayer1sTurn() {
    Cli cli = mockCli("1");
    game.whoStartsFirst(cli);

    assertThat(game.nextPlayer(), is (game.getPlayer2()));

  }

  @Test
  public void NextPlayerIfPlayer2sTurn() {
    Cli cli = mockCli("1");
    game.whoStartsFirst(cli);

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
    Cli cli = mockCli("1");
    assertThat(game.playAgain(cli), is (false));
  }

  @Test
  public void PlayerChoosesToPlayAgain() {
    Cli cli = mockCli("2");
    assertThat(game.playAgain(cli), is (true));
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
    Cli cli = mockCli("a\n1");
    game.whoStartsFirst(cli);

    assertThat(out.toString(), containsString("You can only input integers"));
    assertThat(game.getPlayer1(), is (game.getCurrentPlayer()));

  }

  @Test
  public void InputUserNumHigherThanMaxWhenAskPlayerStartFirstThenPlayer2() {
    Cli cli = mockCli("5\n2");
    game.whoStartsFirst(cli);

    assertThat(out.toString(), containsString("You can only input an integer between"));
    assertThat(game.getPlayer2(), is (game.getCurrentPlayer()));

  }

  @Test
  public void InputUserCharWhenAskQuitOrPlayThenQuit() {
    Cli cli = mockCli("!\n1");
    game.playAgain(cli);

    assertThat(out.toString(), containsString("You can only input integers"));
  }

  @Test
  public void InputUserNumHigherThanMaxWhenAskQuitOrPlayThenStartAgain() {
    Cli cli = mockCli("3\n2");
    game.playAgain(cli);

    assertThat(out.toString(), containsString("You can only input an integer between"));
  }

  @Test
  public void FinalAnnouncemntsIsTie() {
    game.getBoard().setCell(0, game.getPlayer1().getSymbol());
    game.getBoard().setCell(1, game.getPlayer2().getSymbol());
    game.getBoard().setCell(2, game.getPlayer2().getSymbol());

    game.getBoard().setCell(3, game.getPlayer2().getSymbol());
    game.getBoard().setCell(4, game.getPlayer1().getSymbol());
    game.getBoard().setCell(5, game.getPlayer1().getSymbol());

    game.getBoard().setCell(6, game.getPlayer1().getSymbol());
    game.getBoard().setCell(7, game.getPlayer2().getSymbol());
    game.getBoard().setCell(8, game.getPlayer2().getSymbol());

    Cli cli = mockCli("UserInput");

    game.finalGameAnnouncements(cli);

    assertThat(out.toString(), containsString("Ohh there's no winner, it's a tie!"));
  }

  @Test
  public void FinalAnnouncemntsPlayer1Wins() {
    game.getBoard().setCell(0, game.getPlayer1().getSymbol());
    game.getBoard().setCell(1, game.getPlayer2().getSymbol());
    game.getBoard().setCell(2, " ");

    game.getBoard().setCell(3, game.getPlayer1().getSymbol());
    game.getBoard().setCell(4, game.getPlayer2().getSymbol());
    game.getBoard().setCell(5, game.getPlayer1().getSymbol());

    game.getBoard().setCell(6, game.getPlayer1().getSymbol());
    game.getBoard().setCell(7, " ");
    game.getBoard().setCell(8, game.getPlayer2().getSymbol());

    Cli cli = mockCli("UserInput");

    game.finalGameAnnouncements(cli);

    assertThat(out.toString(), containsString(game.getPlayer1().getName()));
  }

  @Test
  public void FinalAnnouncemntsPlayer2Wins() {
    game.getBoard().setCell(0, game.getPlayer1().getSymbol());
    game.getBoard().setCell(1, game.getPlayer2().getSymbol());
    game.getBoard().setCell(2, game.getPlayer2().getSymbol());

    game.getBoard().setCell(3, game.getPlayer2().getSymbol());
    game.getBoard().setCell(4, game.getPlayer1().getSymbol());
    game.getBoard().setCell(5, game.getPlayer1().getSymbol());

    game.getBoard().setCell(6, game.getPlayer2().getSymbol());
    game.getBoard().setCell(7, game.getPlayer2().getSymbol());
    game.getBoard().setCell(8, game.getPlayer2().getSymbol());

    Cli cli = mockCli("UserInput");

    game.finalGameAnnouncements(cli);

    assertThat(out.toString(), containsString(game.getPlayer2().getName()));
  }

}
