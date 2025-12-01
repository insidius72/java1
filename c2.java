
class node {
    public int a;  
    public node b;  
    public node c;  

    public node(int data) {
        this.a = data;
        this.b = null;
        this.c = null;
    }
}

class DoublyLinkedList {
    public node a;
    public node b; 

    public void add(int data) {
        node d = new node(data);
        if (a == null) {
            a = d;
            b = d;
        } else {
            b.b = d; 
            d.c = b; 
            b = d; 
        }
    }

    public void display() {
        node d = a;
        while (d != null) {
            System.out.print(d.a + " ");
            d = d.b;
        }
        System.out.println();
    }

    public void add(int index, int data) {
        node d = new node(data);

        if (index == 0) {
            if (a == null) {
                a = d;
                b = d;
            } else {
                d.b = a;
                a.c = d;
                a = d;
            }
            return;
        }

        node e = a; 
        int f = 0;
        while (e != null && f < index - 1) {
            e = e.b;
            f++;
        }

        if (e == null) return; 

        node g = e.b; 

        if (g == null) {
            add(data); 
        } else {
            d.b = g; 
            d.c = e; 
            e.b = d; 
            g.c = d;
        }
    }

    public void remove(int index) {
        if (a == null) return;

        if (index == 0) {
            a = a.b;
            if (a != null) {
                a.c = null;
            } else {
                b = null;
            }
            return;
        }

        node d = a;
        int e = 0;
        while (d != null && e < index) {
            d = d.b;
            e++;
        }

        if (d == null) return; 

        if (d == b) {
            b = d.c;
            b.b = null;
        } else {
            d.c.b = d.b; 
            d.b.c = d.c;
        }
    }

    public int get(int index) {
        node d = a;
        int e = 0;
        while (d != null) {
            if (e == index) return d.a;
            d = d.b;
            e++;
        }
        return -1;
    }

    public void displayReverse() {
        node d = b;
        while (d != null) {
            System.out.print(d.a + " ");
            d = d.c;
        }
        System.out.println();
    }

    public int getFirst() {
        if (a != null) return a.a;
        return -1;
    }

    public int getLast() {
        if (b != null) return b.a;
        return -1;
    }
}

public class c2 {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);

        System.out.print("Список: ");
        list.display();

        System.out.print("Вставка 15 на index 1: ");
        list.add(1, 15);
        list.display(); 

        System.out.print("Удаление index 2 (число 20): ");
        list.remove(2);
        list.display();

        System.out.print("Обратный порядок: ");
        list.displayReverse();

        System.out.println("Первый элемент: " + list.getFirst());
        System.out.println("Последний элемент: " + list.getLast());
        System.out.println("Элемент под индексом 1: " + list.get(1));
    }
}
