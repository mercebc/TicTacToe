package players;

import com.Cli;
import com.Validate;
import exceptions.SameNameException;
import exceptions.SameSpotException;
import exceptions.SameSymbolException;
import game.Board;

import java.util.InputMismatchException;


public class Human extends Player {

  private Validate validate = new Validate();

  Cli cli;

  public Human(String name, String symbol, Cli cli) {
    super(name, symbol);//calls parent class constructor
    this.cli = cli;
  }

  //overwrite the methods in player


  public int getSpot(Board board, Player opponent) {

    int spot;
    try {
      cli.printMessage(this.getName() + ", please enter a number between 1 and 9 to allocate your symbol. Enter \"h\" for help.\n");

      spot = cli.askForIntegerOrHelpBetweenMinAndMax(1, 9) - 1;

      validate.spot(board, spot, this,opponent);
      return spot;
    }

    catch (InputMismatchException|IllegalArgumentException|SameSpotException  ex) {
      cli.printMessage(ex.getMessage());
      return getSpot(board, opponent);
    }

  }

  public boolean changeName(Player opponent) {
    System.out.println("Enter new name for " + this.getName());

    String name = cli.askForString();

      try{
        validate.notSameName(opponent, name);//if new name is not the same as the other player's name
        this.setName(name);//change name
        cli.printMessage("Name changed to " + this.getName());
        return true;
      }
      catch (SameNameException ex) {
        cli.printMessage(ex.getMessage());
        return false;
      }

  }

  public boolean changeSymbol(Player opponent) {
    cli.printMessage("Enter new symbol for " + this.getName());

    String symbol = cli.askForString();

      if(symbol.length() > 1) {//if input is more than one character, trim and inform user
        symbol = trimSymbol(symbol);
      }

      try{
        validate.notSameSymbol(opponent, symbol);
        this.setSymbol(symbol);//change symbol
        cli.printMessage("Symbol changed to " + this.getSymbol());
        return true;
      }
      catch (SameSymbolException ex) {
        cli.printMessage(ex.getMessage());
        return false;
      }
  }


  private String trimSymbol(String symbol) {
    char s = symbol.charAt(0);//get only the first character
    cli.printMessage("Note: We have trimmed your Symbol as it can only contain one character");
    return Character.toString(s);
  }


}
