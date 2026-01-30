package ics202;

public abstract class AbstractObject implements MyComparable {

    @Override
    public final boolean isLT(MyComparable object) {
        return compare(object) < 0;
    }

    @Override
    public final boolean isLE(MyComparable object) {
        return compare(object) <= 0;
    }

    @Override
    public final boolean isGT(MyComparable object) {
        return compare(object) > 0;
    }

    @Override
    public final boolean isGE(MyComparable object) {
        return compare(object) >= 0;
    }

    @Override
    public final boolean isEQ(MyComparable object) {
        return compare(object) == 0;
    }

    @Override
    public final boolean isNE(MyComparable object) {
        return compare(object) != 0;
    }

    @Override
    public final boolean equals(Object object) {
        return object instanceof MyComparable other && isEQ(other);
    }

    @Override
    public final int compare(MyComparable arg) {
        if (getClass() == arg.getClass()) {
            return compareTo(arg);
        }
        return getClass().getName().compareTo(arg.getClass().getName());
    }

    protected abstract int compareTo(MyComparable arg);
}
