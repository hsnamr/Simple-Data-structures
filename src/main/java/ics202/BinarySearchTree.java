package ics202;

public class BinarySearchTree extends BinaryTree implements SearchTree {

    private BinarySearchTree getLeftBST() {
        return (BinarySearchTree) getLeft();
    }

    private BinarySearchTree getRightBST() {
        return (BinarySearchTree) getRight();
    }

    @Override
    public boolean isMember(MyComparable comparable) {
        return find(comparable) != null;
    }

    @Override
    public MyComparable find(MyComparable comparable) {
        if (isEmpty()) {
            return null;
        }
        MyComparable key = (MyComparable) getKey();
        if (comparable.isEQ(key)) {
            return key;
        }
        if (comparable.isLT(key)) {
            return getLeftBST().find(comparable);
        }
        return getRightBST().find(comparable);
    }

    @Override
    public MyComparable findMin() {
        if (isEmpty()) {
            return null;
        }
        if (getLeftBST().isEmpty()) {
            return (MyComparable) getKey();
        }
        return getLeftBST().findMin();
    }

    @Override
    public MyComparable findMax() {
        if (isEmpty()) {
            return null;
        }
        if (getRightBST().isEmpty()) {
            return (MyComparable) getKey();
        }
        return getRightBST().findMax();
    }

    @Override
    public void attachKey(Object obj) {
        if (!isEmpty()) {
            throw new InvalidOperationException();
        }
        key = obj;
        left = new BinarySearchTree();
        right = new BinarySearchTree();
    }

    @Override
    public void insert(MyComparable comparable) {
        if (isEmpty()) {
            attachKey(comparable);
            return;
        }
        MyComparable key = (MyComparable) getKey();
        if (comparable.isEQ(key)) {
            throw new IllegalArgumentException("duplicate key");
        }
        if (comparable.isLT(key)) {
            getLeftBST().insert(comparable);
        } else {
            getRightBST().insert(comparable);
        }
    }

    @Override
    public void withdraw(MyComparable comparable) {
        if (isEmpty()) {
            throw new IllegalArgumentException("object not found");
        }
        MyComparable key = (MyComparable) getKey();
        if (comparable.isLT(key)) {
            getLeftBST().withdraw(comparable);
        } else if (comparable.isGT(key)) {
            getRightBST().withdraw(comparable);
        } else {
            if (isLeaf()) {
                detachKey();
            } else if (!getLeftBST().isEmpty()) {
                MyComparable max = getLeftBST().findMax();
                super.key = max;
                getLeftBST().withdraw(max);
            } else {
                MyComparable min = getRightBST().findMin();
                super.key = min;
                getRightBST().withdraw(min);
            }
        }
    }

    public int depth(MyComparable object) {
        if (isEmpty()) {
            throw new IllegalArgumentException("object not found");
        }
        MyComparable key = (MyComparable) getKey();
        if (object.isEQ(key)) {
            return 0;
        }
        if (object.isLT(key)) {
            return 1 + getLeftBST().depth(object);
        }
        return 1 + getRightBST().depth(object);
    }

    public int width() {
        if (isEmpty()) {
            throw new InvalidOperationException();
        }
        QueueAsLinkedList queue = new QueueAsLinkedList();
        queue.enqueue(this);
        int max = 1;
        int previousLevelCount = 1;
        while (!queue.isEmpty()) {
            int currentLevelCount = 0;
            for (int i = 0; i < previousLevelCount; i++) {
                BinaryTree tree = (BinaryTree) queue.dequeue();
                if (!tree.getLeft().isEmpty()) {
                    queue.enqueue(tree.getLeft());
                    currentLevelCount++;
                }
                if (!tree.getRight().isEmpty()) {
                    queue.enqueue(tree.getRight());
                    currentLevelCount++;
                }
            }
            previousLevelCount = currentLevelCount;
            if (currentLevelCount > max) {
                max = currentLevelCount;
            }
        }
        return max;
    }
}
