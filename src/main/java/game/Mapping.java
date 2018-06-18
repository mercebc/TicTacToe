package game;

import java.util.HashMap;

public class Mapping{
  HashMap<Integer, Integer> corner = new HashMap<Integer, Integer>();
  HashMap<Integer, Integer> edge = new HashMap<Integer, Integer>();
  HashMap<Integer, Integer> diagonal = new HashMap<Integer, Integer>();

  public Mapping() {
    corner.put(0, 0);
    corner.put(1, 2);
    corner.put(2, 6);
    corner.put(3, 8);

    edge.put(0, 1);
    edge.put(1, 3);
    edge.put(2, 5);
    edge.put(3, 7);

    diagonal.put(0, 8);
    diagonal.put(1, 6);
    diagonal.put(2, 2);
    diagonal.put(3, 0);
  }

  public int getMappingCorner(int i){
    return corner.get(i);
  }

  public int getMappingEdge(int i){
    return edge.get(i);
  }

  public int getMappingCenter(){
    return 4;
  }

  public int getMappingDiagonal(int i){
    return diagonal.get(i);
  }

}