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

//  @Test
//  public void showMenuIfplayer1IsComputerAndEnterValidValue() {
//    Cli cli = mockCli("1");
//    Menu menu = new Menu(game, cli);
//    game.createPlayers(3);
//
//    menu.showMenu();
//
//    assertThat(out.toString(), containsString("to watch a computer battle"));
//
//  }

//  public void showMenu(){
//
//    boolean validEntry = false;
//    int maxvalue;
//
//    do {
//      if (player1 instanceof Computer) {
//        System.out.println("Enter \"1\" to watch a computer battle, \"2\" to Choose game mode");
//        maxvalue = 2;
//      } else {
//        System.out.println("Enter \"1\" to Play, \"2\" to Choose game mode, \"3\" to Change names or \"4\" to Change symbols");
//        maxvalue = 4;
//      }
//      //Validate the input
//      try {
//
//        showOptions(cli.askForIntegerBetweenMinAndMax(1,maxvalue));
//        validEntry = true;
//
//      } catch (IllegalArgumentException ex) {//catch the exceptions
//        System.out.println(ex.getMessage());
//        validEntry = false; //to loop
//
//      } catch (InputMismatchException ex) {
//        System.out.println(ex.getMessage());
//        input.next();//to lose the value as it is not valid
//        validEntry = false;
//      }
//    } while (!validEntry);//keeps looping until validEntry is valid(true)
//
//  }




}
