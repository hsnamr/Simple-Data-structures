package ics202;

public interface SearchableContainer extends Container {

    boolean isMember(MyComparable object);

    void insert(MyComparable object);

    void withdraw(MyComparable obj);

    MyComparable find(MyComparable object);
}
