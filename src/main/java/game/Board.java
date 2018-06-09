package game;

import players.Player;

import java.util.ArrayList;
import java.util.List;

public class Board {

  private List<Cell> cells = new ArrayList<>();
  private double capacity;

  private List<Line> allLines = new ArrayList<>();

  public Board() {
    int size = 3;
    this.capacity = Math.pow(size, 2);
    for (int i = 0; i < this.capacity; i++) {
      this.cells.add(new Cell());
    }

    this.allLines.add(new Line(getCell(0),getCell(1),getCell(2)));
    this.allLines.add(new Line(getCell(3),getCell(4),getCell(5)));
    this.allLines.add(new Line(getCell(6),getCell(7),getCell(8)));

    this.allLines.add(new Line(getCell(0),getCell(3),getCell(6)));
    this.allLines.add(new Line(getCell(1),getCell(4),getCell(7)));
    this.allLines.add(new Line(getCell(2),getCell(5),getCell(8)));

    this.allLines.add(new Line(getCell(0),getCell(4),getCell(8)));
    this.allLines.add(new Line(getCell(2),getCell(4),getCell(6)));

  }

  public Cell getCell(int position) {
    return this.cells.get(position);
  }

  public void setCell(int position, String symbol) {
    this.cells.get(position).setValue(symbol);
  }

  public double getCapacity() { return capacity; }

  public List<Cell> getCells() {
    return cells;
  }

  public boolean checkLinePlayer(Player player) {
    for ( Line line : allLines) {
      if(line.allLine(player)){
        return true;
      }
    }
    return false;
  }

  private boolean checkLine(Player player2, Player player1){
    return (checkLinePlayer(player1) || checkLinePlayer(player2));
  }

  public boolean threeInLine(Player player1, Player player2) {//check if there's three in line
    return checkLine(player1, player2);
  }

  public boolean tie(Player player1, Player player2) {
    return (cells.stream().allMatch(t -> (t.belongsTo(player1) || t.belongsTo(player2))) && !threeInLine(player1,player2));
  }
}