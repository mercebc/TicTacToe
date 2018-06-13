package com;

import game.Game;

public class StartTest {

  public static void main(String[] args) {


    Cli cli = new Cli(System.in, System.out);
    Game game = new Game();
    Menu menu = new Menu(game, cli);

    menu.showMenu();
  }


}
