package ics202;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTreeTest {

    @Test void defaultConstructorCreatesEmpty() {
        var tree = new ExpressionTree();
        assertTrue(tree.isEmpty());
    }

    @Test void singleArgConstructor() {
        var tree = new ExpressionTree("+");
        assertEquals("+", tree.getKey());
    }

    @Test void threeArgConstructor() {
        var left = new ExpressionTree("a");
        var right = new ExpressionTree("b");
        var tree = new ExpressionTree("*", left, right);
        assertEquals("*", tree.getKey());
        assertSame(left, tree.getLeft());
        assertSame(right, tree.getRight());
    }

    @Test void attachTreeToLeftRight() {
        var tree = new ExpressionTree("root");
        var left = new ExpressionTree("L");
        var right = new ExpressionTree("R");
        tree.attachTreeToLeft(left);
        tree.attachTreeToRight(right);
        assertSame(left, tree.getLeft());
        assertSame(right, tree.getRight());
    }
}
