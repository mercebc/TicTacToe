package game;

import players.Player;

public class Turn{
  long turn;
  State boardState;

  public Turn(Board board, Player current, Player opponent){
    this.turn = board.getCells().stream().filter(x -> x.belongsTo(current) || x.belongsTo(opponent)).count();
    this.boardState = new State(board, current, opponent);
  }

  public long getTurn() {
    return turn;
  }

  public State getBoardState() {
    return boardState;
  }
}
