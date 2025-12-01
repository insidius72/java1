import java.util.LinkedList;
import java.util.Queue;

class Node1 {
    int a;
    Node1 b;
    Node1 c;

    public Node1(int d) {
        a = d;
        b = null;
        c = null;
    }
}

public class Lab4 {

    Node1 root;

    void preOrder(Node1 a) {
        if (a == null) return;
        System.out.print(a.a + " ");
        preOrder(a.b);
        preOrder(a.c);
    }

    void inOrder(Node1 a) {
        if (a == null) return;
        inOrder(a.b);
        System.out.print(a.a + " ");
        inOrder(a.c);
    }

    void postOrder(Node1 a) {
        if (a == null) return;
        postOrder(a.b);
        postOrder(a.c);
        System.out.print(a.a + " ");
    }

    void levelOrder(Node1 a) {
        if (a == null) return;
        Queue<Node1> b = new LinkedList<>();
        b.add(a);
        while (!b.isEmpty()) {
            Node1 c = b.poll();
            System.out.print(c.a + " ");
            if (c.b != null) b.add(c.b);
            if (c.c != null) b.add(c.c);
        }
    }

    int height(Node1 a) {
        if (a == null) return 0;
        int b = height(a.b);
        int c = height(a.c);
        if (b > c) return b + 1;
        else return c + 1;
    }

    boolean isFull(Node1 a) {
        if (a == null) return true;

        if (a.b == null && a.c == null) return true;

        if (a.b != null && a.c != null) {
            return isFull(a.b) && isFull(a.c);
        }

        return false;
    }

    Node1 insertBST(Node1 a, int b) {
        if (a == null) {
            a = new Node1(b);
            return a;
        }
        if (b < a.a) {
            a.b = insertBST(a.b, b);
        } else if (b > a.a) {
            a.c = insertBST(a.c, b);
        }
        return a;
    }

    Node1 sortedArrayToBST(int[] a, int b, int c) {
        if (b > c) return null;

        int d = (b + c) / 2;
        Node1 e = new Node1(a[d]);

        e.b = sortedArrayToBST(a, b, d - 1);
        e.c = sortedArrayToBST(a, d + 1, c);

        return e;
    }

    void insertLevelOrder(int a) {
        if (root == null) {
            root = new Node1(a);
            return;
        }
        Queue<Node1> b = new LinkedList<>();
        b.add(root);

        while (!b.isEmpty()) {
            Node1 c = b.poll();

            if (c.b == null) {
                c.b = new Node1(a);
                break;
            } else {
                b.add(c.b);
            }

            if (c.c == null) {
                c.c = new Node1(a);
                break;
            } else {
                b.add(c.c);
            }
        }
    }

    void delete(int a) {
        if (root == null) return;
        if (root.b == null && root.c == null) {
            if (root.a == a) root = null;
            return;
        }

        Node1 b = null;
        Node1 c = null;

        Queue<Node1> e = new LinkedList<>();
        e.add(root);

        while (!e.isEmpty()) {
            c = e.poll();
            if (c.a == a) b = c;
            if (c.b != null) e.add(c.b);
            if (c.c != null) e.add(c.c);
        }

        if (b != null) {
            int f = c.a; 
            deleteDeepest(root, c);
            b.a = f;
        }
    }

    void deleteDeepest(Node1 a, Node1 b) {
        Queue<Node1> c = new LinkedList<>();
        c.add(a);
        while(!c.isEmpty()) {
            Node1 d = c.poll();
            if (d.c != null) {
                if (d.c == b) {
                    d.c = null;
                    return;
                } else {
                    c.add(d.c);
                }
            }
            if (d.b != null) {
                if (d.b == b) {
                    d.b = null;
                    return;
                } else {
                    c.add(d.b);
                }
            }
        }
    }

    public static void main(String[] args) {
        Lab4 tree = new Lab4();

        System.out.println("1. Обходы и высота");
        tree.root = new Node1(1);
        tree.root.b = new Node1(2);
        tree.root.c = new Node1(3);
        tree.root.b.b = new Node1(4);
        tree.root.b.c = new Node1(5);

        System.out.print("PreOrder: ");
        tree.preOrder(tree.root);
        System.out.println();

        System.out.print("LevelOrder: ");
        tree.levelOrder(tree.root);
        System.out.println();

        System.out.println("Высота дерева: " + tree.height(tree.root));

        System.out.println("\n2. Проверка на Full Binary Tree");
        System.out.println("Является полным? " + tree.isFull(tree.root));

        System.out.println("\n3. Построение BST");
        Lab4 bst = new Lab4();
        bst.root = bst.insertBST(null, 50);
        bst.insertBST(bst.root, 30);
        bst.insertBST(bst.root, 20);
        bst.insertBST(bst.root, 40);
        bst.insertBST(bst.root, 70);
        System.out.print("BST InOrder (должен быть отсортирован): ");
        bst.inOrder(bst.root);
        System.out.println();

        System.out.println("\n4. Сбалансированное дерево из массива");
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Lab4 balancedTree = new Lab4();
        balancedTree.root = balancedTree.sortedArrayToBST(arr, 0, arr.length - 1);
        System.out.print("PreOrder сбалансированного: ");
        balancedTree.preOrder(balancedTree.root);
        System.out.println();

        System.out.println("\n5. Вставка и удаление (Level Order)");
        Lab4 opTree = new Lab4();
        opTree.insertLevelOrder(10);
        opTree.insertLevelOrder(20);
        opTree.insertLevelOrder(30);
        opTree.insertLevelOrder(40);
        opTree.insertLevelOrder(50);

        System.out.print("До удаления: ");
        opTree.levelOrder(opTree.root);
        System.out.println();

        opTree.delete(20);

        System.out.print("После удаления 20: ");
        opTree.levelOrder(opTree.root);
        System.out.println();
    }

}

