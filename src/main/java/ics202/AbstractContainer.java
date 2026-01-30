package ics202;

import java.util.function.Consumer;

public abstract class AbstractContainer extends AbstractObject implements Container {

    protected int count;

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return getCount() == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public void accept(Visitor visitor) {
        var enumeration = getEnumeration();
        while (enumeration.hasMoreElements() && !visitor.isDone()) {
            visitor.visit(enumeration.nextElement());
        }
    }

    /** Creates a Visitor that applies the given action to each element (higher-order function). */
    public static Visitor forEach(Consumer<Object> action) {
        return new AbstractVisitor() {
            @Override
            public void visit(Object obj) {
                action.accept(obj);
            }
        };
    }

    @Override
    public String toString() {
        var buffer = new StringBuilder();
        var comma = new boolean[]{ false };
        accept(forEach(obj -> {
            if (comma[0]) buffer.append(", ");
            buffer.append(obj);
            comma[0] = true;
        }));
        return "{" + buffer + "}";
    }

    public abstract void purge();

    public abstract Enumeration getEnumeration();
}
