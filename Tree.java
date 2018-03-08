package ics202;

public interface Tree extends Container
{

    public abstract Object getKey();

    public abstract Tree getSubtree(int i);

    public abstract boolean isLeaf();

    public abstract int getDegree();

    public abstract int getHeight();

    public abstract void depthFirstTraversal(PrePostVisitor prepostvisitor);

    public abstract void breadthFirstTraversal(Visitor visitor);

}