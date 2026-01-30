package ics202;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    BinaryTree tree;

    @BeforeEach
    void setUp() {
        tree = new BinaryTree();
    }

    @Test void isEmptyInitially() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.getCount());
    }

    @Test void attachKeyMakesNonEmpty() {
        tree.attachKey("root");
        assertFalse(tree.isEmpty());
        assertEquals("root", tree.getKey());
    }

    @Test void getKeyOnEmptyThrows() {
        assertThrows(InvalidOperationException.class, () -> tree.getKey());
    }

    @Test void getLeftGetRightOnEmptyThrows() {
        assertThrows(InvalidOperationException.class, () -> tree.getLeft());
        assertThrows(InvalidOperationException.class, () -> tree.getRight());
    }

    @Test void isLeafWhenBothChildrenEmpty() {
        tree.attachKey("x");
        assertTrue(tree.isLeaf());
    }

    @Test void getDegreeIsTwoWhenNonEmpty() {
        tree.attachKey("x");
        assertEquals(2, tree.getDegree());
    }

    @Test void getSubtreeReturnsLeftOrRight() {
        tree.attachKey("r");
        assertSame(tree.getLeft(), tree.getSubtree(0));
        assertSame(tree.getRight(), tree.getSubtree(1));
    }

    @Test void getSubtreeOutOfBoundsThrows() {
        // Empty tree: getSubtree(0) delegates to getLeft() which throws InvalidOperationException
        assertThrows(InvalidOperationException.class, () -> tree.getSubtree(0));
        tree.attachKey("r");
        assertThrows(IndexOutOfBoundsException.class, () -> tree.getSubtree(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> tree.getSubtree(2));
    }

    @Test void getHeightEmptyIsMinusOne() {
        assertEquals(-1, tree.getHeight());
    }

    @Test void getHeightSingleNodeIsZero() {
        tree.attachKey("r");
        assertEquals(0, tree.getHeight());
    }

    @Test void purgeClearsTree() {
        tree.attachKey("r");
        tree.purge();
        assertTrue(tree.isEmpty());
    }

    @Test void enumerationIteratesKeys() {
        tree.attachKey("r");
        tree.getLeft().attachKey("l");
        var e = tree.getEnumeration();
        assertTrue(e.hasMoreElements());
        assertEquals("r", e.nextElement());
        assertEquals("l", e.nextElement());
    }
}
