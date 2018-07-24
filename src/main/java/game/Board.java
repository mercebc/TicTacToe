package game;

import players.Player;

import java.util.ArrayList;
import java.util.List;

public class Board {

  public static final int size = 3;
  public static final int capacity = size*size;

  private ArrayList<Cell> cells = new ArrayList<>();

  private ArrayList<Line> allLines = new ArrayList<>();

  public Board() {

    for (int i = 0; i < this.capacity; i++) {
      this.cells.add(new Cell());
    }

    setRows();
    setColumns();
    setDiagonals();

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

  private void setRows(){

    for(int i=0; i < capacity; i = i + size){
      ArrayList<Cell> row = new ArrayList<>(size);
      for(int j=i; j < i + size; j++){
        row.add(getCell(j));
      }

      allLines.add(new Line(row));
    }

  }

  private void setColumns(){

    for(int i=0; i < size; i++){
      ArrayList<Cell> column = new ArrayList<>(size);
      for(int j=i; j< capacity;j = j + size){
        column.add(getCell(j));
      }
      allLines.add(new Line(column));
    }

  }

  private void setDiagonals(){

    ArrayList<Cell> firstDiagonal = new ArrayList<>(size);
    ArrayList<Cell> secondDiagonal = new ArrayList<>(size);

    for(int i=0; i < capacity; i = i + size + 1){
      firstDiagonal.add(getCell(i));
    }
    allLines.add(new Line(firstDiagonal));

    for(int i=size - 1; i < capacity - 1; i = i + size - 1){
      secondDiagonal.add(getCell(i));
    }
    allLines.add(new Line(secondDiagonal));

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

  public void clearBoard(){
    for (int i = 0; i < this.capacity; i++) {
      this.getCell(i).setValue(" ");
    }
  }

}