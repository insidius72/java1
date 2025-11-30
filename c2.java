// 1. Класс Узла (Node)
class node {
    public int a;    // Данные (data)
    public node b;   // Следующий (next)
    public node c;   // Предыдущий (prev)

    public node(int data) {
        this.a = data;
        this.b = null;
        this.c = null;
    }
}

// 2. Класс Двусвязного Списка
class DoublyLinkedList {
    public node a; // Голова (head) - начало списка
    public node b; // Хвост (tail) - конец списка

    // --- Стандартный метод добавления в конец ---
    public void add(int data) {
        node d = new node(data);
        if (a == null) {
            a = d;
            b = d;
        } else {
            b.b = d; // Хвост ссылается на новый
            d.c = b; // Новый ссылается назад на хвост
            b = d;   // Новый становится хвостом
        }
    }

    // --- Вывод списка ---
    public void display() {
        node d = a;
        while (d != null) {
            System.out.print(d.a + " ");
            d = d.b;
        }
        System.out.println();
    }

    // --- 1. Вставка по индексу ---
    public void add(int index, int data) {
        node d = new node(data);

        // Если вставка в начало (индекс 0)
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

        // Ищем позицию
        node e = a; // Текущий узел
        int f = 0;  // Счетчик
        while (e != null && f < index - 1) {
            e = e.b;
            f++;
        }

        if (e == null) return; // Ошибка индекса

        // Вставка (e - это узел ПЕРЕД местом вставки)
        node g = e.b; // Узел, который сейчас на этом месте

        if (g == null) {
            add(data); // Если это конец списка
        } else {
            d.b = g; // Новый -> Следующий
            d.c = e; // Новый -> Предыдущий
            e.b = d; // Предыдущий -> Новый
            g.c = d; // Следующий -> Новый
        }
    }

    // --- 2. Удаление по индексу ---
    public void remove(int index) {
        if (a == null) return;

        // Удаление головы
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

        if (d == null) return; // Индекс не найден

        // Если удаляем хвост
        if (d == b) {
            b = d.c;
            b.b = null;
        } else {
            // Удаляем из середины
            d.c.b = d.b; // Предыдущий перепрыгивает
            d.b.c = d.c; // Следующий ссылается назад
        }
    }

    // --- 3. Получение по индексу ---
    public int get(int index) {
        node d = a;
        int e = 0;
        while (d != null) {
            if (e == index) return d.a;
            d = d.b;
            e++;
        }
        return -1; // Если не найдено
    }

    // --- 4. Вывод в обратном порядке ---
    public void displayReverse() {
        node d = b; // Начинаем с хвоста
        while (d != null) {
            System.out.print(d.a + " ");
            d = d.c; // Идем назад (prev)
        }
        System.out.println();
    }

    // --- 5. Получение первого элемента ---
    public int getFirst() {
        if (a != null) return a.a;
        return -1;
    }

    // --- 6. Получение последнего элемента ---
    public int getLast() {
        if (b != null) return b.a;
        return -1;
    }
}

// 3. Запуск программы (Main)
public class c2 {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        // Тестирование
        list.add(10);
        list.add(20);
        list.add(30);

        System.out.print("Список: ");
        list.display(); // 10 20 30

        System.out.print("Вставка 15 на index 1: ");
        list.add(1, 15);
        list.display(); // 10 15 20 30

        System.out.print("Удаление index 2 (число 20): ");
        list.remove(2);
        list.display(); // 10 15 30

        System.out.print("Обратный порядок: ");
        list.displayReverse(); // 30 15 10

        System.out.println("Первый элемент: " + list.getFirst());
        System.out.println("Последний элемент: " + list.getLast());
        System.out.println("Элемент под индексом 1: " + list.get(1));
    }
}