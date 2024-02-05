package ds.non_linear;

public class AVLTree {

    private class AVLNode {
        private int value;
        private int height;
        private AVLNode left;
        private AVLNode right;
        public AVLNode(int value){
            this.value = value;
        }
        @Override
        public String toString(){
            return "Node=" + value;
        }
    }

    private AVLNode root;

    // insert with recursion implementation :)
    private AVLNode insert(AVLNode root, int value){
        if (root == null){
            return new AVLNode(value);
        }

        if (value < root.value)
            root.left = insert(root.left, value);
        else if (value > root.value)
            root.right = insert(root.right, value);

        updateHeight(root);

        return balance(root);
    }

    // only unique values inserted
    public void insert(int value){
        root = insert(root, value);
    }

    private AVLNode balance(AVLNode root){

        if (isLeftHeavy(root)){
            if(balanceFactor(root.left) < 0)
                root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        else if (isRightHeavy(root)){
            if(balanceFactor(root.right) > 0)
                root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private AVLNode leftRotate(AVLNode root){

        AVLNode child = root.right;
        root.right = child.left;
        child.left = root;

        updateHeight(child.left);
        updateHeight(child);

        return child;
    }

    private AVLNode rightRotate(AVLNode root){

        AVLNode child = root.left;
        root.left = child.right;
        child.right = root;

        updateHeight(child.right);
        updateHeight(child);

        return child;
    }

    private void updateHeight(AVLNode root){
        root.height =  Math.max(
                height(root.left),
                height(root.right)) + 1;
    }

    private int height(AVLNode root){
        return (root == null) ? -1 : root.height;
    }

    private boolean isLeftHeavy(AVLNode root){
        return balanceFactor(root) > 1;
    }

    private boolean isRightHeavy(AVLNode root){
        return balanceFactor(root) < -1;
    }

    private int balanceFactor(AVLNode root){
        if (root == null) return 0;
        return height(root.left) - height(root.right);
    }

}
