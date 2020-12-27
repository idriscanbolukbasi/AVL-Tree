/******************************************************
 * AVL ADT.
 * Supported operations:
 * Insert
 * Delete
 * Find
 * Min
 * Max
 * Depth
 * Print
 ******************************************************/
public class AVL {
    public AVLNode root;      /* Pointer to the root of the tree */
    private int noOfNodes;     /* No of nodes in the tree */

    /*******************************************************
     * Constructor: Initializes the AVL
     *******************************************************/
    public AVL() {
        this.root = null;
        noOfNodes = 0;
    }

    /*******************************************************
     * Returns a pointer to the root of the tree
     *******************************************************/
    public AVLNode Root() {
        return root;
    }

    /*******************************************************
     * Returns the number of nodes in the tree
     *******************************************************/
    public int NoOfNodes() {
        return noOfNodes;
    }

    public int height(AVLNode node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    public int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }

        return height(node.left) - height(node.right);
    }

    public AVLNode rightRotate(AVLNode node) {
        AVLNode left = node.left;
        AVLNode leftsRight = left.right;

        left.right = node;
        node.left = leftsRight;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        left.height = Math.max(height(left.left), height(left.right)) + 1;

        return left;
    }

    public AVLNode leftRotate(AVLNode node) {
        AVLNode right = node.right;
        AVLNode rightsLeft = right.left;

        right.left = node;
        node.right = rightsLeft;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        right.height = Math.max(height(right.left), height(right.right)) + 1;

        return right;
    }


    /*******************************************************
     * Inserts the key into the AVL. Returns a pointer to
     * the inserted node
     *******************************************************/
    public AVLNode Insert(AVLNode inserted, int key) {

        if (inserted == null) {
            return new AVLNode(key);
        }

        if (key < inserted.key) {
            inserted.left = Insert(inserted.left, key);
        } else if (key > inserted.key) {
            inserted.right = Insert(inserted.right, key);
        }

        inserted.height = Math.max(height(inserted.left), height(inserted.right)) + 1;

        int balance = getBalance(inserted);

        // Left-Left Case
        if (balance > 1 && key > inserted.left.key) {
            return rightRotate(inserted);
        }

        // Right-Right Case
        if (balance < -1 && key > inserted.right.key) {
            return leftRotate(inserted);
        }

        // Left-Right Case
        if (balance > 1 && key > inserted.left.key) {
            inserted.left = leftRotate(inserted.left);
            return rightRotate(inserted);
        }

        // Right-Left Case
        if (balance < -1 && key < inserted.right.key) {
            inserted.right = rightRotate(inserted.right);
            return leftRotate(inserted);
        }

        return inserted;
    } //end-Insert

    /*******************************************************
     * Deletes the key from the tree (if found). Returns
     * 0 if deletion succeeds, -1 if it fails
     *******************************************************/
    public int Delete(int key) {
        // Fill this in
        return 0;
    } //end-Delete

    /*******************************************************
     * Searches the AVL for a key. Returns a pointer to the
     * node that contains the key (if found) or NULL if unsuccessful
     *******************************************************/
    public AVLNode Find(int key) {
        AVLNode current = root;
        while (current != null) {
            if (current.key == key) {
                break;
            }

            current = key < current.key ? current.left : current.right;
        }
        return current;
    } //end-Find

    /*******************************************************
     * Returns a pointer to the node that contains the minimum key
     *******************************************************/
    public AVLNode Min(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    } //end-Min

    /*******************************************************
     * Returns a pointer to the node that contains the maximum key
     *******************************************************/
    public AVLNode Max(AVLNode node) {
        AVLNode current = node;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    } //end-Max

    /*******************************************************
     * Returns the depth of tree. Depth of a tree is defined as
     * the depth of the deepest leaf node. Root is at depth 0
     *******************************************************/
    public int Depth() {
        if (root == null) {
            return -1;
        }

        return root.height;
    } //end-Depth

    /*******************************************************
     * Performs an inorder traversal of the tree and prints [key, count]
     * pairs in sorted order
     *******************************************************/
    public void Print(AVLNode base, String indent, boolean last) {

        if (base != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            System.out.println(base.key);
            Print(base.left, indent, false);
            Print(base.right, indent, true);
        }
    }
    //end-Print
};
