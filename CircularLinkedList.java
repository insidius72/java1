// Класс для отдельного элемента (узла)
class Node2 {
    int d;      // данные (число)
    Node2 n;    // ссылка на следующий элемент

    Node2(int d) {
        this.d = d;
        this.n = null;
    }
}

// Класс самого циклического списка
public class CircularLinkedList {
    Node2 h = null; // h - head (начало)
    Node2 t = null; // t - tail (конец)

    // Базовый метод: добавление элемента в конец
    public void add(int a) {
        Node2 b = new Node2(a); // b - новый узел

        if (h == null) {
            // Если список пуст
            h = b;
            t = b;
            b.n = h; // Замыкаем круг (ссылается сам на себя)
        } else {
            // Если элементы уже есть
            t.n = b; // Старый хвост ссылается на новый
            t = b;   // Новый становится хвостом
            t.n = h; // Замыкаем круг: хвост ссылается на голову
        }
    }

    // Базовый метод: вывод списка на экран
    public void print() {
        if (h == null) {
            System.out.println("Список пуст");
            return;
        }

        Node2 a = h;
        // Используем do-while, чтобы выполнить тело хотя бы один раз
        do {
            System.out.print(a.d + " ");
            a = a.n;
        } while (a != h); // Остановимся, когда вернемся в начало
        System.out.println();
    }

    // 1. rotate() - Циклический сдвиг
    // Первый элемент становится последним (голова сдвигается вперед)
    public void rotate() {
        if (h == null || h == t) {
            return; // Нечего вращать
        }
        // Просто сдвигаем указатели головы и хвоста на один шаг вперед
        h = h.n;
        t = t.n;
        System.out.println("Список сдвинут.");
    }

    // 2. findCycle() - Проверка наличия цикла
    public boolean findCycle() {
        if (h == null) return false;

        // Проверяем, ссылается ли хвост на голову
        if (t.n == h) {
            return true;
        }
        return false;
    }

    // 3. find(int data) - Поиск элемента
    public boolean find(int a) {
        if (h == null) return false;

        Node2 b = h;
        do {
            if (b.d == a) {
                return true; // Нашли
            }
            b = b.n;
        } while (b != h); // Ищем только один полный круг

        return false; // Не нашли
    }

    // 4. splitIntoTwo() - Разделение списка на два
    public void splitIntoTwo() {
        if (h == null) return;

        // Сначала посчитаем количество элементов
        Node2 a = h;
        int c = 0; // счетчик
        do {
            c++;
            a = a.n;
        } while (a != h);

        if (c < 2) {
            System.out.println("Слишком мало элементов для разделения");
            return;
        }

        // Находим середину
        int mid = c / 2;
        if (c % 2 != 0) mid++; // Если нечетное, первая часть будет больше

        Node2 current = h;
        for (int i = 0; i < mid - 1; i++) {
            current = current.n;
        }

        // current сейчас стоит на конце первого будущего списка

        // Создаем начала для двух списков
        Node2 h1 = h;
        Node2 h2 = current.n;

        // Замыкаем первый список
        current.n = h1;

        // Замыкаем второй список
        // t (старый хвост) должен ссылаться на начало второго списка (h2)
        t.n = h2;

        System.out.println("--- Разделение выполнено ---");

        System.out.print("Список 1: ");
        printListFromNode(h1);

        System.out.print("Список 2: ");
        printListFromNode(h2);
    }

    // Вспомогательный метод для печати, чтобы показать результат разделения
    void printListFromNode(Node2 start) {
        Node2 temp = start;
        do {
            System.out.print(temp.d + " ");
            temp = temp.n;
        } while (temp != start);
        System.out.println();
    }

    // Тестирование
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