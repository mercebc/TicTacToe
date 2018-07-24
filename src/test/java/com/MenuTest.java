package com;

import game.Game;
import org.junit.Before;
import org.junit.Test;
import players.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;

public class MenuTest {

  private ByteArrayOutputStream out;
  private PrintStream output;
  Game game;

  private Cli mockCli(String mockInput) {
    ByteArrayInputStream input = new ByteArrayInputStream(mockInput.getBytes());

    return new Cli(input, output);
  }

  @Before
  public void setUp() {
    out = new ByteArrayOutputStream();
    output = new PrintStream(out);
    game = new Game();
  }

  @Test
  public void showMenuEnterNumHigherThanMaxThenReEnter5() {
    Cli cli = mockCli("\n9\n5");
    GameUI gameUI = new GameUI(game, cli);
    Menu menu = new Menu(gameUI, game, cli);

    assertThat(menu.showStartMenuAndGetOption(), is(5));
    assertThat(out.toString(), containsString("You can only input an integer between"));
    }

  @Test
  public void showMenuEnterCharacterThenReEnter3() {
    Cli cli = mockCli("\np\n3");
    GameUI gameUI = new GameUI(game, cli);
    Menu menu = new Menu(gameUI, game, cli);

    assertThat(menu.showStartMenuAndGetOption(), is(3));
    assertThat(out.toString(), containsString("You can only input integers"));

  }

  @Test
  public void showMenuAndWatchComputerBattle() {
    Cli cli = mockCli("1\n1");
    GameUI gameUI = new GameUI(game, cli);
    gameUI.createPlayers(3);
    Menu menu = new Menu(gameUI, game, cli);

    assertThat(menu.showStartMenuAndGetOption(), is(1));
    menu.showOptionsMenu(1);
    assertThat(out.toString(), containsString("to watch a computer battle"));
    assertThat(out.toString(), containsString("Ohh there's no winner, it's a tie!"));
  }

