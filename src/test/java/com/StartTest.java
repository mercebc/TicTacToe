package com;

import game.Game;

public class StartTest {

  public static void main(String[] args) {


    Cli cli = new Cli(System.in, System.out);
    Game game = new Game();
    GameUI gameUI = new GameUI(game, cli);

    Menu menu = new Menu(gameUI, game, cli);

    menu.flowGame();

  }


}
