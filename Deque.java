import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");

        Node newNode = new Node();
        newNode.item = item;
        newNode.prev = null;

        if (isEmpty()) {
            newNode.next = null;
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");

        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;

        if (isEmpty()) {
            newNode.prev = null;
            first = newNode;
            last = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");

        Item item = first.item;
        first = first.next;
        size--;

        if (isEmpty()) {
            first = null;
            last = null;
        } else {
            first.prev = null;
        }

        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");

        Item item = last.item;
        last = last.prev;
        size--;

        if (isEmpty()) {
            first = null;
            last = null;
        } else {
            last.next = null;
        }

        return item;
    }


    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node cursor = first;


        public boolean hasNext() {
            return cursor != null;
        }


        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = cursor.item;
            cursor = cursor.next;
            return item;
        }


        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(0);
        deque.addLast(1);
        System.out.println("Is empty? " + deque.isEmpty());  // false
        deque.addLast(3);
        System.out.println("Removed first: " + deque.removeFirst());  // 0
        deque.addLast(5);
        deque.addLast(6);
        System.out.println("Removed first: " + deque.removeFirst());  // 1
        System.out.println("Removed last: " + deque.removeLast());    // 6
        System.out.println("Current size: " + deque.size());          // 2
    }
}


