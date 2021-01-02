public class Main {
    public static void main(String[] args) {
        AVL tree = new AVL();
        tree.Insert(1);
        tree.Insert(2);
        tree.Insert(3);
        tree.Insert(4);
        tree.Insert(5);
        tree.Insert(6);
        tree.Insert(7);
        tree.Insert(8);
        tree.Insert(9);
        tree.Insert(10);

        printTree(tree);

        tree.Delete(3);

        printTree(tree);
    }

    public static void printTree(AVL tree) {
        System.out.println("Hierarchy in the tree: ");
        tree.PrintTree(tree.Root(), "", true);
        System.out.print("Inorder traversal of the tree: ");
        //tree.Print(tree.Root());
        System.out.println("\nDepth of the tree: " + tree.Depth());
        System.out.println("Number of nodes in the tree: " + tree.NoOfNodes());
    }
}
