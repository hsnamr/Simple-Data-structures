package ics202;


public abstract class AbstractHashTable extends AbstractSearchableContainer
    implements HashTable
{

    public abstract int getLength();

    protected final int f(Object obj)
    {
        return obj.hashCode();
    }

    protected final int g(int i)
    {
        return Math.abs(i) % getLength();
    }

    protected final int h(Object obj)
    {
        return g(f(obj));
    }

    public final double getLoadFactor()
    {
        return (double)getCount() / (double)getLength();
    }

}
