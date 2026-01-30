package ics202;

import java.util.NoSuchElementException;

public class StackAsLinkedList extends AbstractContainer implements Stack {

    protected final MyLinkedList list = new MyLinkedList();

    @Override
    public void purge() {
        list.purge();
        count = 0;
    }

    @Override
    public void push(Object obj) {
        list.prepend(obj);
        count++;
    }

    @Override
    public Object pop() {
        if (count == 0) {
            throw new ContainerEmptyException();
        }
        Object obj = list.getFirst();
        list.extractFirst();
        count--;
        return obj;
    }

    @Override
    public Object getTop() {
        if (count == 0) {
            throw new ContainerEmptyException();
        }
        return list.getFirst();
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
