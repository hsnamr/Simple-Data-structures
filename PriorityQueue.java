package ics202;

public interface PriorityQueue extends Container {

    void enqueue(MyComparable comparable);

    MyComparable findMin();

    MyComparable dequeueMin();
}
