package game;

import com.Cli;
import org.junit.Before;
import org.junit.Test;
import players.Computer;
import players.Human;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsString;

public class GameTest {

  private ByteArrayOutputStream out;
  private PrintStream output;

  @Before
  public void setUp() {
    out = new ByteArrayOutputStream();
    output = new PrintStream(out);
  }

  Cli cli = new Cli(System.in, output);
  Game game = new Game(cli);


  @Test
  public void Player1StartsFirst() {

    game.whoStartsFirst(1);

    assertThat(game.getPlayer1(), is (game.getCurrentPlayer()));
  }

  @Test
  public void Player2StartsFirst() {

    game.whoStartsFirst(2);

    assertThat(game.getPlayer2(), is (game.getCurrentPlayer()));
  }

  @Test
  public void NextPlayerIfPlayer1sTurn() {

    game.whoStartsFirst(1);

    assertThat(game.nextPlayer(), is (game.getPlayer2()));

  }

  @Test
  public void NextPlayerIfPlayer2sTurn() {

    game.whoStartsFirst(1);

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

    assertThat(game.playAgain(1), is (false));
  }

  @Test
  public void PlayerChoosesToPlayAgain() {

    assertThat(game.playAgain(2), is (true));
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

  @Test
  public void FinalAnnouncemntsIsTie() {
    game.getBoard().setCell(0, "X");
    game.getBoard().setCell(1, "O");
    game.getBoard().setCell(2, "O");

    game.getBoard().setCell(3, "O");
    game.getBoard().setCell(4, "X");
    game.getBoard().setCell(5, "X");

    game.getBoard().setCell(6, "X");
    game.getBoard().setCell(7, "O");
    game.getBoard().setCell(8, "O");

    game.finalGameAnnouncements();

    //assertThat(out.toString(), containsString(game.getPlayer1().getSymbol()));
    //assertThat(out.toString(), containsString(game.getPlayer2().getSymbol()));
    assertThat(out.toString(), containsString("Ohh there's no winner, it's a tie!"));
  }

  //TestInitGame

//

//  @Test
//  public void AnnounceIfCheckLinePlayer1() {
//    Cli cli = mockCliString("UserInput");
//    cli.announceWinner(game.getPlayer1());
//    assertThat(out.toString(), containsString("Congratulations! The winner is " + game.getPlayer1().getName()));
//  }
//
//  @Test
//  public void AnnounceIfCheckLinePlayer2() {
//    Cli cli = mockCliString("UserInput");
//    cli.announceWinner(game.getPlayer2);
//    assertThat(out.toString(), containsString("Congratulations! The winner is " + game.getPlayer2().getName()));
//  }

  //TestGetSpot

}
