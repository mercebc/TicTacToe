package players;

import com.Cli;
import com.Validate;
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

    int spot = getHumanSpot();

    if(validate.spot(board, spot, this,opponent)) {
      return spot;
    }else{
      cli.printMessage("Enter a number that is not already used");
      return getSpot (board, opponent);
    }
  }

  private int getHumanSpot() {

    int spot = -1;

    try {
      cli.printMessage(this.getName() + ", please enter a number between 1 and 9 to allocate your symbol. Enter \"h\" for help.\n");

      spot = cli.askForIntegerOrHelpBetweenMinAndMax(1, 9) - 1;
    }
      catch (InputMismatchException ex) {
        cli.printMessage(ex.getMessage());
        spot = getHumanSpot();
      }
      catch (IllegalArgumentException ex) {
        cli.printMessage(ex.getMessage());
        spot = getHumanSpot();
      }
    return spot;
  }

  public boolean changeName(Player opponent) {
    System.out.println("Enter new name for " + this.getName());

    String name = cli.askForString();

      if (validate.notSameName(opponent, name)) {//if new name is not the same as the other player's name
        this.setName(name);//change name
        cli.printMessage("Name changed to " + this.getName());
        return true;
      } else {
        cli.printMessage("The name you are trying to change is the same one as your opponent's name: " + opponent.getName());
        return false;
      }

  }

  public boolean changeSymbol(Player opponent) {
    cli.printMessage("Enter new symbol for " + this.getName());

    String symbol = cli.askForString();

      if(symbol.length() > 1) {//if input is more than one character, trim and inform user
        symbol = trimSymbol(symbol);
      }

      if(validate.notSameSymbol(opponent, symbol)){
        this.setSymbol(symbol);//change symbol
        cli.printMessage("Symbol changed to " + this.getSymbol());
        return true;
      }else{
        cli.printMessage("The symbol you are trying to change is the same one as your opponent's symbol: " + opponent.getName());
        return false;
      }
  }


  private String trimSymbol(String symbol) {
    char s = symbol.charAt(0);//get only the first character
    cli.printMessage("Note: We have trimmed your Symbol as it can only contain one character");
    return Character.toString(s);
  }


}
