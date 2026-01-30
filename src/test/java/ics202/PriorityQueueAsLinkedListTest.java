package ics202;

import ics202.lab04.PriorityQueueAsLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueAsLinkedListTest {

    PriorityQueueAsLinkedList pq;

    @BeforeEach
    void setUp() {
        pq = new PriorityQueueAsLinkedList();
    }

    @Test void isEmptyInitially() {
        assertTrue(pq.isEmpty());
    }

    @Test void enqueueInsertsByPriority() {
        pq.enqueue(new Int(5));
        pq.enqueue(new Int(2));
        pq.enqueue(new Int(8));
        assertEquals(3, pq.getCount());
        var e = pq.getEnumeration();
        assertEquals(2, ((Int) e.nextElement()).intValue());
        assertEquals(5, ((Int) e.nextElement()).intValue());
        assertEquals(8, ((Int) e.nextElement()).intValue());
    }

    @Test void dequeueReturnsFIFOWithinPriority() {
        pq.enqueue(new Int(1));
        pq.enqueue(new Int(1));
        assertEquals(1, ((Int) pq.dequeue()).intValue());
        assertEquals(1, ((Int) pq.dequeue()).intValue());
    }
}
