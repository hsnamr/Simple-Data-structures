package ics202;

public class BinaryTree extends AbstractTree
{

	protected Object key;
    protected BinaryTree left;
    protected BinaryTree right;

    public BinaryTree(Object obj, BinaryTree left, BinaryTree right)
    {
        key = obj;
        this.left = left;
        this.right = right;
    }

    public BinaryTree()
    {
        this(null, null, null);
    }

    public BinaryTree(Object obj)
    {
        this(obj, new BinaryTree(), new BinaryTree());
    }

    public void purge()
    {
        key = null;
        left = null;
        right = null;
    }

    public boolean isLeaf()
    {
        return !isEmpty() && left.isEmpty() && right.isEmpty();
    }

    public int getDegree()
    {
        return !isEmpty() ? 2 : 0;
    }

    public boolean isEmpty()
    {
        return key == null;
    }
    public boolean isFull()
    {	  if(isEmpty())
             return true;
             else if(left.getHeight() !=right.getHeight())
                     return false;      	    
   	   return left.isFull() && right.isFull();   	   
    }
    public Object getKey()
    {
        if(isEmpty())
            throw new InvalidOperationException();
        else
            return key;
    }
    
    
    public Tree getSubtree(int i)
    {
        if (i<0 || i>1)
            throw new IndexOutOfBoundsException();
        if(i == 0)
            return getLeft();
        else
            return getRight();
    }

    public BinaryTree getLeft()
    {
        if(isEmpty())
            throw new InvalidOperationException();
        else
            return left;
    }

    public BinaryTree getRight()
    {
        if(isEmpty())
            throw new InvalidOperationException();
        else
            return right;
    }

   public void attachKey(Object obj)
    {
        if(!isEmpty())
            throw new InvalidOperationException();
        else
        {
            key = obj;
            left = new BinaryTree();
            right = new BinaryTree();
        }
    }

    public Object detachKey()
    {
        if(!isLeaf())
            throw new InvalidOperationException();
        else
        {
            Object obj = key;
            key = null;
            left = null;
            right = null;
            return obj;
        }
    }
    public int getCount(){
    	if(isEmpty())
    	   return 0;
    	else
    		return 1+left.getCount()+right.getCount();
    		}   
    public int leavesCount(){
    	if(isEmpty())
    	   return 0;
    	
    	else if(isLeaf()) return 1;
    	
    	else
    		return left.leavesCount()+right.leavesCount();
    		}   
    
    public void depthFirstTraversal(PrePostVisitor prepostvisitor)
    {
        if(!isEmpty())
        {
            prepostvisitor.preVisit(key);
            left.depthFirstTraversal(prepostvisitor);
            prepostvisitor.inVisit(key);
            right.depthFirstTraversal(prepostvisitor);
            prepostvisitor.postVisit(key);
        }
    }
    
    protected int compareTo(MyComparable comparable)
	{  
        BinaryTree binarytree = (BinaryTree)comparable;
        if(isEmpty())
            return !binarytree.isEmpty() ? -1 : 0;
        if(binarytree.isEmpty())
            return 1;
        int i = ((MyComparable)getKey()).compare((MyComparable)binarytree.getKey());
        if(i == 0)
            i = getLeft().compareTo(((MyComparable) (binarytree.getLeft())));
        if(i == 0)
            i = getRight().compareTo(((MyComparable) (binarytree.getRight())));
        return i;
    }  
}