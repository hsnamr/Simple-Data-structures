package ics202;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    Queue queue;

    @BeforeEach
    void setUp() {
        queue = new QueueAsLinkedList();
    }

    @Test void isEmptyInitially() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.getCount());
    }

    @Test void enqueueIncrementsCount() {
        queue.enqueue("a");
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.getCount());
    }

    @Test void getHeadReturnsFirstEnqueued() {
        queue.enqueue("a");
        queue.enqueue("b");
        assertEquals("a", queue.getHead());
    }

    @Test void dequeueReturnsInFIFOOrder() {
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        assertEquals("a", queue.dequeue());
        assertEquals("b", queue.dequeue());
        assertEquals("c", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test void getHeadOnEmptyThrows() {
        assertThrows(ContainerEmptyException.class, () -> queue.getHead());
    }

    @Test void dequeueOnEmptyThrows() {
        assertThrows(ContainerEmptyException.class, () -> queue.dequeue());
    }

    @Test void purgeClearsQueue() {
        queue.enqueue("a");
        queue.purge();
        assertTrue(queue.isEmpty());
    }

    @Test void enumerationIteratesFIFO() {
        queue.enqueue(1);
        queue.enqueue(2);
        var e = queue.getEnumeration();
        assertEquals(1, e.nextElement());
        assertEquals(2, e.nextElement());
        assertFalse(e.hasMoreElements());
    }
}
