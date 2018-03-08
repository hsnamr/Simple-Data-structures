package ics202;

public interface SearchTree extends Tree, SearchableContainer
{

    public abstract MyComparable findMin();

    public abstract MyComparable findMax();
}