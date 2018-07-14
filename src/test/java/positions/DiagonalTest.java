package positions;

import org.junit.Before;
import org.junit.Test;
import positions.Diagonal;

import java.util.HashMap;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DiagonalTest {

  Diagonal diagonal = new Diagonal();

  HashMap<Integer, Integer> position = new HashMap<Integer, Integer>();

  @Before
  public void setUp(){

    position.put(0, 8);
    position.put(1, 6);
    position.put(2, 2);
    position.put(3, 0);
  }

  @Test
  public void getPosition0() {
    assertThat(diagonal.getPosition(0), is(8));
  }

  @Test
  public void getPosition1() {
    assertThat(diagonal.getPosition(1), is(6));
  }

  @Test
  public void getPosition2() {
    assertThat(diagonal.getPosition(2), is(2));
  }

  @Test
  public void getPosition3() {
    assertThat(diagonal.getPosition(3), is(0));
  }

}
