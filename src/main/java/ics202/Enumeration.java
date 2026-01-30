package ics202;

/**
 * Legacy-style enumeration over a collection of elements.
 * Prefer {@link java.util.Iterator} or {@link java.util.stream.Stream} for new code.
 */
public interface Enumeration {

    boolean hasMoreElements();

    Object nextElement();
}
