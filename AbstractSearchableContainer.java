package ics202;

public abstract class AbstractSearchableContainer extends AbstractContainer
    implements SearchableContainer
{

    public abstract boolean isMember(MyComparable comparable);

    public abstract void insert(MyComparable comparable);

    public abstract void withdraw(MyComparable comparable);

    public abstract MyComparable find(MyComparable comparable);

}