class Node {
    int a;             
    Node nextUzel;      

    Node(int c) {
        this.a = c;
        this.nextUzel = null;
    }
}

class SinglyLinkedList {
    Node firstUzel; 

    public void addFirst(int c) {
        Node newUzel = new Node(c); 
        newUzel.nextUzel = firstUzel;
        firstUzel = newUzel;
    }

    public void addLast(int c) {
        Node newUzel = new Node(c); 


        if (firstUzel == null) {
            firstUzel = newUzel;
            return;
        }

        Node b = firstUzel;
        while (b.nextUzel != null) {
            b = b.nextUzel;
        }
        b.nextUzel = newUzel;
    }

    public void removeFirst() {
        if (firstUzel == null) {
            return;
        }
        firstUzel = firstUzel.nextUzel;
    }

    public void removeLast() {
        if (firstUzel == null) {
            return;
        }

        if (firstUzel.nextUzel == null) {
            firstUzel = null;
            return;
        }

        Node b = firstUzel;

        while (b.nextUzel.nextUzel != null) {
            b = b.nextUzel;
        }
        b.nextUzel = null;
    }

    public void remove(int c) {
        if (firstUzel == null) {
            return;
        }

        if (firstUzel.a == c) {
            firstUzel = firstUzel.nextUzel;
            return;
        }

        Node b = firstUzel;
        while (b.nextUzel != null) {
            if (b.nextUzel.a == c) {
                b.nextUzel = b.nextUzel.nextUzel; 
                return;
            }
            b = b.nextUzel;
        }
    }

    public boolean contains(int c) {
        Node b = firstUzel;
        while (b != null) {
            if (b.a == c) {
                return true;
            }
            b = b.nextUzel;
        }
        return false;
    }

    public int size() {
        int e = 0; 
        Node b = firstUzel;
        while (b != null) {
            e++;
            b = b.nextUzel;
        }
        return e;
    }

    public boolean isEmpty() {
        if (firstUzel == null) {
            return true;
        } else {
            return false;
        }
    }

    public void display() {
        Node b = firstUzel;
        while (b != null) {
            System.out.print(b.a + " -> ");
            b = b.nextUzel;
        }
        System.out.println("null");
    }

    public void clear() {
        firstUzel = null;
    }
}

public class с1 {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        System.out.println("Тест добавления");
        list.addLast(10);
        list.addLast(20);
        list.addFirst(5);
        list.display();

        System.out.println("Тест удаления");
        list.remove(20);
        list.display();
    }
}
