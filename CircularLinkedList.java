class Node2 {
    int d;
    Node2 n;

    Node2(int d) {
        this.d = d;
        this.n = null;
    }
}


public class CircularLinkedList {
    Node2 h = null; 
    Node2 t = null;

    public void add(int a) {
        Node2 b = new Node2(a); 

        if (h == null) {

            h = b;
            t = b;
            b.n = h;
        } else {
            t.n = b;
            t = b;
            t.n = h;
        }
    }

    public void print() {
        if (h == null) {
            System.out.println("Список пуст");
            return;
        }

        Node2 a = h;
        do {
            System.out.print(a.d + " ");
            a = a.n;
        } while (a != h);
        System.out.println();
    }

    public void rotate() {
        if (h == null || h == t) {
            return;
        }
        h = h.n;
        t = t.n;
        System.out.println("Список сдвинут.");
    }

    public boolean findCycle() {
        if (h == null) return false;

        if (t.n == h) {
            return true;
        }
        return false;
    }

    public boolean find(int a) {
        if (h == null) return false;

        Node2 b = h;
        do {
            if (b.d == a) {
                return true;
            }
            b = b.n;
        } while (b != h);
        return false;
    }

    public void splitIntoTwo() {
        if (h == null) return;

        Node2 a = h;
        int c = 0;
        do {
            c++;
            a = a.n;
        } while (a != h);

        if (c < 2) {
            System.out.println("Слишком мало элементов для разделения");
            return;
        }

        int mid = c / 2;
        if (c % 2 != 0) mid++;

        Node2 current = h;
        for (int i = 0; i < mid - 1; i++) {
            current = current.n;
        }

        Node2 h1 = h;
        Node2 h2 = current.n;
        current.n = h1;
        t.n = h2;
        System.out.println("--- Разделение выполнено ---");
        System.out.print("Список 1: ");
        printListFromNode(h1);
        System.out.print("Список 2: ");
        printListFromNode(h2);
    }

    void printListFromNode(Node2 start) {
        Node2 temp = start;
        do {
            System.out.print(temp.d + " ");
            temp = temp.n;
        } while (temp != start);
        System.out.println();
    }

    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(60);

        System.out.print("Исходный список: ");
        list.print();

        list.rotate();
        System.out.print("После сдвига (rotate): ");
        list.print();

        list.splitIntoTwo();
    }

}
