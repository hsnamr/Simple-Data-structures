package ics202;

import ics202.lab01.MyContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyContainerTest {

    MyContainer container;

    @BeforeEach
    void setUp() {
        container = new MyContainer();
    }

    @Test void isEmptyInitially() {
        assertTrue(container.isEmpty());
        assertEquals(0, container.getCount());
    }

    @Test void insertIncreasesCount() {
        container.insert(new Int(1));
        assertEquals(1, container.getCount());
        container.insert(new Int(2));
        assertEquals(2, container.getCount());
    }

    @Test void isFullAtCapacity() {
        for (int i = 0; i < 100; i++) {
            container.insert(new Int(i));
        }
        assertTrue(container.isFull());
        assertEquals(100, container.getCount());
        container.insert(new Int(100)); // no-op when full
        assertEquals(100, container.getCount());
    }

    @Test void purgeClearsContainer() {
        container.insert(new Int(1));
        container.purge();
        assertTrue(container.isEmpty());
        assertEquals(0, container.getCount());
    }

    @Test void enumerationIteratesInsertOrder() {
        container.insert(new Int(10));
        container.insert(new Int(20));
        var e = container.getEnumeration();
        assertEquals(10, ((Int) e.nextElement()).intValue());
        assertEquals(20, ((Int) e.nextElement()).intValue());
        assertFalse(e.hasMoreElements());
    }
}
