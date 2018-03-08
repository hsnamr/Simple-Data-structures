package ics202;

public interface Stack extends Container
{

    public abstract Object getTop();

    public abstract void push(Object obj);

    public abstract Object pop();
}