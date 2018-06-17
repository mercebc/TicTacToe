package players;


public class PlayerFactory {

  public Player player1(String gameOption) {

    if(gameOption.equalsIgnoreCase("HUMAN-COMPUTER") || gameOption.equalsIgnoreCase("HUMAN-HUMAN")){
      return new Human("Player1", "X");
    }
    return new Computer("Computer", "O");
  }

  public Player player2(String gameOption) {
    if(gameOption.equalsIgnoreCase("HUMAN-COMPUTER") || gameOption.equalsIgnoreCase("COMPUTER-COMPUTER")){
      return new Computer("Computer", "L");
    }
    return new Human("Player2", "D");
  }

}
