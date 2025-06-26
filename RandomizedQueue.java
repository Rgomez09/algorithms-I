import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] a;

    public RandomizedQueue() {
        a = (Item[]) new Object[1];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Cannot add null");
        if (size == a.length) resize(2 * a.length);
        a[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        int randIndex = StdRandom.uniformInt(size);
        Item item = a[randIndex];
        a[randIndex] = a[--size];
        a[size] = null;
        if (size > 0 && size == a.length / 4) resize(a.length / 2);
        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return a[StdRandom.uniformInt(size)];
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = 0;
        private final int[] indices;

        public RandomizedQueueIterator() {
            indices = new int[size];
            for (int j = 0; j < size; j++) {
                indices[j] = j;
            }
            StdRandom.shuffle(indices);
        }

        public boolean hasNext() {
            return i < indices.length;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[indices[i++]];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // Required for autograder
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        System.out.println("Sample: " + rq.sample());
        System.out.println("Dequeue: " + rq.dequeue());
        System.out.println("Size after dequeue: " + rq.size());
    }
}