  @Test
  public void PlayAgain() {
    Cli cli = mockCli("1\n2\n1\n1");
    GameUI gameUI = new GameUI(game, cli);
    gameUI.createPlayers(3);
    Menu menu = new Menu(gameUI, game, cli);

    assertThat(menu.showStartMenuAndGetOption(), is(1));
    menu.showOptionsMenu(1);
    assertThat(out.toString(), containsString("to watch a computer battle"));
    assertThat(out.toString(), containsString("Ohh there's no winner, it's a tie!"));
    assertThat(out.toString(), containsString("to Play again"));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void chooseComputerAsOpponent_EnterNumHigherThanMaxThenOkThenExit() {
    Cli cli = mockCli("2\n6\n1\n5");
    GameUI gameUI = new GameUI(game, cli);
    Menu menu = new Menu(gameUI, game, cli);

    menu.flowGame();

    assertThat(out.toString(), containsString("You can only input an integer between"));
    assertThat(out.toString(), containsString("You are playing against " + game.getPlayer2().getName()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void choosePlayerAsOpponent_EnterCharacterThenOkThenExit() {
    Cli cli = mockCli("2\nkp\n1\n5");
    GameUI gameUI = new GameUI(game, cli);
    Menu menu = new Menu(gameUI, game, cli);

    menu.flowGame();

    assertThat(out.toString(), containsString("You can only input integers"));
    assertThat(out.toString(), containsString("You are playing against " + game.getPlayer2().getName()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void changeNamePlayer2_ModeHumanHuman_EnterNumHigherThanMax_ThenTheSameValue_ThenOk() {
    Cli cli = mockCli("3\n3\n2\nPlayer1\n2\nAngela\n5");
    GameUI gameUI = new GameUI(game, cli);
    Menu menu = new Menu(gameUI, game, cli);
    gameUI.createPlayers(1);

    menu.flowGame();

    assertThat(out.toString(), containsString("to change Player2's name"));
    assertThat(out.toString(), containsString("You can only input an integer between"));
    assertThat(out.toString(), containsString("The name you are trying to change is the same one as your opponent's name"));
    assertThat(out.toString(), containsString("Name changed to " + game.getPlayer2().getName()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void changeNamePlayer2_ModeHumanHuman_EnterCharacter_ThenTheSameValue_ThenOk() {
    Cli cli = mockCli("3\np\n1\nPlayer2\n1\nAmelia\n5");
    GameUI gameUI = new GameUI(game, cli);
    Menu menu = new Menu(gameUI, game, cli);
    gameUI.createPlayers(1);

    menu.flowGame();

    assertThat(out.toString(), containsString("to change Player1's name"));
    assertThat(out.toString(), containsString("You can only input integers"));
    assertThat(out.toString(), containsString("The name you are trying to change is the same one as your opponent's name"));
    assertThat(out.toString(), containsString("Name changed to " + game.getPlayer1().getName()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void changeSymbolPlayer2_ModeHumanHuman_EnterNumHigherThanMax_ThenSameSymbolPlayer1_ThenTrimSymbolOk() {
    Cli cli = mockCli("4\n6\n2\nX\n2\nApple\n5");
    GameUI gameUI = new GameUI(game, cli);
    Menu menu = new Menu(gameUI, game, cli);
    gameUI.createPlayers(1);

    menu.flowGame();

    assertThat(out.toString(), containsString("to change " + game.getPlayer2().getName() +"'s symbol"));
    assertThat(out.toString(), containsString("You can only input an integer between"));
    assertThat(out.toString(), containsString("The symbol you are trying to change is the same one as your opponent's symbol"));
    assertThat(out.toString(), containsString("Note: We have trimmed your Symbol as it can only contain one character"));
    assertThat(out.toString(), containsString("Symbol changed to " + game.getPlayer2().getSymbol()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void changeSymbolPlayer2_ModeHumanHuman_EnterCharacter_ThenSameSymbolPlayer1_ThenOk() {
    Cli cli = mockCli("4\nl\n1\nD\n1\nM\n5");
    GameUI gameUI = new GameUI(game, cli);
    Menu menu = new Menu(gameUI, game, cli);
    gameUI.createPlayers(1);

    menu.flowGame();

    assertThat(out.toString(), containsString("to change " + game.getPlayer1().getName() +"'s symbol"));
    assertThat(out.toString(), containsString("You can only input integers"));
    assertThat(out.toString(), containsString("The symbol you are trying to change is the same one as your opponent's symbol"));
    assertThat(out.toString(), containsString("Symbol changed to " + game.getPlayer1().getSymbol()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void changeSymbolHuman_modeHumanComputer() {
    Cli cli = mockCli("4\nX\n5");
    GameUI gameUI = new GameUI(game, cli);
    Menu menu = new Menu(gameUI, game, cli);

    menu.flowGame();
    assertThat(out.toString(), containsString("Symbol changed to " + game.getPlayer1().getSymbol()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void changeSymbolHuman_modeHumanComputer_EnterSameAsComputer_ThenOk() {
    Cli cli = mockCli("4\nL\nM\n5");
    GameUI gameUI = new GameUI(game, cli);
    Menu menu = new Menu(gameUI, game, cli);

    menu.flowGame();

    assertThat(out.toString(), containsString("The symbol you are trying to change is the same one as your opponent's symbol"));
    assertThat(out.toString(), containsString("Symbol changed to " + game.getPlayer1().getSymbol()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }


  @Test
  public void changeName_modeHumanComputer() {
    Cli cli = mockCli("3\nPeter\n5");
    GameUI gameUI = new GameUI(game, cli);
    Menu menu = new Menu(gameUI, game, cli);

    menu.flowGame();

    assertThat(out.toString(), containsString("Name changed to " + game.getPlayer1().getName()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

  @Test
  public void changeName_modeHumanComputer_EnterSameAsComputer_ThenOk() {
    Cli cli = mockCli("3\nComputer\nMary\n5");
    GameUI gameUI = new GameUI(game, cli);
    Menu menu = new Menu(gameUI, game, cli);

    menu.flowGame();

    assertThat(out.toString(), containsString("The name you are trying to change is the same one as your opponent's name"));
    assertThat(out.toString(), containsString("Name changed to " + game.getPlayer1().getName()));
    assertThat(out.toString(), containsString("Sorry to hear you are leaving us, see you soon!"));
  }

}
