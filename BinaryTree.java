package ics202;

public class BinaryTree extends AbstractTree {

    protected Object key;
    protected BinaryTree left;
    protected BinaryTree right;

    public BinaryTree(Object obj, BinaryTree left, BinaryTree right) {
        key = obj;
        this.left = left;
        this.right = right;
    }

    public BinaryTree() {
        this(null, null, null);
    }

    public BinaryTree(Object obj) {
        this(obj, new BinaryTree(), new BinaryTree());
    }

    @Override
    public void purge() {
        key = null;
        left = null;
        right = null;
    }

    public boolean isLeaf() {
        return !isEmpty() && left.isEmpty() && right.isEmpty();
    }

    @Override
    public int getDegree() {
        return isEmpty() ? 0 : 2;
    }

    @Override
    public boolean isEmpty() {
        return key == null;
    }

    @Override
    public boolean isFull() {
        if (isEmpty()) {
            return true;
        }
        if (left.getHeight() != right.getHeight()) {
            return false;
        }
        return left.isFull() && right.isFull();
    }

    @Override
    public Object getKey() {
        if (isEmpty()) {
            throw new InvalidOperationException();
        }
        return key;
    }

    @Override
    public Tree getSubtree(int i) {
        if (i < 0 || i > 1) {
            throw new IndexOutOfBoundsException();
        }
        return i == 0 ? getLeft() : getRight();
    }

    public BinaryTree getLeft() {
        if (isEmpty()) {
            throw new InvalidOperationException();
        }
        return left;
    }

    public BinaryTree getRight() {
        if (isEmpty()) {
            throw new InvalidOperationException();
        }
        return right;
    }

    public void attachKey(Object obj) {
        if (!isEmpty()) {
            throw new InvalidOperationException();
        }
        key = obj;
        left = new BinaryTree();
        right = new BinaryTree();
    }

    public Object detachKey() {
        if (!isLeaf()) {
            throw new InvalidOperationException();
        }
        Object obj = key;
        key = null;
        left = null;
        right = null;
        return obj;
    }

    @Override
    public int getCount() {
        return isEmpty() ? 0 : 1 + left.getCount() + right.getCount();
    }

    public int leavesCount() {
        if (isEmpty()) {
            return 0;
        }
        if (isLeaf()) {
            return 1;
        }
        return left.leavesCount() + right.leavesCount();
    }

    @Override
    public void depthFirstTraversal(PrePostVisitor prepostvisitor) {
        if (!isEmpty()) {
            prepostvisitor.preVisit(key);
            left.depthFirstTraversal(prepostvisitor);
            prepostvisitor.inVisit(key);
            right.depthFirstTraversal(prepostvisitor);
            prepostvisitor.postVisit(key);
        }
    }

    @Override
    protected int compareTo(MyComparable comparable) {
        if (comparable instanceof BinaryTree binarytree) {
            if (isEmpty()) {
                return binarytree.isEmpty() ? 0 : -1;
            }
            if (binarytree.isEmpty()) {
                return 1;
            }
            int i = ((MyComparable) getKey()).compare((MyComparable) binarytree.getKey());
            if (i == 0) {
                i = getLeft().compareTo(binarytree.getLeft());
            }
            if (i == 0) {
                i = getRight().compareTo(binarytree.getRight());
            }
            return i;
        }
        return getClass().getName().compareTo(comparable.getClass().getName());
    }
}
