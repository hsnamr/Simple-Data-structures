package ics202;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    BinaryHeap heap;

    @BeforeEach
    void setUp() {
        heap = new BinaryHeap(16);
    }

    @Test void isEmptyInitially() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.getCount());
    }

    @Test void enqueueIncreasesCount() {
        heap.enqueue(new Int(5));
        assertEquals(1, heap.getCount());
    }

    @Test void findMinReturnsMinimum() {
        heap.enqueue(new Int(3));
        heap.enqueue(new Int(1));
        heap.enqueue(new Int(2));
        assertEquals(1, ((Int) heap.findMin()).intValue());
    }

    @Test void dequeueMinReturnsInAscendingOrder() {
        heap.enqueue(new Int(3));
        heap.enqueue(new Int(1));
        heap.enqueue(new Int(2));
        assertEquals(1, ((Int) heap.dequeueMin()).intValue());
        assertEquals(2, ((Int) heap.dequeueMin()).intValue());
        assertEquals(3, ((Int) heap.dequeueMin()).intValue());
        assertTrue(heap.isEmpty());
    }

    @Test void findMinOnEmptyThrows() {
        assertThrows(ContainerEmptyException.class, () -> heap.findMin());
    }

    @Test void dequeueMinOnEmptyThrows() {
        assertThrows(ContainerEmptyException.class, () -> heap.dequeueMin());
    }

    @Test void isFullWhenCapacityReached() {
        for (int i = 0; i < 16; i++) {
            heap.enqueue(new Int(i));
        }
        assertTrue(heap.isFull());
        assertThrows(ContainerFullException.class, () -> heap.enqueue(new Int(99)));
    }

    @Test void purgeClearsHeap() {
        heap.enqueue(new Int(1));
        heap.purge();
        assertTrue(heap.isEmpty());
    }

    @Test void constructorFromArrayBuildsHeap() {
        var arr = new MyComparable[]{ new Int(5), new Int(2), new Int(8), new Int(1) };
        var h = new BinaryHeap(arr);
        assertEquals(4, h.getCount());
        assertEquals(1, ((Int) h.findMin()).intValue());
    }
}
