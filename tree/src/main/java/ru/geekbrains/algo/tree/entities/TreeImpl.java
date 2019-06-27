package ru.geekbrains.algo.tree.entities;

import java.util.Stack;

public class TreeImpl<E extends Comparable<? super E>> implements Tree<E> {

    private Node<E> root;
    private int size;

    @Override
    public boolean add(E value) {
        Node<E> newNode = new Node<>(value);
        if (isEmpty()) {
            this.root = newNode;
            return true;
        }

        NodeAndParent nodeAndParent = doFind(value);
        if (nodeAndParent.current != null) {
            return false;
        }

        Node<E> parent = nodeAndParent.parent;

        assert parent != null;
        if (parent.shouldBeLeft(value)) {
            parent.setLeftChild(newNode);
        } else {
            parent.setRightChild(newNode);
        }

        size++;
        return true;
    }


    @Override
    public boolean addCludged(E value, int maxDepth) {

        if (findPosHops(value) > maxDepth -1)
            return false;

        Node<E> newNode = new Node<>(value);
        if (isEmpty()) {
            this.root = newNode;
            return true;
        }

        NodeAndParent nodeAndParent = doFind(value);
        if (nodeAndParent.current != null) {
            return false;
        }

        Node<E> parent = nodeAndParent.parent;

        assert parent != null;

        //int treeHeight = height(root);

        if (parent.shouldBeLeft(value)) {
            parent.setLeftChild(newNode);
        } else {
            parent.setRightChild(newNode);
        }

        size++;
        return true;
    }




    @Override
    public boolean find(E value) {
        return doFind(value).current != null;
    }

    private NodeAndParent doFind(E value) {
        Node<E> parent = null;
        Node<E> current = this.root;

        while (current != null) {
            if (current.getValue().equals(value)) {
                return new NodeAndParent(current, parent);
            }

            parent = current;
            if (current.shouldBeLeft(value)) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
        }

        return new NodeAndParent(null, parent);
    }


    public int findPosHops(E value) {

        int result = 0;

        //Node<E> parent = null;
        Node<E> current = this.root;

        while (current != null) {
            if (current.getValue().equals(value)) {
                return result;
            }

            //parent = current;
            if (current.shouldBeLeft(value)) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
            result++;
        }

        return result;
    }



    @Override
    public boolean remove(E value) {
        NodeAndParent nodeAndParent = doFind(value);
        Node<E> parent = nodeAndParent.parent;
        Node<E> removedNode = nodeAndParent.current;

        if (removedNode == null) {
            return false;
        }

        if ( removedNode.isLeaf() ) {
            removeLeafNode(parent, removedNode);
        }
        else if (hasOnlySingleChildNode(removedNode)) {
            removeNodeWithSingleChild(parent, removedNode);
        }
        else {
            removeCommonNode(parent, removedNode);
        }

        size--;
        return true;
    }

    private void removeCommonNode(Node<E> parent, Node<E> removedNode) {
        Node<E> successor = getSuccessor(removedNode);
        if (removedNode == root) {
            root = successor;
        }
        else if ( parent.shouldBeLeft(removedNode.getValue()) ) {
            parent.setLeftChild(successor);
        }
        else {
            parent.setRightChild(successor);
        }

        successor.setLeftChild(removedNode.getLeftChild());
    }

