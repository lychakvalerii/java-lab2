


import java.util.Scanner;

// головний клас програми
public class Lab2Variant10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\nМеню Лабораторної №2:");
            System.out.println("1. Завдання 1 (Клас Cursor)");
            System.out.println("2. Завдання 2 (Клас Rectangle)");
            System.out.println("3. Завдання 3 (Клас IntegerQueue)");
            System.out.println("0. Вихід");
            System.out.print("Оберіть пункт: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1: demoCursor(); break;
                    case 2: demoRectangle(); break;
                    case 3: demoQueue(); break;
                    case 0: System.out.println("Завершення..."); break;
                    default: System.out.println("Невірний вибір!");
                }
            } else {
                System.out.println("Введіть число!");
                scanner.next();
            }
        }
        scanner.close();
    }

    // демонстрація роботи класу Cursor
    public static void demoCursor() {
        System.out.println("\n--- Завдання 1 ---");
        Cursor c1 = new Cursor(); // виклик конструктора за замовчуванням
        System.out.println("Створено c1: " + c1.toString());

        Cursor c2 = new Cursor(10, 20, "вертикальний", 5); // виклик параметризованого конструктора
        System.out.println("Створено c2: " + c2.toString());

        System.out.println("\nЗмінюємо c1...");
        c1.changeCoordinates(100, 150);
        c1.changeType("горизонтальний");
        c1.changeSize(12);
        System.out.println("Після змін c1: " + c1.toString());

        System.out.println("\nХоваємо c2...");
        c2.hide();
        System.out.println(c2.toString());
        
        System.out.println("Чи однакові c1 і c2? -> " + c1.equals(c2));
        
        Cursor.printTotalCursors(); // виклик статичного методу через назву класу
    }

    // демонстрація роботи класу Rectangle
    public static void demoRectangle() {
        System.out.println("\n--- Завдання 2 ---");
        Rectangle r1 = new Rectangle(4.0, 6.0);
        Rectangle r2 = new Rectangle(8.0, 12.0);
        Rectangle r3 = new Rectangle(4.0, 6.0);

        System.out.println("R1: " + r1.toString());
        System.out.println("R2: " + r2.toString());

        System.out.println("\nПлоща R1: " + r1.getArea());
        System.out.println("Периметр R1: " + r1.getPerimeter());

        System.out.println("\nПорівняння R1 і R3 (через equals): " + r1.equals(r3));
        System.out.println("Порівняння площ (R1 > R2 ?): " + r1.compareTo(r2));
        System.out.println("Чи подібні R1 і R2 (пропорційні сторони)? -> " + r1.isSimilarTo(r2));

        Rectangle.printTotalRectangles();
    }

    // демонстрація роботи черги
    public static void demoQueue() {
        System.out.println("\n--- Завдання 3 ---");
        IntegerQueue queue = new IntegerQueue();
        
        System.out.println("Додаємо елементи: 10, 20, 30");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println(queue.toString());

        System.out.println("\nПошук числа 20: " + queue.search(20));
        System.out.println("Пошук числа 99: " + queue.search(99));

        System.out.println("\nВилучаємо елемент: " + queue.dequeue());
        System.out.println("Стан черги: " + queue.toString());
        
        IntegerQueue.printTotalQueues();
    }
}

// завдання 1 (Клас Cursor)[cite: 18]
class Cursor {
    // статичне поле (спільне для всього класу, рахує всі створені об'єкти)[cite: 16]
    private static int totalCursorsCreated = 0;

    // поля об'єкта (координати, вигляд, розмір)[cite: 18]
    private int x, y; 
    private String type; 
    private int size; 
    private boolean isVisible; 

    // блок ініціалізації (спрацьовує завжди ДО конструкторів)[cite: 16]
    {
        isVisible = true; 
        totalCursorsCreated++;
    }

    // конструктор 1: за замовчуванням (без параметрів)[cite: 16]
    public Cursor() {
        this.x = 1;
        this.y = 1;
        this.type = "горизонтальний";
        this.size = 1;
    }

    // конструктор 2: з параметрами[cite: 16]
    public Cursor(int x, int y, String type, int size) {
        changeCoordinates(x, y);
        changeType(type);
        changeSize(size);
    }

    // статичний метод (викликається без створення об'єкта)[cite: 16]
    public static void printTotalCursors() {
        System.out.println("Створено курсорів: " + totalCursorsCreated);
    }

    // методи зміни стану об'єкта[cite: 18]
    public void changeCoordinates(int x, int y) {
        if (x > 0 && y > 0) {
            this.x = x;
            this.y = y;
        }
    }

