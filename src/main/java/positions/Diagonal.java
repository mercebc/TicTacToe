package positions;

import java.util.HashMap;

public class Diagonal{
  private HashMap<Integer, Integer> position;

  public Diagonal(){

    position = new HashMap<Integer, Integer>();

    position.put(0, 8);
    position.put(1, 6);
    position.put(2, 2);
    position.put(3, 0);

  }

  public int getPosition(int i){
    return position.get(i);
  }

}
