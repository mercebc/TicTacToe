package players;

import com.Cli;
import game.Board;
import game.Cell;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Human extends Player {

  public Human(String name, String symbol) {
    super(name, symbol);//calls parent class constructor
  }

  //overwrite the methods in player


  public int getSpot(Board board, Player player1, Player player2, Cli cli) {
    System.out.print(this.getName() + ", please enter a number between 1 and 9 to allocate your symbol. Enter \"h\" for help.\n");
    int spot = getHumanSpot(cli);
    if(validateSpot (board, spot, player1, player2)) {
      return spot;
    }else{
      return getSpot (board, player1, player2, cli);
    }
  }

  private int getHumanSpot(Cli cli) {
    int spot = -1;
    try {
      spot = cli.askForIntegerOrHelpBetweenMinAndMax(1, 9) - 1;
      }
      catch (InputMismatchException ex) {
        System.out.println(ex.getMessage());
        getHumanSpot(cli);
      }
      catch (IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
        getHumanSpot(cli);
      }
  return spot;
  }

  public boolean changeName(Cli cli, Player opponent) {
    System.out.println("Enter new name for " + this.getName());

    String name = cli.askForString();

    if (checkOpponentName(opponent, name)) {//if new name is not the same as the other player's name
      this.setName(name);//change name
      System.out.println("Name changed to " + this.getName());
      return true;
    }else{
      System.out.println("The name you are trying to change is the same one as your opponent's name: " + opponent.getName());
      return false;
    }
  }

  private boolean checkOpponentName(Player opponent, String name) {
    return (!(opponent.getName().equals(name)));
  }

  public boolean changeSymbol(Cli cli, Player opponent) {

    String symbol = cli.askForString();

    if(symbol.length() > 1) {//if input is more than one character, trim and inform user
      symbol = trimSymbol(symbol);
    }

    if(checkOpponentSymbol(opponent, symbol)){
      this.setSymbol(symbol);//change symbol
      System.out.println("Symbol changed to " + this.getSymbol());
      return true;
    }else{
      System.out.println("The symbol you are trying to change is the same one as your opponent's symbol: " + opponent.getName());
      return false;
    }
  }

  private boolean checkOpponentSymbol(Player opponent, String symbol) {
    return (!(opponent.getSymbol().equals(symbol)));
  }


  private String trimSymbol(String symbol) {
    char s = symbol.charAt(0);//get only the first character
    System.out.println("Note: We have trimmed your Symbol as it can only contain one character");
    return Character.toString(s);
  }

  private boolean validateSpot(Board board, int spot, Player player1, Player player2){
    if (!board.getCell(spot).getValue().equals(player1.getSymbol()) && !board.getCell(spot).getValue().equals(player2.getSymbol())) {//check the spot is already taken
      return true;
    } else {
      System.out.println("Enter a number that is not already used");
      return false;
    }
  }

}
