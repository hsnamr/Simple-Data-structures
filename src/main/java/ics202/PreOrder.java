package ics202;

/**
 * Pre-order traversal adapter: calls visitor.visit() in preVisit.
 */
public class PreOrder extends AbstractPrePostVisitor {

    private final Visitor visitor;

    public PreOrder(Visitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public void preVisit(Object obj) {
        visitor.visit(obj);
    }

    @Override
    public boolean isDone() {
        return visitor.isDone();
    }
}