    public void changeType(String type) {
        if (type.equalsIgnoreCase("горизонтальний") || type.equalsIgnoreCase("вертикальний")) {
            this.type = type.toLowerCase();
        }
    }

    public void changeSize(int size) {
        if (size >= 1 && size <= 15) {
            this.size = size;
        }
    }

    public void hide() { this.isVisible = false; }
    public void restore() { this.isVisible = true; }

    // перевантаження системного методу toString для красивого виводу[cite: 16]
    @Override
    public String toString() {
        String visibility = isVisible ? "Видимий" : "Прихований";
        return String.format("Курсор[%s, тип: %s, розмір: %d, координати: (%d, %d)]", 
                              visibility, type, size, x, y);
    }

    // перевантаження equals для правильного порівняння об'єктів за значеннями полів[cite: 16]
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null || getClass() != obj.getClass()) return false; 
        Cursor other = (Cursor) obj; 
        return this.x == other.x && this.y == other.y && 
               this.size == other.size && this.type.equals(other.type);
    }
}

// завдання 2 (Клас Прямокутник)[cite: 19]
class Rectangle {
    private static int totalRectangles = 0; // статичне поле[cite: 16]

    private double a; 
    private double b; 

    // ініціалізатор[cite: 16]
    {
        totalRectangles++;
    }

    // конструктор за замовчуванням[cite: 16]
    public Rectangle() {
        this.a = 1.0;
        this.b = 1.0;
    }

    // конструктор з параметрами[cite: 16]
    public Rectangle(double a, double b) {
        if (a > 0 && b > 0) {
            this.a = a;
            this.b = b;
        } else {
            this.a = 1.0;
            this.b = 1.0;
        }
    }

    public static void printTotalRectangles() {
        System.out.println("Створено прямокутників: " + totalRectangles);
    }

    // методи обчислення[cite: 19]
    public double getPerimeter() {
        return 2 * (a + b); 
    }

    public double getArea() {
        return a * b;
    }

    // порівняння з іншим прямокутником (за площею)[cite: 19]
    public boolean compareTo(Rectangle other) {
        return this.getArea() > other.getArea();
    }

    // виявлення подібності (пропорційність сторін)[cite: 19]
    public boolean isSimilarTo(Rectangle other) {
        double ratio1 = this.a / other.a;
        double ratio2 = this.b / other.b;
        double ratio3 = this.a / other.b;
        double ratio4 = this.b / other.a;
        
        return (Math.abs(ratio1 - ratio2) < 0.0001) || (Math.abs(ratio3 - ratio4) < 0.0001);
    }

    // перевизначення toString[cite: 16]
    @Override
    public String toString() {
        return String.format("Прямокутник [a=%.2f, b=%.2f]", a, b);
    }

    // перевизначення equals[cite: 16]
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rectangle other = (Rectangle) obj;
        return (this.a == other.a && this.b == other.b) || 
               (this.a == other.b && this.b == other.a);
    }
}

// завдання 3 (Динамічна Черга)[cite: 20]
class IntegerQueue {
    private static int totalQueues = 0; 

    // внутрішній клас для зв'язного списку (динамічна структура)[cite: 20]
    private class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; this.next = null; }
    }

    private Node head; // початок черги (звідки вилучаємо)
    private Node tail; // кінець черги (куди додаємо)
    private int size;  

    // ініціалізатор[cite: 16]
    {
        head = null;
        tail = null;
        size = 0;
        totalQueues++;
    }

    public IntegerQueue() {
    }

    public static void printTotalQueues() {
        System.out.println("Створено черг: " + totalQueues);
    }

    // додавання елемента у чергу (в кінець)[cite: 20]
    public void enqueue(int value) {
        Node newNode = new Node(value);
        if (tail == null) { 
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // вилучення елемента з черги (з початку)[cite: 20]
    public Integer dequeue() {
        if (head == null) return null;
        int removedData = head.data;
        head = head.next;
        if (head == null) tail = null; 
        size--;
        return removedData;
    }

    // пошук елемента[cite: 20]
    public boolean search(int target) {
        Node current = head;
        while (current != null) {
            if (current.data == target) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public String toString() {
        if (head == null) return "Черга: [порожня]";
        StringBuilder sb = new StringBuilder("Черга: [");
        Node current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(" -> ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        IntegerQueue other = (IntegerQueue) obj;
        if (this.size != other.size) return false;
        
        Node current1 = this.head;
        Node current2 = other.head;
        while (current1 != null) {
            if (current1.data != current2.data) return false;
            current1 = current1.next;
            current2 = current2.next;
        }
        return true;
    }
}

