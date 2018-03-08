package ics202;

import java.util.NoSuchElementException;

public abstract class AbstractTree extends AbstractContainer implements Tree
{
   
    public abstract Object getKey();

    public abstract Tree getSubtree(int i);

    public abstract boolean isLeaf();

    public abstract int getDegree();

    protected class TreeEnumeration implements Enumeration
    {
		protected Stack stack;

        public TreeEnumeration()
        {
            stack = new StackAsLinkedList();
            if(!isEmpty())
                stack.push(AbstractTree.this);
        }
        
        public boolean hasMoreElements()
        {
            return !stack.isEmpty();
        }

        public Object nextElement()
        {
            if(stack.isEmpty())
                throw new NoSuchElementException();
                
            Tree tree = (Tree)stack.pop();
            for(int i = tree.getDegree() - 1; i >= 0; i--)
            {
                Tree subtree = tree.getSubtree(i);
                if(!subtree.isEmpty())
                    stack.push(subtree);
            }

            return tree.getKey();
        } 
    }

	public Enumeration getEnumeration()
    {
        return new TreeEnumeration();
    }

    public void depthFirstTraversal(PrePostVisitor prepostvisitor)
    {
        if(prepostvisitor.isDone())
            return;
        if(!isEmpty())
        {
            prepostvisitor.preVisit(getKey());
            for(int i = 0; i < getDegree(); i++)
                getSubtree(i).depthFirstTraversal(prepostvisitor);

            prepostvisitor.postVisit(getKey());
        }
    }

    public void breadthFirstTraversal(Visitor visitor)
    {
        QueueAsLinkedList queueaslinkedlist = new QueueAsLinkedList();
        if(!isEmpty())
            queueaslinkedlist.enqueue(this);
        while(!queueaslinkedlist.isEmpty() && !visitor.isDone()) 
        {
            Tree tree = (Tree)queueaslinkedlist.dequeue();
            visitor.visit(tree.getKey());
            
            for(int i = 0; i < tree.getDegree(); i++)
            {
                Tree subtree = tree.getSubtree(i);
                if(!subtree.isEmpty())
                    queueaslinkedlist.enqueue(subtree);
            }

        }
    }

    public void accept(Visitor visitor)
    {
        depthFirstTraversal(new PreOrder(visitor));
    }

   
    public int getHeight()
    {
        if(isEmpty())
            return -1;
        int i = -1;
        for(int j = 0; j < getDegree(); j++)
            i = Math.max(i, getSubtree(j).getHeight());

        return i + 1;
    }

    public int getCount()
    {
        if(isEmpty())
            return 0;
        int i = 1;
        for(int j = 0; j < getDegree(); j++)
            i += getSubtree(j).getCount();

        return i;
    }
}