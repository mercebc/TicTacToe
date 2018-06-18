package game;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MappingTest {

  HashMap<Integer, Integer> corner = new HashMap<Integer, Integer>();
  HashMap<Integer, Integer> edge = new HashMap<Integer, Integer>();
  HashMap<Integer, Integer> diagonal = new HashMap<Integer, Integer>();

  Mapping mapping = new Mapping();

  @Before
  public void setUp(){
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

  @Test
  public void getMappedCenter() {
    assertThat(mapping.getMappingCenter(), is(4));
  }

  @Test
  public void getMappedCorner2() {
    assertThat(mapping.getMappingCorner(2), is(6));
  }

  @Test
  public void getMappedDCorner1() {
    assertThat(mapping.getMappingCorner(1), is(2));
  }

  @Test
  public void getMappedEdge3() {
    assertThat(mapping.getMappingEdge(3), is(7));
  }

  @Test
  public void getMappedDiagonal0() {
    assertThat(mapping.getMappingDiagonal(0), is(8));
  }

}
