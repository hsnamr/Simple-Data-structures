package ics202;

public class MatchingVisitor extends AbstractVisitor {

    private final Vertex target;
    private boolean found;

    public MatchingVisitor(Vertex target) {
        this.target = target;
    }

    @Override
    public void visit(Object obj) {
        if (obj instanceof Vertex v && v.getNumber() == target.getNumber()) {
            found = true;
        }
    }

    @Override
    public boolean isDone() {
        return found;
    }

    public boolean isFound() {
        return found;
    }
}
