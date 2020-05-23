import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeWithCircularArray<Item> implements Iterable<Item> {

    private  static final int INITIAL_QUEUE_SIZE = 4;

    private Item[] container;
    private int front = 0;
    private int rear = 10;

    private class ArrayIterator implements Iterator<Item> {

        private int current;

        private ArrayIterator() {
            current = front;
        }

        @Override
        public boolean hasNext() {
            return current != -1;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = container[current];
            if (current == rear) current = -1;
            else current++;
            if (current == container.length) current = 0;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // construct an empty deque
    public DequeWithCircularArray() {
        resize(INITIAL_QUEUE_SIZE);
        front = -1;
        rear = 0;
    }

    private void resize(int capacity)
    {
//        System.out.println("["+container.length+"]["+capacity+"]");
        if (capacity < INITIAL_QUEUE_SIZE) capacity = INITIAL_QUEUE_SIZE;
        Item[] copy = (Item[]) new Object[capacity];
        int actualSize = size();

        if (container != null) {
            // Just one item
            if (front == rear) {
                // Copy rear
                System.arraycopy(container, front, copy, 0, actualSize);
                front = 0;
                rear = 0;
            }

            // Data is contiguous
            else if (front < rear) {
                // Copy rear
                System.arraycopy(container, front, copy, 0, actualSize);
                front = 0;
                rear = actualSize - 1;
            }

            // Front is at the end of container
            // rear is at the beginning of the container;
            else {

                // Copy rear
                System.arraycopy(container, 0, copy, 0, rear + 1);

                // Copy front
                if (front > 0) {
                    int frontQty = container.length - front;
                    int newPos = capacity - frontQty;
                    System.arraycopy(container, front, copy, newPos, frontQty);
                    front = newPos;
                }
            }
        }
        container = copy;
    }

    private void expand() {
        if (container.length <= 512) resize(container.length + 12);
        else resize(container.length * 2);
    }

    private void shrink() {
        resize(container.length / 2);
    }

    private boolean isFull() {
        return ((front == 0 && rear == container.length-1) ||
                front == rear+1);
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        if (front == -1) return 0;
        if (front == rear) return 1;
        if (front > rear) {
            return (rear + 1) + (container.length - front);
        }
        return rear - front + 1;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isFull()) expand();
        if (front == -1) {
            front = 0;
            rear = 0;
        }
        else if (front == 0) front = container.length - 1;
        else front--;
        container[front] = item;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isFull()) expand();
        if (front == -1) {
            front = 0;
            rear = 0;
        }
        else if (rear == container.length - 1) rear = 0;
        else rear++;
        container[rear] = item;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        if (size() == container.length / 4) shrink();
        Item item = container[front];
        container[front] = null;
        if (front == rear) {
            front = -1;
            rear = -1;
        }
        else {
            if (front == container.length - 1) front = 0;
            else front++;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        if (size() == container.length / 4) shrink();
        Item item = container[rear];
        container[rear] = null;
        if (front == rear) {
            front = -1;
            rear = -1;
        }
        else if (rear == 0) rear = container.length - 1;
        else rear--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

        DequeWithCircularArray<String> queue = new DequeWithCircularArray<String>();
        queue.addLast("Donald");
        queue.addLast("Patinhas");
        queue.addLast("Huguinho");
        queue.addLast("Zezinho");
        queue.addLast("Luizinho");
        queue.addLast("Dayse");
        queue.addLast("April");
        queue.addLast("May");
        queue.addLast("June");
        queue.addLast("Patolino");
        queue.addLast("Pateta");

        while (!queue.isEmpty()) {
            for (String item : queue) {
                System.out.println(item);
            }
            queue.removeFirst();
//            deque.removeLast();
        }

    }
}


