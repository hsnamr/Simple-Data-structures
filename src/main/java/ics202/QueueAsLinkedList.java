package ics202;

import java.util.NoSuchElementException;

public class QueueAsLinkedList extends AbstractContainer implements Queue {

    protected final MyLinkedList list = new MyLinkedList();

    @Override
    public void purge() {
        list.purge();
        count = 0;
    }

    @Override
    public Object getHead() {
        if (count == 0) {
            throw new ContainerEmptyException();
        }
        return list.getFirst();
    }

    @Override
    public void enqueue(Object obj) {
        list.append(obj);
        count++;
    }

    @Override
    public Object dequeue() {
        if (count == 0) {
            throw new ContainerEmptyException();
        }
        Object obj = list.getFirst();
        list.extractFirst();
        count--;
        return obj;
    }

    @Override
    public Enumeration getEnumeration() {
        return new Enumeration() {

            private MyLinkedList.Element position = list.getHead();

            @Override
            public boolean hasMoreElements() {
                return position != null;
            }

            @Override
            public Object nextElement() {
                if (position == null) {
                    throw new NoSuchElementException();
                }
                Object obj = position.getDatum();
                position = position.getNext();
                return obj;
            }
        };
    }

    @Override
    protected int compareTo(MyComparable comparable) {
        throw new MethodNotImplemented();
    }
}
