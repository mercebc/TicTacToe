package game;

import com.Cli;
import org.junit.Test;
import players.Computer;
import players.Human;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class GameTest {
  Cli cli = new Cli();
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

  //TestInitGame

  //TestFinalAnnouncements

  //TestGetSpot

}
