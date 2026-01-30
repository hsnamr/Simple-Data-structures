package ics202;

/**
 * Immutable wrapper for int, used where Object container must hold a primitive.
 */
public final class Int extends AbstractObject {

    private final int value;

    public Int(int value) {
        this.value = value;
    }

    public int intValue() {
        return value;
    }

    @Override
    protected int compareTo(MyComparable other) {
        if (other instanceof Int i) {
            return Integer.compare(value, i.value);
        }
        return getClass().getName().compareTo(other.getClass().getName());
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
