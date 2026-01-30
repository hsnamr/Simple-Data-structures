package ics202;

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
        Enumeration enumeration = getEnumeration();
        while (enumeration.hasMoreElements() && !visitor.isDone()) {
            visitor.visit(enumeration.nextElement());
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        AbstractVisitor visitor = new AbstractVisitor() {
            private boolean comma;

            @Override
            public void visit(Object obj) {
                if (comma) {
                    buffer.append(", ");
                }
                buffer.append(obj);
                comma = true;
            }
        };
        accept(visitor);
        return "{" + buffer + "}";
    }

    public abstract void purge();

    public abstract Enumeration getEnumeration();
}
