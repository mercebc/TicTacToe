package game;

import java.util.HashMap;

public class Mapping{
  HashMap<Integer, Integer> diagonal = new HashMap<Integer, Integer>();

  public Mapping() {
    diagonal.put(0, 8);
    diagonal.put(1, 6);
    diagonal.put(2, 2);
    diagonal.put(3, 0);
  }

  public int getMappingCenter(){
    return 4;
  }

  public int getMappingDiagonal(int i){
    return diagonal.get(i);
  }

}