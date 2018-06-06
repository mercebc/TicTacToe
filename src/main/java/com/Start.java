package com;

import game.Game;

public class Start {

    public static void main(String[] args) {


      Game game = new Game();
      game.createPlayers(1);

      Cli cli = new Cli(game);

      cli.showMenu();
    }
}
