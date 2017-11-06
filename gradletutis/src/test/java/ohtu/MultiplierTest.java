package ohtu;


import static org.junit.Assert.*;
import org.junit.Test;
import ohtu.Multiplier;

public class MultiplierTest {
  @Test

  public void kertominenToimii() {

    Multiplier viisi = new Multiplier(5);

    assertEquals(5, viisi.multipliedBy(1));
    assertEquals(10, viisi.multipliedBy(2));
  }
}
