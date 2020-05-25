import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    CharacterComparator offByN = new OffByN(5);

    @Test
    public void TestequalChar(){
        assertTrue(offByN.equalChars('a','f'));
        assertTrue(offByN.equalChars('f', 'a'));
        assertFalse(offByN.equalChars('f', 'h'));

    }
}
