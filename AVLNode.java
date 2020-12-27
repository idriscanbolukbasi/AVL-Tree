/******************************************************
 * AVL Node
 ******************************************************/
public class AVLNode {

    public int height;
    public int key;           /* Key */
    public int count;         /* No of times a key is inserted into the tree */
    public AVLNode left;      /* Pointer to the left child */
    public AVLNode right;     /* Pointer to the right child */


    // Constructor
    public AVLNode(int key) {
        this.key = key;
        count = 1;
        height = 1;
        left = right = null;
    }

    @Override
    public String toString() {
        return "AVLNode{" +
                "key=" + key +
                ", height=" + height +
                ", count=" + count +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
};
