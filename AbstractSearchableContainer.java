package ics202;

public abstract class AbstractSearchableContainer extends AbstractContainer implements SearchableContainer {

    @Override
    public abstract boolean isMember(MyComparable comparable);

    @Override
    public abstract void insert(MyComparable comparable);

    @Override
    public abstract void withdraw(MyComparable comparable);

    @Override
    public abstract MyComparable find(MyComparable comparable);
}
