package ics202;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    DoublyLinkedList list;

    @BeforeEach
    void setUp() {
        list = new DoublyLinkedList();
    }

    @Test void isEmptyInitially() {
        assertTrue(list.isEmpty());
    }

    @Test void prependAddsToHead() {
        list.prepend("a");
        list.prepend("b");
        assertEquals("b", list.getFirst());
        assertEquals("a", list.getLast());
    }

    @Test void appendAddsToTail() {
        list.append("a");
        list.append("b");
        assertEquals("a", list.getFirst());
        assertEquals("b", list.getLast());
    }

    @Test void getFirstOnEmptyThrows() {
        assertThrows(ListEmptyException.class, () -> list.getFirst());
    }

    @Test void extractRemovesElement() {
        list.append("a");
        list.append("b");
        list.append("c");
        list.extract("b");
        assertNull(list.find("b"));
    }

    @Test void findReturnsElementOrNull() {
        list.append("x");
        assertNotNull(list.find("x"));
        assertNull(list.find("y"));
    }

    @Test void purgeClearsList() {
        list.append("a");
        list.purge();
        assertTrue(list.isEmpty());
    }

    @Test void toStringFormatsCorrectly() {
        list.append("a");
        list.append("b");
        assertEquals("{a,b}", list.toString());
    }
}
