import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size = 0;
    private Node first;
    private Node last;

    private class Node {
        private final Item item;
        private Node next;
        private Node previous;

        Node(Item item) {
            this.item = item;
        }
    }

    private class LinkedListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public Deque() {

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }


    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node node = new Node(item);
        if (first == null) {
            first = node;
            last = first;
        }
        else {
            node.next = first;
            first.previous = node;
            first = node;
        }
        size++;
        return;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node node = new Node(item);
        if (last == null) {
            last = node;
            first = last;
        }
        else {
            node.previous = last;
            last.next = node;
            last = node;
        }
        size++;
        return;
    }

    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        }
        else {
            first.previous = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        if (last == null) {
            first = null;
        }
        else {
            last.next = null;
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    public static void main(String[] args) {
        StdOut.println("Hello World!!!");
    }

}
