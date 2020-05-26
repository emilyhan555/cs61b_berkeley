import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void testFlik() {
        assertTrue(Flik.isSameNumber(50, 50));
        assertFalse(Flik.isSameNumber(40, 50));
        assertTrue(Flik.isSameNumber(128, 128));
    }
}
