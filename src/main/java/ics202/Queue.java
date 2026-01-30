package ics202;

public interface Queue extends Container {

    Object getHead();

    void enqueue(Object obj);

    Object dequeue();
}
