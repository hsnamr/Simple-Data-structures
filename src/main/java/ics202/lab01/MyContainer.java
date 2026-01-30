package ics202.lab01;

import ics202.*;

public class MyContainer extends AbstractContainer {

    private static final int SIZE = 100;
    protected final MyComparable[] list = new MyComparable[SIZE];

    @Override
    public boolean isFull() {
        return count == SIZE;
    }

    @Override
    public void purge() {
        for (int i = 0; i < count; i++) {
            list[i] = null;
        }
        count = 0;
    }

    @Override
    public Enumeration getEnumeration() {
        return new Enumeration() {

            private int pos = 0;

            @Override
            public boolean hasMoreElements() {
                return pos < count;
            }

            @Override
            public Object nextElement() {
                MyComparable element = list[pos];
                pos++;
                return element;
            }
        };
    }

    @Override
    protected int compareTo(MyComparable object) {
        throw new MethodNotImplemented();
    }

    public void insert(MyComparable object) {
        if (!isFull()) {
            list[count] = object;
            count++;
        }
    }
}
