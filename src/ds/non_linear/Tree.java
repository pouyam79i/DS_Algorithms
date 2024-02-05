package ds.non_linear;

import java.util.LinkedList;

public class Tree {

    private class Node{
        private int value;
        private Node leftChild;
        private Node rightChild;
        public Node(int value){
            this.value = value;
        }
        @Override
        public String toString(){
            return "Node=" + value;
        }
    }

    private Node root;
    private int count;

    public void insert(int value){
        var parent = root;
        count++;
        var child = new Node(value);
        if (root == null){
            root = child;
        }else{
            while (true){
                if (child.value < parent.value){
                    if (parent.leftChild != null){
                        parent = parent.leftChild;
                    }else {
                        parent.leftChild = child;
                        break;
                    }
                }else if(child.value > parent.value) {
                    if (parent.rightChild != null){
                        parent = parent.rightChild;
                    }else {
                        parent.rightChild = child;
                        break;
                    }
                }else {
                    break; // if it already exists do not insert it
                }
            }
        }
    }

    private int maxBT(Node root){
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.value, Math.max(maxBT(root.leftChild), maxBT(root.rightChild)));
    }

    private int maxBST(Node root){
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.value, maxBST(root.rightChild));
    }

    public int max(){
        if (root == null) throw new IllegalStateException();
        // for BT
        return maxBT(root);
        // for BST
//        return maxBST(root);
    }
    
    public boolean find(int value){
        var node = root;
        while (node != null){
            if (node.value == value) return true;
            else if (value < node.value) {
                node = node.leftChild;
            }else {
                node = node.rightChild;
            }
        }
        return false;
    }

    private int height(Node root){
        if (root == null) return -1;
        if (isLeaf(root)) return 0;
        return 1 + Math.max(height(root.leftChild), height(root.rightChild));
    }

    public int height(){
        return height(root);
    }

    private boolean getAncestors(Node root, int value, LinkedList<Integer> list){
        if(root == null) return false;
        if(root.value == value) return true;
        if(
                getAncestors(root.leftChild, value, list) || getAncestors(root.rightChild, value, list)
        ){
            list.add(root.value);
            return true;
        }
        return false;
    }

    public LinkedList<Integer> getAncestors(int value){
        LinkedList<Integer> list = new LinkedList<>();
        getAncestors(root, value, list);
        return list;
    }

    private boolean areSiblings(Node root, int value1, int value2){
        if (root.leftChild != null && root.rightChild != null) {
            if ((root.leftChild.value == value1 && root.rightChild.value == value2) ||
                    (root.leftChild.value == value2 && root.rightChild.value == value1)){
                return true;
            }
            return areSiblings(root.leftChild, value1, value2) || areSiblings(root.rightChild, value1, value2);
        }
        return false;
    }

    public boolean areSiblings(int value1, int value2){
        return areSiblings(root, value1, value2);
    }

    private boolean contains(Node root, int value){
        if (root == null) return false;
        else if (value == root.value) return true;
        else if (value < root.value) return contains(root.leftChild, value);
        else return contains(root.rightChild, value);
    }

    public boolean contains(int value){
        return contains(root, value);
    }

    private void printNodesWithKDistance(Node root, int distance){
        if (root == null)
            return;
        if (distance == 0){
            System.out.println(root.value);
        }else {
            printNodesWithKDistance(root.leftChild, distance-1);
            printNodesWithKDistance(root.rightChild, distance-1);
        }
    }

    public void printNodesWithKDistance(int k){
        if (k < 0) return;
        printNodesWithKDistance(root, k);
    }

    private boolean validateBST(Node node, int min, int max){
        if (node == null) return true;
        if (node.value > min && node.value < max)
            return validateBST(node.leftChild, min, node.value) && validateBST(node.rightChild, node.value, max);
        return false;
    }

    public boolean validateBST(Tree t){
        if (t == null){
            return true;
        }
        else return validateBST(t.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean checkEquality(Node root1, Node root2){
        if (root1 == null && root2 == null) return true;
        else if (root1 != null && root2 != null)
            return root1.value == root2.value &&
                    checkEquality(root1.leftChild, root2.leftChild) &&
                    checkEquality(root1.rightChild, root2.rightChild);
        return false;
    }

    public boolean equals(Tree anotherTree){
        return checkEquality(root, anotherTree.root);
    }

    private boolean isLeaf(Node node){
        if (node == null) throw new IllegalStateException();
        return node.leftChild == null && node.rightChild == null;
    }

}
