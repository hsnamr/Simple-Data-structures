package ics202;

import java.util.NoSuchElementException;

public abstract class AbstractTree extends AbstractContainer implements Tree {

    @Override
    public abstract Object getKey();

    @Override
    public abstract Tree getSubtree(int i);

    @Override
    public abstract boolean isLeaf();

    @Override
    public abstract int getDegree();

    protected class TreeEnumeration implements Enumeration {

        protected Stack stack;

        public TreeEnumeration() {
            stack = new StackAsLinkedList();
            if (!isEmpty()) {
                stack.push(AbstractTree.this);
            }
        }

        @Override
        public boolean hasMoreElements() {
            return !stack.isEmpty();
        }

        @Override
        public Object nextElement() {
            if (stack.isEmpty()) {
                throw new NoSuchElementException();
            }
            Tree tree = (Tree) stack.pop();
            for (int i = tree.getDegree() - 1; i >= 0; i--) {
                Tree subtree = tree.getSubtree(i);
                if (!subtree.isEmpty()) {
                    stack.push(subtree);
                }
            }
            return tree.getKey();
        }
    }

    @Override
    public Enumeration getEnumeration() {
        return new TreeEnumeration();
    }

    @Override
    public void depthFirstTraversal(PrePostVisitor prepostvisitor) {
        if (prepostvisitor.isDone()) {
            return;
        }
        if (!isEmpty()) {
            prepostvisitor.preVisit(getKey());
            for (int i = 0; i < getDegree(); i++) {
                getSubtree(i).depthFirstTraversal(prepostvisitor);
            }
            prepostvisitor.postVisit(getKey());
        }
    }

    @Override
    public void breadthFirstTraversal(Visitor visitor) {
        QueueAsLinkedList queue = new QueueAsLinkedList();
        if (!isEmpty()) {
            queue.enqueue(this);
        }
        while (!queue.isEmpty() && !visitor.isDone()) {
            Tree tree = (Tree) queue.dequeue();
            visitor.visit(tree.getKey());
            for (int i = 0; i < tree.getDegree(); i++) {
                Tree subtree = tree.getSubtree(i);
                if (!subtree.isEmpty()) {
                    queue.enqueue(subtree);
                }
            }
        }
    }

    @Override
    public void accept(Visitor visitor) {
        depthFirstTraversal(new PreOrder(visitor));
    }

    @Override
    public int getHeight() {
        if (isEmpty()) return -1;
        var max = -1;
        for (var j = 0; j < getDegree(); j++)
            max = Math.max(max, getSubtree(j).getHeight());
        return max + 1;
    }

    @Override
    public int getCount() {
        if (isEmpty()) return 0;
        var n = 1;
        for (var j = 0; j < getDegree(); j++)
            n += getSubtree(j).getCount();
        return n;
    }
}
