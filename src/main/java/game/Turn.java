package game;

import players.Player;

public class Turn{
  long turn;
  State boardState;
  Mapping mapping;

  public Turn(Board board, Player current, Player opponent){
    this.turn = board.getCells().stream().filter(x -> x.getValue().equals(current.getSymbol()) || x.getValue().equals(opponent.getSymbol())).count();
    this.boardState = new State(board, current, opponent);
    this.mapping = new Mapping();
  }

  public long getTurn() {
    return turn;
  }

  public State getBoardState() {
    return boardState;
  }

  public Mapping getMapping() {
    return mapping;
  }
}
