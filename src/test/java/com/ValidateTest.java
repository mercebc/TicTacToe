package com;

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
      opponent = new Human("Tom", "X");
      current = new Human("Elisabeth", "E");

      board.setCell(0, "E");
      board.setCell(1, "X");
      board.setCell(2, "E");
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void inputNameIsNotTheSameAsTheOpponentName(){
      String name = "Peter";

      assertThat(validate.notSameName(opponent, name), is(true));
    }

    @Test
    public void inputNameIsTheSameAsTheOpponentName(){
      String name = "Tom";

      assertThat(validate.notSameName(opponent, name), is(false));
    }

    @Test
    public void inputSymbolIsNotTheSameAsTheOpponentSymbol(){
      String symbol = "P";

      assertThat(validate.notSameSymbol(opponent, symbol), is(true));
    }

    @Test
    public void inputSymbolIsTheSameAsTheOpponentSymbol(){
      String symbol = "X";

      assertThat(validate.notSameSymbol(opponent, symbol), is(false));
    }

    @Test
    public void spotInBoardDoesntIsEmpty(){
      int spot = 3;

      assertThat(validate.spot(board,spot, current, opponent), is(true));
    }

    @Test
    public void spotInBoardIsNotEmptyBecauseBelongsToCurrentPlayer(){
      int spot = 0;

      assertThat(validate.spot(board,spot, current, opponent), is(false));
    }

    @Test
    public void spotInBoardIsNotEmptyBecauseBelongsToOpponent(){
      int spot = 1;

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