    private Node<E> getSuccessor(Node<E> removedNode) {
        Node<E> successor = removedNode;
        Node<E> successorParent = null;
        Node<E> current = removedNode.getRightChild();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != removedNode.getRightChild()) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(removedNode.getRightChild());
        }

        return successor;
    }

    private void removeNodeWithSingleChild(Node<E> parent, Node<E> removedNode) {
        Node<E> childNode = removedNode.getLeftChild() != null ? removedNode.getLeftChild() : removedNode.getRightChild();

        if (removedNode == root) {
            root = childNode;
        }
        else if ( parent.shouldBeLeft(removedNode.getValue()) ) {
            parent.setLeftChild(childNode);
        }
        else {
            parent.setRightChild(childNode);
        }
    }

    private void removeLeafNode(Node<E> parent, Node<E> removedNode) {
        if (removedNode == root) {
            root = null;
        }
        else if ( parent.shouldBeLeft(removedNode.getValue()) ) {
            parent.setLeftChild(null);
        }
        else {
            parent.setRightChild(null);
        }
    }

    private boolean hasOnlySingleChildNode(Node<E> removedNode) {
        return removedNode.getLeftChild() != null ^ removedNode.getRightChild() != null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void traverse(TraverseMode mode) {
        switch (mode) {
            case IN_ORDER:
                inOrder(root);
                break;
            case PRE_ORDER:
                preOrder(root);
                break;
            case POST_ORDER:
                postOrder(root);
                break;
            default:
                throw new IllegalArgumentException("Unknown value: " + mode);
        }
    }

    private void postOrder(Node<E> current) {
        if (current == null) {
            return;
        }

        postOrder(current.getLeftChild());
        postOrder(current.getRightChild());
        System.out.println(current);
    }

    private void preOrder(Node<E> current) {
        if (current == null) {
            return;
        }

        System.out.println(current);
        preOrder(current.getLeftChild());
        preOrder(current.getRightChild());
    }

    private void inOrder(Node<E> current) {
        if (current == null) {
            return;
        }

        inOrder(current.getLeftChild());
        System.out.println(current);
        inOrder(current.getRightChild());
    }


    // ----------------------------------------------------


    /**
     * Check is tree is balanced (home made).
     * <br>
     * https://www.geeksforgeeks.org/iterative-postorder-traversal-using-stack/
     * <br>
     * + some modifications, not (quite) sure that it works as intended
     */
    @Override
    public boolean isBalanced () {

        boolean result = true;

        Stack<Node> S = new Stack<>();



        // Check for empty tree
        if (root == null) {
            return true;
        }

        S.push(root);
        Node prev = null;

        while (!S.isEmpty())
        {
            Node current = S.peek();

            /* go down the tree in search of a leaf an if so process it
            and pop stack otherwise move down */
            if (prev == null ||
                prev.getLeftChild() == current ||
                prev.getRightChild() == current)
            {
                if (current.getLeftChild() != null) {
                    S.push(current.getLeftChild());
                }
                else if (current.getRightChild() != null) {
                    S.push(current.getRightChild());
                }
                else {
                    S.pop();


                    if (prev!= null &&
                        prev.getLeftChild() == current) {

                        prev.getBLevel().left += 1;
                    }

                    if (prev!= null &&
                        prev.getRightChild() == current) {

                        prev.getBLevel().right += 1;
                    }

                    //System.out.println(current);

                }

                /* go up the tree from left node, if the child is right
                   push it onto stack otherwise process parent and pop
                   stack */
            }
            else if (current.getLeftChild() == prev)
            {
                if (current.getRightChild() != null) {

                    S.push(current.getRightChild());
                }
                else {

                    S.pop();
                    current.getBLevel().left = prev.getBLevel().left + 1;

                    result = bLevelOk(current);
                    if (!result)
                        break;

                    //System.out.println(current);

                }

                /* go up the tree from right node and after coming back
                 from right node process parent and pop stack */
            }
            else if (current.getRightChild() == prev) {
                S.pop();
                current.getBLevel().right = prev.getBLevel().right + 1;

                result = bLevelOk(current);
                if (!result)
                    break;

                //System.out.println(current);

            }
            prev = current;
        }

        // check log2(size)
        if (result) {

            int level = Math.max(root.getBLevel().left, root.getBLevel().right) + 1;
            result = level > (Math.log(size) / Math.log(2));
        }

        return result;
    }


    private boolean bLevelOk(Node node) {

        boolean result = true;

        if(Math.abs(node.getBLevel().left - node.getBLevel().right) > 1) {
            result = false;
        }

        return result;
    }



    // ----------------------------------------------------
    //https://www.geeksforgeeks.org/how-to-determine-if-a-binary-tree-is-balanced/


    public boolean isBalanced(Node<E> node)
    {
        int lh; /* for height of left subtree */

        int rh; /* for height of right subtree */

        /* If tree is empty then return true */
        if (node == null)
            return true;

        /* Get the height of left and right sub trees */
        lh = height(node.getLeftChild());
        rh = height(node.getRightChild());

        return Math.abs(lh - rh) <= 1
               && isBalanced(node.getLeftChild())
               && isBalanced(node.getRightChild());

        /* If we reach here then tree is not height-balanced */
    }

    /* UTILITY FUNCTIONS TO TEST isBalanced() FUNCTION */
    /*  The function Compute the "height" of a tree. Height is the
        number of nodes along the longest path from the root node
        down to the farthest leaf node.*/
    public int height(Node<E> node)
    {
        /* base case tree is empty */
        if (node == null)
            return 0;

        /* If tree is not empty then height = 1 + max of left
         height and right heights */
        return 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }


    //------------------------------------------------------

    @Override
    public void display() {
        Stack<Node> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 64;

        boolean isRowEmpty = false;
        System.out.println("................................................................");

        while (!isRowEmpty) {
            Stack<Node> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                Node tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getValue());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }

            nBlanks /= 2;
        }
        System.out.println("................................................................");
    }


    public Node<E> getRoot() {
        return root;
    }

    private class NodeAndParent {
        Node<E> current;
        Node<E> parent;

        public NodeAndParent(Node<E> current, Node<E> parent) {
            this.current = current;
            this.parent = parent;
        }
    }
}
