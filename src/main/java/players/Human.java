package players;

import com.Cli;
import game.Board;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;


public class Human extends Player {

  public Human(String name, String symbol) {
    super(name, symbol);//calls parent class constructor
  }

  //overwrite the methods in player


  public int getSpot(Board board, Player player1, Player player2, Cli cli) {
    int spot;

    do{
      spot = getHumanSpot(cli);

    }while (spot == -1);

    if(validateSpot (board, spot, player1, player2, cli)) {
      return spot;
    }else{
      return getSpot (board, player1, player2, cli);
    }
  }

  private int getHumanSpot(Cli cli) {
    int spot = -1;
    try {
      cli.printMessage(this.getName() + ", please enter a number between 1 and 9 to allocate your symbol. Enter \"h\" for help.\n");

      spot = cli.askForIntegerOrHelpBetweenMinAndMax(1, 9) - 1;
    }
      catch (InputMismatchException ex) {
        cli.printMessage(ex.getMessage());
        spot = getHumanSpot(cli);
      }
      catch (IllegalArgumentException ex) {
        cli.printMessage(ex.getMessage());
        spot = getHumanSpot(cli);
      }
    return spot;
  }

  public void changeName(Cli cli, Player opponent) {
    System.out.println("Enter new name for " + this.getName());

    String name = cli.askForString();

      if (checkOpponentName(opponent, name)) {//if new name is not the same as the other player's name
        this.setName(name);//change name
        cli.printMessage("Name changed to " + this.getName());
      } else {
        cli.printMessage("The name you are trying to change is the same one as your opponent's name: " + opponent.getName());
      }

  }

  private boolean checkOpponentName(Player opponent, String name) {
    return (!(opponent.getName().equals(name)));
  }

  public void changeSymbol(Cli cli, Player opponent) {
    cli.printMessage("Enter new symbol for " + this.getName());

    String symbol = cli.askForString();

      if(symbol.length() > 1) {//if input is more than one character, trim and inform user
        symbol = trimSymbol(symbol, cli);
      }

      if(checkOpponentSymbol(opponent, symbol)){
        this.setSymbol(symbol);//change symbol
        cli.printMessage("Symbol changed to " + this.getSymbol());
      }else{
        cli.printMessage("The symbol you are trying to change is the same one as your opponent's symbol: " + opponent.getName());
      }
  }

  private boolean checkOpponentSymbol(Player opponent, String symbol) {
    return (!(opponent.getSymbol().equals(symbol)));
  }


  private String trimSymbol(String symbol, Cli cli) {
    char s = symbol.charAt(0);//get only the first character
    cli.printMessage("Note: We have trimmed your Symbol as it can only contain one character");
    return Character.toString(s);
  }

  private boolean validateSpot(Board board, int spot, Player player1, Player player2, Cli cli){
    if (!board.getCell(spot).getValue().equals(player1.getSymbol()) && !board.getCell(spot).getValue().equals(player2.getSymbol())) {//check the spot is already taken
      return true;
    } else {
      cli.printMessage("Enter a number that is not already used");
      return false;
    }
  }

}
