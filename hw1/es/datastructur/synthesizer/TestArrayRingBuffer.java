package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 */

public class TestArrayRingBuffer {
    @Test
    public void testEnqueue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        for (int i = 0; i < 8; i++) {
            arb.enqueue(i);
        }

        assertEquals(10, arb.capacity());
        assertEquals(8, arb.fillCount());
        assertEquals((int) arb.peek(),0);

        arb.dequeue();
        assertEquals((int) arb.peek(), 1);
    }

    @Test
    public void testIteration() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        ArrayRingBuffer<Integer> other = new ArrayRingBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            arb.enqueue(i);
            other.enqueue(i);
        }
        assertEquals(true, arb.equals(other));
    }
}
