package ics202;

public interface Stack extends Container {

    Object getTop();

    void push(Object obj);

    Object pop();
}
