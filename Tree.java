package ics202;

public interface Tree extends Container {

    Object getKey();

    Tree getSubtree(int i);

    boolean isLeaf();

    int getDegree();

    int getHeight();

    void depthFirstTraversal(PrePostVisitor prepostvisitor);

    void breadthFirstTraversal(Visitor visitor);
}
