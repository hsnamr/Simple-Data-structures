package ics202;

public interface PrePostVisitor {

    void preVisit(Object obj);

    void inVisit(Object obj);

    void postVisit(Object obj);

    boolean isDone();
}
