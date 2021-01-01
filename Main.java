public class Main {
    public static void main(String[] args) {
        AVL tree = new AVL();
        tree.Insert(1);
        tree.Insert(2);
        tree.Insert(3);
        tree.Insert(6);
        tree.Insert(5);
        tree.Insert(4);
        tree.Insert(7);
        tree.Insert(8);
        tree.Insert(9);
        tree.Insert(10);

        tree.Print(tree.Root());
        System.out.println("\n");

        tree.Delete(3);

        tree.Print(tree.Root());
    }
}
