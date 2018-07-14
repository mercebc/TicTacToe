package positions;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CenterTest {


  Center center = new Center();

  HashMap<String, Integer> position = new HashMap<>();

  @Before
  public void setUp(){

    position.put("center", 4);

  }

  @Test
  public void getCenterPosition() {
    assertThat(center.getPosition("center"), is(4));
  }

}
