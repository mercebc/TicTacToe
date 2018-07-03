package players;

import game.Mapping;
import game.State;

public interface ComputerMove {

  int compute(Player opponent, State state, Mapping map);

}
