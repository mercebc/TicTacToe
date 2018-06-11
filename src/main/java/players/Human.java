package players;

import com.Cli;
import game.Board;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Human extends Player {

  public Human(String name, String symbol) {
    super(name, symbol);//calls parent class constructor
  }

  //overwrite the methods in player

  public int getSpot(Board board, Player player1, Player player2, Player currentPlayer, Cli cli) {

    int spot = 0;
    boolean validInput = false;

    do {

      String help = "";

      System.out.print(currentPlayer.getName() + ", please enter a number between 1 and 9 to allocate your symbol. Enter \"h\" for help.\n");

      try {

        spot = cli.askForIntegerOrHelpBetweenMinAndMax(1, 9) - 1;

        if(spot > 0) {
          if (validateSpot(board, spot, player1, player2)) {
            validInput = true;//input value is true as its an int 1-9
          } else {
            validInput = false;
          }
        }
      }
      catch (InputMismatchException ex) {
        System.out.println(ex.getMessage());
        validInput = false;
      }

      catch (IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
        validInput = false;
      }

    } while(!validInput);

  return spot;

  }

  public boolean changeName(String InputStream, Player opponent) {
    System.out.println("Enter new name for " + this.getName());

    String name = InputStream;

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

  public boolean changeSymbol(String InputStream, Player opponent) {

    String symbol = InputStream;

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
    if (!board.getCell(spot).equals(player1.getSymbol()) && !board.getCell(spot).equals(player2.getSymbol())) {//check the spot is already taken
      return true;
    } else {
      System.out.println("Enter a number that is not already used");
      return false;
    }
  }

}
