package com;

import exceptions.SameNameException;
import exceptions.SameSpotException;
import exceptions.SameSymbolException;
import game.Board;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import players.Human;
import players.Player;

import java.util.InputMismatchException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;


  public class ValidateTest {

    private Validate validate = new Validate();
    private Board board = new Board();
    Player opponent;
    Player current;

    @Before
    public void setUp() {
      Cli cli = new Cli(System.in, System.out);

      opponent = new Human("Tom", "X", cli);
      current = new Human("Elisabeth", "E", cli);

      board.setCell(0, "E");
      board.setCell(1, "X");
      board.setCell(2, "E");
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void inputNameIsNotTheSameAsTheOpponentName() throws SameNameException {
      String name = "Peter";

      assertThat(validate.notSameName(opponent, name), is(true));
    }

    @Test
    public void inputNameIsTheSameAsTheOpponentName() throws SameNameException {
      String name = "Tom";
      thrown.expect(SameNameException.class);
      thrown.expectMessage(containsString("The name you are trying to change is the same one as your opponent's name: " + opponent.getName()));


      validate.notSameName(opponent, name);
    }

    @Test
    public void inputSymbolIsNotTheSameAsTheOpponentSymbol() throws SameSymbolException {
      String symbol = "P";

      assertThat(validate.notSameSymbol(opponent, symbol), is(true));
    }

    @Test
    public void inputSymbolIsTheSameAsTheOpponentSymbol() throws SameSymbolException {
      String symbol = "X";
      thrown.expect(SameSymbolException.class);
      thrown.expectMessage(containsString("The symbol you are trying to change is the same one as your opponent's symbol: " + opponent.getSymbol()));


      assertThat(validate.notSameSymbol(opponent, symbol), is(false));
    }

    @Test
    public void spotInBoardIsEmpty() throws SameSpotException {
      int spot = 3;

      assertThat(validate.spot(board,spot, current, opponent), is(true));
    }

    @Test
    public void spotInBoardIsNotEmptyBecauseBelongsToCurrentPlayer() throws SameSpotException {
      int spot = 0;
      thrown.expect(SameSpotException.class);
      thrown.expectMessage(containsString("Enter a number that is not already used"));


      assertThat(validate.spot(board,spot, current, opponent), is(false));
    }

    @Test
    public void spotInBoardIsNotEmptyBecauseBelongsToOpponent() throws SameSpotException {
      int spot = 1;
      thrown.expect(SameSpotException.class);
      thrown.expectMessage(containsString("Enter a number that is not already used"));


      assertThat(validate.spot(board,spot, current, opponent), is(false));
    }

    @Test
    public void NumBetweenMaxAndMin(){
      int max = 5;
      int min = 0;
      int num = 1;

      assertThat(validate.betweenMinAndMax(min,max,num), is(num));
    }

    @Test
    public void NumLargerThanMax(){
      int max = 5;
      int min = 0;
      int num = 6;
      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage(containsString("You can only input an integer between " + min + " and " + max));

      validate.betweenMinAndMax(min,max,num);
    }

    @Test
    public void NumSmallerThanMin(){
      int max = 5;
      int min = 3;
      int num = 2;
      thrown.expect(IllegalArgumentException.class);
      thrown.expectMessage(containsString("You can only input an integer between " + min + " and " + max));

      validate.betweenMinAndMax(min,max,num);
    }

    @Test
    public void StringHelpEqualsToH(){
      String help = "h";

      assertThat(validate.isHelp(help), is(true));
    }

    @Test
    public void StringHelpDoesntEqualsToH(){
      thrown.expect(InputMismatchException.class);
      thrown.expectMessage(containsString("You can only input integers or \"h\" for help."));

      String help = "p";

      validate.isHelp(help);
    }

  }
