package positions;

import java.util.HashMap;

public class Center{
  HashMap<String, Integer> position = new HashMap<>();

  public Center() {
    position.put("center", 4);
  }

  public int getPosition(String center){
    return position.get(center);
  }

}