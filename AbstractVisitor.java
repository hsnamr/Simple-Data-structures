package ics202;

public abstract class AbstractVisitor implements Visitor {

    @Override
    public abstract void visit(Object obj);

    @Override
    public boolean isDone() {
        return false;
    }
}
