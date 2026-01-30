package ics202;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests container accept(Visitor) and enumeration behavior.
 */
class ContainerVisitorTest {

    @Test void stackAcceptVisitorVisitsAllElements() {
        Stack stack = new StackAsLinkedList();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        var sb = new StringBuilder();
        stack.accept(new AbstractVisitor() {
            @Override
            public void visit(Object obj) {
                sb.append(obj);
            }
        });
        assertEquals("cba", sb.toString());
    }

    @Test void queueAcceptVisitorVisitsAllElements() {
        Queue queue = new QueueAsLinkedList();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        var sb = new StringBuilder();
        queue.accept(new AbstractVisitor() {
            @Override
            public void visit(Object obj) {
                sb.append(obj);
            }
        });
        assertEquals("abc", sb.toString());
    }

    @Test void containerToStringFormat() {
        Stack stack = new StackAsLinkedList();
        stack.push(1);
        stack.push(2);
        String s = stack.toString();
        assertTrue(s.startsWith("{"));
        assertTrue(s.endsWith("}"));
        assertTrue(s.contains("2"));
        assertTrue(s.contains("1"));
    }
}
