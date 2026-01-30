package ics202;

import java.util.NoSuchElementException;

public class DoublyLinkedList {

    protected Element head;
    protected Element tail;

    public void purge() {
        head = null;
        tail = null;
    }

    public Element getHead() {
        return head;
    }

    public Element getTail() {
        return tail;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Object getFirst() {
        if (head == null) {
            throw new ListEmptyException();
        }
        return head.datum;
    }

    public Object getLast() {
        if (tail == null) {
            throw new ListEmptyException();
        }
        return tail.datum;
    }

    public void prepend(Object item) {
        Element tmp = new Element(item, head, null);
        if (head != null) {
            head.previous = tmp;
            head = tmp;
        } else {
            head = tail = tmp;
        }
    }

    public void append(Object item) {
        Element tmp = new Element(item, null, tail);
        if (head != null) {
            tail.next = tmp;
            tail = tmp;
        } else {
            head = tail = tmp;
        }
    }

    public void assign(DoublyLinkedList dlist) {
        if (dlist != this) {
            purge();
            for (Element ptr = dlist.head; ptr != null; ptr = ptr.next) {
                append(ptr.datum);
            }
        }
    }

    public void extractFirst() {
        if (head == null) {
            throw new IllegalArgumentException("item not found");
        }
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.previous = null;
        }
    }

    public void extractLast() {
        if (tail == null) {
            throw new IllegalArgumentException("item not found");
        }
        tail = tail.previous;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
    }

    @Override
    public String toString() {
        if (head == null) {
            return "{}";
        }
        StringBuilder s = new StringBuilder("{");
        Element e = head;
        s.append(e.datum);
        while (e != tail) {
            e = e.next;
            s.append(",").append(e.datum);
        }
        return s.append("}").toString();
    }

    public Element find(Object obj) {
        if (isEmpty()) {
            return null;
        }
        Element element = head;
        while (element != null && !element.datum.equals(obj)) {
            element = element.next;
        }
        return element;
    }

    public void extract(Object obj) {
        Element element = head;
        while (element != null && !element.datum.equals(obj)) {
            element = element.next;
        }
        if (element == null) {
            throw new IllegalArgumentException("item not found");
        }
        if (element.previous != null) {
            element.previous.next = element.next;
        } else {
            head = element.next;
        }
        if (element.next != null) {
            element.next.previous = element.previous;
        } else {
            tail = element.previous;
        }
    }

    public final class Element {

        Object datum;
        Element next;
        Element previous;

        Element(Object datum, Element next, Element previous) {
            this.datum = datum;
            this.next = next;
            this.previous = previous;
        }

        public Object getDatum() {
            return datum;
        }

        public Element getNext() {
            return next;
        }

        public Element getPrevious() {
            return previous;
        }

        public void insertAfter(Object obj) {
            Element element = new Element(obj, this.next, this);
            if (this.next != null) {
                this.next.previous = element;
            } else {
                tail = element;
            }
            this.next = element;
        }

        public void insertBefore(Object obj) {
            Element element = new Element(obj, this, this.previous);
            if (this.previous != null) {
                this.previous.next = element;
            } else {
                head = element;
            }
            this.previous = element;
        }

        public void extract() {
            if (previous != null) {
                previous.next = next;
            } else {
                head = next;
            }
            if (next != null) {
                next.previous = previous;
            } else {
                tail = previous;
            }
            previous = null;
            next = null;
        }
    }
}
