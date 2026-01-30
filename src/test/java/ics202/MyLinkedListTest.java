package ics202;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    MyLinkedList list;

    @BeforeEach
    void setUp() {
        list = new MyLinkedList();
    }

    @Test void isEmptyInitially() {
        assertTrue(list.isEmpty());
        assertNull(list.getHead());
        assertNull(list.getTail());
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

    @Test void getLastOnEmptyThrows() {
        assertThrows(ListEmptyException.class, () -> list.getLast());
    }

    @Test void extractRemovesElement() {
        list.append("a");
        list.append("b");
        list.append("c");
        list.extract("b");
        assertEquals(2, list.find("a") != null && list.find("c") != null ? 2 : 0);
        assertNull(list.find("b"));
    }

    @Test void extractFirstRemovesHead() {
        list.append("a");
        list.append("b");
        list.extractFirst();
        assertEquals("b", list.getFirst());
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
        assertNull(list.getHead());
    }

    @Test void assignCopiesOtherList() {
        list.append("a");
        list.append("b");
        var other = new MyLinkedList();
        other.append("c");
        list.assign(other);
        assertEquals("c", list.getFirst());
        assertEquals(1, list.getHead() != null ? 1 : 0);
    }
}
