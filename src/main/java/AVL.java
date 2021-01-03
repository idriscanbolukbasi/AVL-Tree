/******************************************************
 * AVL ADT.
 * Supported operations:
 * Insert
 * Delete
 * Find
 * Min
 * Max
 * Depth
 * Height // it's easier to calculate height then depth
 * Print
 ******************************************************/
public class AVL {
    private AVLNode root;      /* Pointer to the root of the tree */
    private int noOfNodes;     /* No of nodes in the tree */

    /*******************************************************
     * Constructor: Initializes the AVL
     *******************************************************/
    public AVL() {
        root = null;
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

    /*******************************************************
     * Inserts the key into the AVL. Returns a pointer to
     * the inserted node
     *******************************************************/
    public AVLNode Insert(int key) {
        int count = 1;
        if (Find(key) != null) {
            count = BalanceCounts(root, key, true);
        }
        if (count == 1) noOfNodes++;
        return root = InsertByNode(root, key, count);
    } //end-Insert

    public AVLNode InsertByNode(AVLNode node, int key, int count) { // methods ...ByNode added for more robust and flexible design
        if (node == null) {
            return new AVLNode(key, count);
        }

        if (key < node.key)
            node.left = InsertByNode(node.left, key, count);
        else
            node.right = InsertByNode(node.right, key, count);

        node = Balance(node);

        return node;
    }

    /*******************************************************
     * Deletes the key from the tree (if found). Returns
     * 0 if deletion succeeds, -1 if it fails
     *******************************************************/
    public int Delete(int key) {
        AVLNode existing = Find(key);
        if (existing == null) return -1;
        root = DeleteByNode(root, key);
        if (Find(key) == null) noOfNodes--;
        else BalanceCounts(root, key, false);
        return 0;
    } //end-Delete

    private AVLNode DeleteByNode(AVLNode node, int key) { // methods ...ByNode added for more robust and flexible design
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = DeleteByNode(node.left, key);
        } else if (key > node.key) {
            node.right = DeleteByNode(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                AVLNode temp = MaxByNode(node.left);
                node.key = temp.key;

                node.left = DeleteByNode(node.left, temp.key);
            }
        }

        node = Balance(node);

        return node;
    }

    /*******************************************************
     * Searches the AVL for a key. Returns a pointer to the
     * node that contains the key (if found) or NULL if unsuccessful
     *******************************************************/
    public AVLNode Find(int key) {
        return FindByNode(root, key);
    } //end-Find

    public AVLNode FindByNode(AVLNode node, int key) { // methods ...ByNode added for more robust and flexible design
        AVLNode current = node;
        while (current != null) {
            if (current.key == key) {
                break;
            }
            current = key < current.key ? current.left : current.right;
        }
        return current;
    }


    /*******************************************************
     * Returns a pointer to the node that contains the minimum key
     *******************************************************/
    public AVLNode Min() {
        return MinByNode(root);
    } //end-Min

    public AVLNode MinByNode(AVLNode node) { // methods ...ByNode added for more robust and flexible design
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

    public AVLNode MaxByNode(AVLNode node) { // methods ...ByNode added for more robust and flexible design
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
        return Height();
    } //end-Depth

    /*******************************************************
     * Performs an inorder traversal of the tree and prints [key, count]
     * pairs in sorted order also added a print method to see the hierarchy easier
     *******************************************************/
    public void Print() {
        PrintByNode(root);
    } //end-Print

    public void PrintByNode(AVLNode base) { // methods ...ByNode added for more robust and flexible design
        if (base != null) {
            PrintByNode(base.left);
            System.out.print("[" + base.key + ", " + base.count + "] ");
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

    /*******************************************************
     * Balancing operations for balance factor and also counts
     * normally it doesn't have to deal with count but in this implementation it's necessary
     *******************************************************/
    public AVLNode Balance(AVLNode node) {
        int balance = BalanceFactor(node);
        int rightBalance = BalanceFactor(node.right);
        int leftBalance = BalanceFactor(node.left);

        if (balance > 1) {
            if (rightBalance >= 0) {
                node = RotateLeft(node);
            } else {
                node.right = RotateRight(node.right);
                node = RotateLeft(node);
            }
        } else if (balance < -1) {
            if (leftBalance <= 0)
                node = RotateRight(node);
            else {
                node.left = RotateLeft(node.left);
                node = RotateRight(node);
            }
        }
        return node;
    }

    public int BalanceFactor(AVLNode node) {
        if (node == null) return 0;
        int rightBalance = node.right != null ? HeightByNode(node.right) + 1 : 0;
        int leftBalance = node.left != null ? HeightByNode(node.left) + 1 : 0;
        return rightBalance - leftBalance;
    }

    public int BalanceCounts(AVLNode node, int key, boolean upwards) {
        int amount = 1;
        if (node != null) {
            int leftBalance = upwards ? BalanceCounts(node.left, key, true) : BalanceCounts(node.left, key, false);
            if (leftBalance != 1) amount = leftBalance;
            if (key == node.key) amount = upwards ? ++node.count : --node.count;
            int rightBalance = upwards ? BalanceCounts(node.right, key, true) : BalanceCounts(node.right, key, false);
            if (rightBalance != 1) amount = rightBalance;
        }
        return amount;
    }

    /*******************************************************
     * Finds out the height of the tree, (total height = depth + height of node)
     * it's required for some calculations i.e., depth
     *******************************************************/
    public int Height() {
        if (root == null) return 0;
        return HeightByNode(root) + 1;
    }

    public int HeightByNode(AVLNode node) { // methods ...ByNode added for more robust and flexible design
        if (node == null) return 0;
        int leftHeight = 0, rightHeight= 0;
        if (node.left != null) {
            leftHeight += HeightByNode(node.left) + 1;
        }
        if (node.right != null) {
            rightHeight += HeightByNode(node.right) + 1;
        }
        return Math.max(leftHeight, rightHeight);
    }

    /*******************************************************
     * Required all 4 possible rotations of AVL Tree
     * occurred by 2 basic rotation here they are
     *******************************************************/

    public AVLNode RotateRight(AVLNode node) {
        AVLNode leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;

        return leftChild;
    }

    public AVLNode RotateLeft(AVLNode node) {
        AVLNode rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;

        return rightChild;
    }

};