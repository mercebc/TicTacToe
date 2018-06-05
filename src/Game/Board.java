package Game;

import Players.Player;

import java.util.ArrayList;
import java.util.List;

public class Board {

  private List<String> cells = new ArrayList<>();
  private double capacity;

  private List<Line> allLines = new ArrayList<>();

  public Board() {
    int size = 3;
    this.capacity = Math.pow(size, 2);
    for (int i = 0; i < this.capacity; i++) {
      this.cells.add(" ");
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

  public String getCell(int position) {
    return this.cells.get(position);
  }

  public void setCell(int position, String symbol) {
    this.cells.set(position, symbol);
  }

  public double getCapacity() {
    return capacity;
  }

  public List<String> getCells() {
    return cells;
  }

  public boolean checkLinePlayer(Player player) {
    for ( Line line : allLines) {
      return (line.AllLine(player));
    }
    return false;
  }

  private boolean checkLine(Player player2, Player player1){
    if(checkLinePlayer(player1) || checkLinePlayer(player2)) {
      return true;
    }else{
      return false;
    }
  }

  public boolean threeInLine(Player player1, Player player2) {//check if there's three in line
    return
        checkLine(player1, player2);
  }

  public boolean tie(Player player1, Player player2) {
    if (cells.stream().allMatch(t -> (t.contains(player1.getSymbol()) || t.contains(player2.getSymbol())))){
      return true;
    }else{
      return false;
    }
  }
}