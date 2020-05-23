import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RandomizedQueueTest {

    static final int LOOP = 100;
    static final int QUEUE_SIZE = 10000;

    @Test
    public void isEmptyWhenNoDataSuppliedExpectsTrue() {

        // Arrange
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        // Act
        boolean value = queue.isEmpty();

        // Assert
        assertTrue(value);
    }

    @Test
    public void isEmptyWhenOneDataIsSuppliedExpectsFalse() {

        // Arrange
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);

        // Act
        boolean value = queue.isEmpty();

        // Assert
        assertFalse(value);
    }

    @Test
    public void sizeWhenNoDataSuppliedExpectsZero() {

        // Arrange
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        // Act
        int value = queue.size();

        // Assert
        assertEquals(0, value);
    }

    @Test
    public void sizeWhenOneDataSuppliedExpectsOne() {

        // Arrange
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);

        // Act
        int value = queue.size();

        // Assert
        assertEquals(1, value);
    }

    @Test
    public void sizeWhenTwoDataSuppliedExpectsTwo() {

        // Arrange
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);

        // Act
        int value = queue.size();

        // Assert
        assertEquals(2, value);
    }

    @Test
    public void sizeWhenRandomQtyDataAreSuppliedExpectsDataSize() {

        // Arrange
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        int qty = StdRandom.uniform(QUEUE_SIZE);
        for (int n =0; n < qty; n++)
            queue.enqueue(n);

        // Act
        int value = queue.size();

        // Assert
        assertEquals(qty, value);
    }

    @Test
    public void sampleWhenNoDataIsSuppliedExpectsNoSuchElementException() {

        // Arrange
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        // Assert
        assertThrows(NoSuchElementException.class, () -> {

            // Act
            queue.sample();
        });
    }

    @Test
    public void sampleWhenRandomQtyDataAreSuppliedExpectsAllSuppliedDataInRandomOrder() {

        // Arrange
        Set<Integer> set = new HashSet<Integer>();
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        int qty = StdRandom.uniform(QUEUE_SIZE);
        for (int n = 0; n < qty; n++) {
            set.add(n);
            queue.enqueue(n);
        }

        for (int n = 0; n < qty; n++) {

            // Act
            int value = queue.sample();

            // Assert
            assertTrue(set.contains(value));
        }
    }

    @Test
    public void enqueueWhenNoDataIsSuppliedExpectsIllegalArgumentException() {

        // Arrange
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {

            // Act
            queue.enqueue(null);
        });
    }

    @Test
    public void dequeueWhenNoDataIsSuppliedExpectsNoSuchElementException() {

        // Arrange
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        // Assert
        assertThrows(NoSuchElementException.class, () -> {

            // Act
            queue.dequeue();
        });
    }

    @Test
    public void dequeueWhenRandomQtyDataAreSuppliedExpectsAllSuppliedDataInRandomOrder() {

        // Arrange
        Set<Integer> set = new HashSet<Integer>();
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        int qty = StdRandom.uniform(QUEUE_SIZE);
        for (int n = 0; n < qty; n++) {
            set.add(n);
            queue.enqueue(n);
        }

        int qtyLeft = qty;
        for (int n = 0; n < qty; n++) {

            // Act
            Integer value = queue.dequeue();

            // Assert
            assertNotNull(value);
            assertTrue(set.contains(value));
            assertEquals(--qtyLeft, queue.size());
        }
    }


    @Test
    public void iteratorHasNextWhenQueueIsEmptyExpectsFalse() {

        // Arrange
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        // Act
        boolean value = queue.iterator().hasNext();

        // Assert
        assertFalse(value);

    }

    @Test
    public void iteratorHasNextWhenQueueHasOneElementExpectsTrue() {

        // Arrange
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);

        // Act
        boolean value = queue.iterator().hasNext();

        // Assert
        assertTrue(value);
    }

    @Test
    public void iteratorHasNextWhenQueueHasTwoElementExpectsTrue() {

        // Arrange
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);

        // Act
        boolean value = queue.iterator().hasNext();

        // Assert
        assertTrue(value);
    }

    @Test
    public void iteratorNextWhenQueueIsEmptyExpectsNoSuchElementException() {

        // Arrange
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        // Assert
        assertThrows(NoSuchElementException.class, () -> {

            // Act
            queue.iterator().next();
        });
    }

    @Test
    public void iteratorNextWhenQueueHasOneElementExpectsNoSuchElementException() {

        // Arrange
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        Iterator<Integer> it = queue.iterator();
        it.next();

        // Assert
        assertThrows(NoSuchElementException.class, () -> {

            // Act
            it.next();
        });
    }

    @Test
    public void iteratorNextWhenQueueHasRandomQtyOfElementExpectsNoSuchElementException() {

        for (int i = 0; i < LOOP; i++) {
            int qty = StdRandom.uniform(QUEUE_SIZE);

            // Arrange
            RandomizedQueue<Integer> queue = new RandomizedQueue<>();
            for ( int j = 0; j < qty; j++) queue.enqueue(j * 1000);
            Iterator<Integer> it = queue.iterator();
            for ( int j = 0; j < qty; j++) it.next();

            // Assert
            assertThrows(NoSuchElementException.class, () -> {

                // Act
                it.next();
            });
        }
    }

    @Test
    public void iteratorNextWhenRandomDataIsSuppliedExpectsSuppliedData() {

        // Using addFirst
        for (int i = 0; i < LOOP; i++) {
            int qty = StdRandom.uniform(QUEUE_SIZE);

            // Arrange
            Set<Integer> set = new HashSet<>();
            RandomizedQueue<Integer> queue = new RandomizedQueue<>();
            for ( int j = 0; j < qty; j++) {
                queue.enqueue(j * 1000);
                set.add(j * 1000);
            }
            Iterator<Integer> it = queue.iterator();

            for ( int j = 0; j < qty ; j++) {

                // Act
                Integer value = it.next();

                // Assert
                assertEquals(qty, queue.size());
                assertNotNull(value);
                assertTrue(set.contains(value));

                set.remove(value);
            }

            // Assert
            assertFalse(it.hasNext());
        }
    }

    @Test
    public void iteratorRemoveExpectsUnsupportedOperationException() {

        // Arrange
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        // Assert
        assertThrows(UnsupportedOperationException.class, () -> {

            // Act
            queue.iterator().remove();
        });
    }

}