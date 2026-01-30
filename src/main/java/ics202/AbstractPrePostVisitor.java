package ics202;

public abstract class AbstractPrePostVisitor implements PrePostVisitor {

    @Override
    public void preVisit(Object obj) {
    }

    @Override
    public void inVisit(Object obj) {
    }

    @Override
    public void postVisit(Object obj) {
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
