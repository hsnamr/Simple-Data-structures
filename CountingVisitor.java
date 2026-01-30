package ics202;

public class CountingVisitor extends AbstractVisitor {

    private int count;

    @Override
    public void visit(Object obj) {
        count++;
    }

    public int getCount() {
        return count;
    }
}
