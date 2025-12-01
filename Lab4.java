import java.util.LinkedList;
import java.util.Queue;

// Класс для узла дерева
class Node1 {
    int a;      // Значение узла (data)
    Node1 b;     // Левый потомок (left)
    Node1 c;     // Правый потомок (right)

    public Node1(int d) {
        a = d;
        b = null;
        c = null;
    }
}

public class Lab4 {

    Node1 root;

    // --- ЗАДАНИЕ 1: Обходы и высота ---

    // Прямой порядок (PreOrder)
    void preOrder(Node1 a) {
        if (a == null) return;
        System.out.print(a.a + " ");
        preOrder(a.b);
        preOrder(a.c);
    }

    // Центрированный порядок (InOrder)
    void inOrder(Node1 a) {
        if (a == null) return;
        inOrder(a.b);
        System.out.print(a.a + " ");
        inOrder(a.c);
    }

    // Обратный порядок (PostOrder)
    void postOrder(Node1 a) {
        if (a == null) return;
        postOrder(a.b);
        postOrder(a.c);
        System.out.print(a.a + " ");
    }

    // Поуровневый порядок (LevelOrder)
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

    // Вычисление высоты дерева
    int height(Node1 a) {
        if (a == null) return 0;
        int b = height(a.b);
        int c = height(a.c);
        if (b > c) return b + 1;
        else return c + 1;
    }

    // --- ЗАДАНИЕ 2: Полное бинарное дерево ---

    // Проверка, является ли дерево полным (Full Binary Tree)
    boolean isFull(Node1 a) {
        // Если дерево пустое, оно считается полным
        if (a == null) return true;

        // Если это лист (нет детей)
        if (a.b == null && a.c == null) return true;

        // Если есть оба ребенка, проверяем дальше
        if (a.b != null && a.c != null) {
            return isFull(a.b) && isFull(a.c);
        }

        // Если есть только один ребенок -> false
        return false;
    }

    // --- ЗАДАНИЕ 3: Бинарное дерево поиска (BST) ---

    // Вставка в BST
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

    // --- ЗАДАНИЕ 4: Сбалансированное дерево из массива ---

    Node1 sortedArrayToBST(int[] a, int b, int c) {
        if (b > c) return null;

        int d = (b + c) / 2;
        Node1 e = new Node1(a[d]);

        e.b = sortedArrayToBST(a, b, d - 1);
        e.c = sortedArrayToBST(a, d + 1, c);

        return e;
    }

    // --- ЗАДАНИЕ 5: Вставка и Удаление (Level Order) ---

    // Вставка в первую свободную позицию (Level Order Insert)
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

    // Удаление узла с заменой на самый глубокий правый
    void delete(int a) {
        if (root == null) return;
        if (root.b == null && root.c == null) {
            if (root.a == a) root = null;
            return;
        }

        Node1 b = null; // Узел, который надо удалить
        Node1 c = null; // Самый глубокий узел


        Queue<Node1> e = new LinkedList<>();
        e.add(root);

        // 1. Ищем узел для удаления и самый последний узел
        while (!e.isEmpty()) {
            c = e.poll();
            if (c.a == a) b = c;
            if (c.b != null) e.add(c.b);
            if (c.c != null) e.add(c.c);
        }

        if (b != null) {
            int f = c.a; // Сохраняем значение самого глубокого
            deleteDeepest(root, c); // Удаляем самый глубокий узел
            b.a = f; // Заменяем значение удаляемого узла
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

    // --- MAIN ---
    public static void main(String[] args) {
        Lab4 tree = new Lab4();

        System.out.println("--- 1. Обходы и высота ---");
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

        System.out.println("\n--- 2. Проверка на Full Binary Tree ---");
        System.out.println("Является полным? " + tree.isFull(tree.root));
        // (Сейчас оно полное: 1 имеет детей 2,3; 2 имеет детей 4,5; 3,4,5 - листы)

        System.out.println("\n--- 3. Построение BST ---");
        Lab4 bst = new Lab4();
        bst.root = bst.insertBST(null, 50);
        bst.insertBST(bst.root, 30);
        bst.insertBST(bst.root, 20);
        bst.insertBST(bst.root, 40);
        bst.insertBST(bst.root, 70);
        System.out.print("BST InOrder (должен быть отсортирован): ");
        bst.inOrder(bst.root);
        System.out.println();

        System.out.println("\n--- 4. Сбалансированное дерево из массива ---");
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Lab4 balancedTree = new Lab4();
        balancedTree.root = balancedTree.sortedArrayToBST(arr, 0, arr.length - 1);
        System.out.print("PreOrder сбалансированного: ");
        balancedTree.preOrder(balancedTree.root);
        System.out.println();

        System.out.println("\n--- 5. Вставка и удаление (Level Order) ---");
        Lab4 opTree = new Lab4();
        opTree.insertLevelOrder(10);
        opTree.insertLevelOrder(20);
        opTree.insertLevelOrder(30);
        opTree.insertLevelOrder(40);
        opTree.insertLevelOrder(50);

        System.out.print("До удаления: ");
        opTree.levelOrder(opTree.root);
        System.out.println();

        opTree.delete(20); // Удаляем 20, на его место встанет 50 (самый глубокий)

        System.out.print("После удаления 20: ");
        opTree.levelOrder(opTree.root);
        System.out.println();
    }

}
