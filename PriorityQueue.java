package ics202;

public interface PriorityQueue extends Container
{

    public abstract void enqueue(MyComparable comparable);

    public abstract MyComparable findMin();

    public abstract MyComparable dequeueMin();

}