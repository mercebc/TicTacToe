package Game;

import Players.Player;

import java.util.ArrayList;


public class Line {

  private ArrayList<String> line = new ArrayList<>();

  public Line(String a, String b, String c) {
    this.line.add(a);
    this.line.add(b);
    this.line.add(c);
  }

  public ArrayList<String> getLine() {
    return line;
  }

  public boolean AllLine(Player player){
    if (this.line.stream().allMatch(t -> (t.contains(player.getSymbol())))){
      return true;
    }else{
      return false;
    }
  }

}