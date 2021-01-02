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

    public int getHeight(AVLNode node) {
        if (node == null) return 0;
        int leftHeight = 0;
        int rightHeight = 0;
        if (node.left != null) {
            leftHeight += getHeight(node.left);
            leftHeight++;
        }
        if (node.right != null) {
            rightHeight += getHeight(node.right);
            rightHeight++;
        }
        return Math.max(leftHeight, rightHeight);
    }

    public int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }

        return getHeight(node.left) - getHeight(node.right);
    }

    public AVLNode rightRotate(AVLNode node) {
        AVLNode left = node.left;
        AVLNode leftsRight = left.right;

        left.right = node;
        node.left = leftsRight;

        return left;
    }

    public AVLNode leftRotate(AVLNode node) {
        AVLNode right = node.right;
        AVLNode rightsLeft = right.left;

        right.left = node;
        node.right = rightsLeft;

        return right;
    }


    /*******************************************************
     * Inserts the key into the AVL. Returns a pointer to
     * the inserted node
     *******************************************************/
    public AVLNode Insert(int key) {
        if (Find(key) != null) {
            AVLNode exist = Find(key);
            exist.count++;
            return null;
        }
        noOfNodes++;
        return root = insert(root, key);
    } //end-Insert

    private AVLNode insert(AVLNode node, int key) {
        if (node == null) {
            return new AVLNode(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }

        int balance = getBalance(node);

        // Left-Left Case
        if (balance > 1 && key > node.left.key) {
            return rightRotate(node);
        }

        // Right-Right Case
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // Left-Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right-Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /*******************************************************
     * Deletes the key from the tree (if found). Returns
     * 0 if deletion succeeds, -1 if it fails
     *******************************************************/
    public int Delete(int key) {
        AVLNode deleted = delete(root, key);
        if (deleted == null) return -1;
        noOfNodes--;
        return 0;
    } //end-Delete

    private AVLNode delete(AVLNode node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (getHeight(node.left) > getHeight(node.right)) {
                    AVLNode temp = MaxByNode(node.left);
                    node.key = temp.key;

                    node.left = delete(node.left, temp.key);
                } else {
                    AVLNode temp = MinByNode(node.right);
                    node.key = temp.key;

                    node.right = delete(node.right, temp.key);
                }
            }
        }

        int balance = getBalance(node);


        // Left-Left Case
        if (balance > 1 && key > node.left.key) {
            return rightRotate(node);
        }

        // Right-Right Case
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // Left-Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right-Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;

    }

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
    public AVLNode Min() {
        return MinByNode(root);
    } //end-Min

    public AVLNode MinByNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    /*******************************************************
     * Returns a pointer to the node that contains the maximum key
     *******************************************************/
    public AVLNode Max() {
        return MaxByNode(root);
    } //end-Max

    public AVLNode MaxByNode(AVLNode node) {
        AVLNode current = node;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    /*******************************************************
     * Returns the depth of tree. Depth of a tree is defined as
     * the depth of the deepest leaf node. Root is at depth 0
     *******************************************************/
    public int Depth() {
        if (root == null) {
            return -1;
        }

        return getHeight(root);
    } //end-Depth

    /*******************************************************
     * Performs an inorder traversal of the tree and prints [key, count]
     * pairs in sorted order
     *******************************************************/
    public void Print() {
        PrintByNode(root);
    }//end-Print

    public void PrintByNode(AVLNode base) {
        if (base != null) {
            PrintByNode(base.left);
            System.out.print(base.key + (base.key == root.key ? "(R) " : " "));
            PrintByNode(base.right);
        }
    }

    public void PrintTree(AVLNode base, String indent, boolean last) {
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
            PrintTree(base.left, indent, false);
            PrintTree(base.right, indent, true);
        }
    }

}
