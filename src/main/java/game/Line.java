package game;

import players.Player;

import java.util.ArrayList;


public class Line {

  private ArrayList<Cell> line = new ArrayList<>();

  public Line(ArrayList<Cell> cells) {
    for (Cell cell: cells) {
      this.line.add(cell);
    }
  }

  public boolean allLine(Player player){
    return this.line.stream().allMatch(t -> t.belongsTo(player));
  }

}