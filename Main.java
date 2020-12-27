public class Main {
    public static void main(String[] args) {
        AVL tree = new AVL();
        tree.root = tree.Insert(tree.Root(), 1);
        tree.root = tree.Insert(tree.Root(), 3);
        tree.root = tree.Insert(tree.Root(), 5);
        tree.root = tree.Insert(tree.Root(), 25);
        tree.root = tree.Insert(tree.Root(), 7);
        tree.root = tree.Insert(tree.Root(), 34);
        tree.root = tree.Insert(tree.Root(), 50);
        tree.root = tree.Insert(tree.Root(), 55);

        tree.Print(tree.Root(), "", true);

    }
}
