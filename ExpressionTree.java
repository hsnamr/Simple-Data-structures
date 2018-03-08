package ics202;

public class ExpressionTree extends BinaryTree
{
    public ExpressionTree(Object obj, ExpressionTree leftSubtree, ExpressionTree rightSubtree)
    {
        super(obj, leftSubtree, rightSubtree);
    }

    public ExpressionTree()
    {
        super(null, null, null);
    }

    public ExpressionTree(Object obj)
    {
        this(obj, new ExpressionTree(), new ExpressionTree());
    }

    public void attachTreeToLeft(ExpressionTree tree)
    {
    	left = tree;
    }

	public void attachTreeToRight(ExpressionTree tree)
    {
    	right = tree;
    }
    
}