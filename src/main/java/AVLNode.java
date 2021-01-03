/******************************************************
 * AVL Node
 ******************************************************/
public class AVLNode {
    public int key;           /* Key */
    public int count;         /* No of times a key is inserted into the tree */
    public AVLNode left;      /* Pointer to the left child */
    public AVLNode right;     /* Pointer to the right child */

    // Constructor
    public AVLNode(int key, int count) {
        this.key = key;
        this.count = count; // if node is already in present setting its count on creation is more easier than increasing it later
        left = right = null;
    }
};
