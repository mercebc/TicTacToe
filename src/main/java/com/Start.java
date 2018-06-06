package com;

import game.Game;

public class Start {

    public static void main(String[] args) {

      Cli cli = new Cli();
      Game game = new Game(cli);
      Menu menu = new Menu(game, cli);

      menu.showMenu();
    }
}
