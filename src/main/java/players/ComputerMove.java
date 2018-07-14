package players;

import game.State;

public interface ComputerMove {

  int compute(Player opponent, State state);

}
