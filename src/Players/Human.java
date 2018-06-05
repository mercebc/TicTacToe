package Players;

import Game.Board;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Human extends Player {

  public Human(String name, String symbol) {
    super(name, symbol);//calls parent class constructor
  }

  //overwrite the methods in player

  public static Scanner input = new Scanner(System.in); // the input Scanner

  public int getSpot(Board board, Player player1, Player player2, Player currentPlayer) {

    int spot = 0;
    boolean validInput = false;

    do {

      String help = "";

      System.out.print(currentPlayer.getName() + ", please enter a number between 1 and 9 to allocate your symbol. Enter \"h\" for help.\n");
      try {
        if (input.hasNextInt()) {
          spot = input.nextInt();//assigns to spot the inserted value. Only if it's a number
        }
        else if (input.hasNext()) {

          help = input.next();//assigns to help the inserted value. If it isn't a number.

          if (help.equals("h")) {//if the input equals "h" a note is displayed to help the user understand how the game works

            System.out.println("The following numbers are the position your symbol will be placed.");
            System.out.println(" 1 | 2 | 3 " + "\n===+===+===\n" + " 4 | 5 | 6 " + "\n===+===+===\n" + " 7 | 8 | 9 ");
            validInput = false; //the input ("h") in not valid to be the spot as it needs to be 1-9

          } else {//if it isn't a "h", throw an exception
            throw new InputMismatchException("You can only input integers or \"h\" for help.");
          }
        }

        if (spot > 0 && spot < 10) {

          spot = spot - 1;//position in array starts at 0, therefore need to take away 1

          if (validateSpot(board, spot, player1, player2)){
            validInput = true;//input value is true as its an int 1-9
          }else {
            validInput = false;
          }

        } else {
          if(!help.equals("h")) {//if it isn't a "h", throw an exception
            throw new IllegalArgumentException("You can only input an integer between 1 and 9");
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

  public boolean changeName(Player player2) {
    System.out.println("Enter new name for " + this.getName());
    if (input.hasNext()) {//No need to check input as there is no restrictions for name
      String name = input.next();

      if (!(player2.getName().equals(name))) {//if new name is not the same as the other player's name
        this.setName(name);//change name
        System.out.println("Name changed to " + this.getName());
        return true;
      }else{
        System.out.println("The name you are trying to change is the same one as your opponent's name: " + player2.getName());
        return false;
      }
    }
    return false;
  }

  public boolean changeSymbol(Player player2) {
    System.out.println("Enter new symbol for " + this.getName());
    if (input.hasNext()) {
      String symbol = input.next();

      if(symbol.length() > 1){//if input is more than one character, trim and inform user
        char s = symbol.charAt(0);//get only the first character
        symbol = Character.toString(s);
        System.out.println("Note: We have trimmed your Symbol as it can only contain one character");
      }

      if (!(player2.getSymbol().equals(symbol))) {
        this.setSymbol(symbol);//change symbol
        System.out.println("Symbol changed to " + this.getSymbol());
        return true;
      }else{
        System.out.println("The symbol you are trying to change is the same one as your opponent's symbol: " + player2.getName());
        return false;
      }
    }
    return false;
  }

  public boolean validateSpot(Board board, int spot, Player player1, Player player2){
    if (!board.getCell(spot).equals(player1.getSymbol()) && !board.getCell(spot).equals(player2.getSymbol())) {//check the spot is already taken
      return true;
    } else {
      System.out.println("Enter a number that is not already used");
      return false;
    }
  }



}
