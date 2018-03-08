package ics202;

public interface Queue extends Container
{

    public abstract Object getHead();

    public abstract void enqueue(Object obj);

    public abstract Object dequeue();

}