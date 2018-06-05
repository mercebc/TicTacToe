package com;

import Game.Game;
import Game.Board;

public class Start {

    public static void main(String[] args) {


      Game game = new Game();
      game.createPlayers(1);

      Cli cli = new Cli(game);

      cli.showMenu();
    }
}
