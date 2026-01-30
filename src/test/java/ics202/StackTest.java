package ics202;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Stack (StackAsLinkedList)")
class StackTest {

    Stack stack;

    @BeforeEach
    void setUp() {
        stack = new StackAsLinkedList();
    }

    @Test void isEmptyInitially() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.getCount());
    }

    @Test void pushIncrementsCount() {
        stack.push("a");
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.getCount());
        stack.push("b");
        assertEquals(2, stack.getCount());
    }

    @Test void getTopReturnsLastPushed() {
        stack.push("a");
        assertEquals("a", stack.getTop());
        stack.push("b");
        assertEquals("b", stack.getTop());
    }

    @Test void popReturnsInLIFOOrder() {
        stack.push("a");
        stack.push("b");
        stack.push("c");
        assertEquals("c", stack.pop());
        assertEquals("b", stack.pop());
        assertEquals("a", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test void popDecrementsCount() {
        stack.push("a");
        stack.push("b");
        stack.pop();
        assertEquals(1, stack.getCount());
    }

    @Test void getTopOnEmptyThrows() {
        assertThrows(ContainerEmptyException.class, () -> stack.getTop());
    }

    @Test void popOnEmptyThrows() {
        assertThrows(ContainerEmptyException.class, () -> stack.pop());
    }

    @Test void purgeClearsStack() {
        stack.push("a");
        stack.push("b");
        stack.purge();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.getCount());
    }

    @Test void enumerationIteratesLIFO() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        var e = stack.getEnumeration();
        assertTrue(e.hasMoreElements());
        assertEquals(3, e.nextElement());
        assertEquals(2, e.nextElement());
        assertEquals(1, e.nextElement());
        assertFalse(e.hasMoreElements());
    }
}
