package game;

import players.Player;

import java.util.ArrayList;


public class Line {

  private ArrayList<Cell> line = new ArrayList<>();

  public Line(Cell a, Cell b, Cell c) {
    this.line.add(a);
    this.line.add(b);
    this.line.add(c);
  }

  public boolean allLine(Player player){
    return this.line.stream().allMatch(t -> t.belongsTo(player));
  }

}