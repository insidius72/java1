// Класс для одного узла
class Node {
    int a;              // данные (число)
    Node nextUzel;      // ссылка на следующий узел

    // Конструктор
    Node(int c) {
        this.a = c;
        this.nextUzel = null;
    }
}

// Класс самого списка
class SinglyLinkedList {
    Node firstUzel; // первый узел списка (голова)

    // 1. Добавление элемента в начало
    public void addFirst(int c) {
        Node newUzel = new Node(c); // создаем новый узел
        newUzel.nextUzel = firstUzel;
        firstUzel = newUzel;
    }

    // 2. Добавление элемента в конец
    public void addLast(int c) {
        Node newUzel = new Node(c); // создаем новый узел

        // Если список пуст
        if (firstUzel == null) {
            firstUzel = newUzel;
            return;
        }

        // Используем b как бегунок для поиска конца
        Node b = firstUzel;
        while (b.nextUzel != null) {
            b = b.nextUzel;
        }
        b.nextUzel = newUzel;
    }

    // 3. Удаление первого элемента
    public void removeFirst() {
        if (firstUzel == null) {
            return;
        }
        firstUzel = firstUzel.nextUzel;
    }

    // 4. Удаление последнего элемента
    public void removeLast() {
        if (firstUzel == null) {
            return;
        }

        if (firstUzel.nextUzel == null) {
            firstUzel = null;
            return;
        }

        Node b = firstUzel;
        // Ищем предпоследний элемент
        while (b.nextUzel.nextUzel != null) {
            b = b.nextUzel;
        }
        b.nextUzel = null;
    }

    // 5. Удаление элемента по значению c
    public void remove(int c) {
        if (firstUzel == null) {
            return;
        }

        // Если нужно удалить голову
        if (firstUzel.a == c) {
            firstUzel = firstUzel.nextUzel;
            return;
        }

        Node b = firstUzel;
        while (b.nextUzel != null) {
            // Проверяем данные следующего узла
            if (b.nextUzel.a == c) {
                b.nextUzel = b.nextUzel.nextUzel; // Удаляем
                return;
            }
            b = b.nextUzel;
        }
    }

    // 6. Проверка наличия элемента c
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

    // 7. Возврат размера списка
    public int size() {
        int e = 0; // Счетчик (использую e, так как c занято аргументом)
        Node b = firstUzel;
        while (b != null) {
            e++;
            b = b.nextUzel;
        }
        return e;
    }

    // 8. Проверка на пустоту
    public boolean isEmpty() {
        if (firstUzel == null) {
            return true;
        } else {
            return false;
        }
    }

    // 9. Вывод всех элементов списка
    public void display() {
        Node b = firstUzel;
        while (b != null) {
            System.out.print(b.a + " -> ");
            b = b.nextUzel;
        }
        System.out.println("null");
    }

    // 10. Очистка списка
    public void clear() {
        firstUzel = null;
    }
}

// Главный класс для проверки
public class с1 {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        System.out.println("Тест добавления");
        list.addLast(10);
        list.addLast(20);
        list.addFirst(5);
        list.display();

        System.out.println("Тест удаления");
        list.remove(20); // Удаляем число 20
        list.display();
    }
}