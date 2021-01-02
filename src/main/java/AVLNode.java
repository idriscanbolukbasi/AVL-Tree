import java.util.Objects;

/******************************************************
 * AVL Node
 ******************************************************/
public class AVLNode {

    public int key;           /* Key */
    public int count;         /* No of times a key is inserted into the tree */
    public AVLNode left;      /* Pointer to the left child */
    public AVLNode right;     /* Pointer to the right child */


    // Constructor
    public AVLNode(int key) {
        this.key = key;
        count = 1;
        left = right = null;
    }

    @Override
    public String toString() {
        return "AVLNode{" +
                "key=" + key +
                ", count=" + count +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AVLNode avlNode = (AVLNode) o;
        return key == avlNode.key;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
};
