package ics202;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    BinarySearchTree bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree();
    }

    @Test void isEmptyInitially() {
        assertTrue(bst.isEmpty());
    }

    @Test void insertThenFind() {
        bst.insert(new Int(5));
        bst.insert(new Int(3));
        bst.insert(new Int(7));
        assertEquals(5, ((Int) bst.find(new Int(5))).intValue());
        assertEquals(3, ((Int) bst.find(new Int(3))).intValue());
        assertEquals(7, ((Int) bst.find(new Int(7))).intValue());
    }

    @Test void findOnEmptyReturnsNull() {
        assertNull(bst.find(new Int(1)));
    }

    @Test void findMinFindMax() {
        bst.insert(new Int(5));
        bst.insert(new Int(2));
        bst.insert(new Int(8));
        assertEquals(2, ((Int) bst.findMin()).intValue());
        assertEquals(8, ((Int) bst.findMax()).intValue());
    }

    @Test void isMember() {
        bst.insert(new Int(4));
        assertTrue(bst.isMember(new Int(4)));
        assertFalse(bst.isMember(new Int(99)));
    }

    @Test void duplicateInsertThrows() {
        bst.insert(new Int(1));
        assertThrows(IllegalArgumentException.class, () -> bst.insert(new Int(1)));
    }

    @Test void withdrawRemovesKey() {
        bst.insert(new Int(5));
        bst.insert(new Int(3));
        bst.withdraw(new Int(3));
        assertNull(bst.find(new Int(3)));
        assertEquals(5, ((Int) bst.find(new Int(5))).intValue());
    }

    @Test void getCountAfterInsert() {
        bst.insert(new Int(5));
        bst.insert(new Int(3));
        bst.insert(new Int(7));
        assertEquals(3, bst.getCount());
    }
}
