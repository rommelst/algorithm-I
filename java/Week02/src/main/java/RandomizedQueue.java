import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private  static final int INITIAL_QUEUE_SIZE = 24;

    private Item[] container;
    private int size = 0;

    private class ArrayIterator implements Iterator<Item> {

        private int[] indexList;
        private int iteratorSize;


        ArrayIterator() {
            iteratorSize = size;
            indexList = new int[size];
            for (int i = 0; i < size; i++) indexList[i] = i;
        }

        @Override
        public boolean hasNext() {
            return iteratorSize > 0;
        }

        @Override
        public Item next() {
            if (!hasNext())  throw new NoSuchElementException();
            int randomIndex = StdRandom.uniform(iteratorSize);
            Item item = container[indexList[randomIndex]];
            indexList[randomIndex] = indexList[--iteratorSize];
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove unsupported.");
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        resize(INITIAL_QUEUE_SIZE * 2);
    }


    private void resize(int capacity)
    {
        if (capacity < INITIAL_QUEUE_SIZE) capacity = INITIAL_QUEUE_SIZE;
        Item[] copy = (Item[]) new Object[capacity];
        if (container != null) System.arraycopy(container, 0, copy, 0, size);
        container = copy;
    }

    private void expand() {
        if (container.length <= 512) resize(container.length + 12);
        resize(container.length * 2);
    }

    private void shrink() {
        resize(container.length / 2);
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size == container.length) expand();
        container[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException();
        if (size == container.length / 4) shrink();
        int index = StdRandom.uniform(size);
        Item item = container[index];
        container[index] = container[--size];
        container[size] = null;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int randomPosition = StdRandom.uniform(size);
        return container[randomPosition];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        StdOut.println("Hellow world!!!");
    }

}
