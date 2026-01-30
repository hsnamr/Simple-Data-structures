package ics202;

public interface SearchTree extends Tree, SearchableContainer {

    MyComparable findMin();

    MyComparable findMax();
}
