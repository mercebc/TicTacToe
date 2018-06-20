package game;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MappingTest {

  HashMap<Integer, Integer> diagonal = new HashMap<Integer, Integer>();

  Mapping mapping = new Mapping();

  @Before
  public void setUp(){

    diagonal.put(0, 8);
    diagonal.put(1, 6);
    diagonal.put(2, 2);
    diagonal.put(3, 0);
  }

  @Test
  public void getMappedCenter() {
    assertThat(mapping.getMappingCenter(), is(4));
  }

  @Test
  public void getMappedDiagonal0() {
    assertThat(mapping.getMappingDiagonal(0), is(8));
  }

}
