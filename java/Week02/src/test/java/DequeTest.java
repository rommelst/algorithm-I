import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {

    static final int LOOP = 100;
    static final int QUEUE_SIZE = 256000;

    public static void printObjectSize(Object object) {
        System.out.println("Object type: " + object.getClass() +
                ", size: " + InstrumentationAgent.getObjectSize(object) + " bytes");
    }

    @Test
    public void isEmptyWhenNoDataSuppliedExpectsTrue() {

        // Arrange
        Deque<Integer> queue = new Deque<>();

        // Act

        // Assert
        assertTrue(queue.isEmpty());
    }

    @Test
    public void isEmptyWhenOneDataIsSuppliedExpectsFalse() {

        // Arrange
        Deque<Integer> queue = new Deque<>();

        // Act
        queue.addFirst(1);

        // Assert
        assertFalse(queue.isEmpty());
    }

    @Test
    public void sizeWhenNoDataSuppliedExpectsZero() {

        // Arrange
        Deque<Integer> queue = new Deque<>();

        // Act

        // Assert
        assertEquals(0, queue.size());
    }

    @Test
    public void sizeWhenTwoDataAreSuppliedExpectsTwo() {

        // Arrange
        Deque<Integer> queue = new Deque<>();

        // Act
        queue.addFirst(1);
        queue.addFirst(2);

        // Assert
        assertEquals(2, queue.size());
    }

    @Test
    public void sizeWhenDataSuppliedAreFrontAndBackExpectsTwo() {

        // Arrange
        Deque<Integer> queue = new Deque<>();

        // Act
        queue.addFirst(1);
        queue.addLast(2);

        // Assert
        assertEquals(2, queue.size());
    }

    @Test
    public void sizeWhen49DataAreSuppliedInFrontExpects49() {

        // Arrange
        Deque<Integer> queue = new Deque<>();

        // Act
        for ( int i = 0; i < 49; i++)
            queue.addFirst(i);

        // Assert
        assertEquals(49, queue.size());
    }

    @Test
    public void sizeWhen49DataAreSuppliedInLastExpects49() {

        // Arrange
        Deque<Integer> queue = new Deque<>();

        // Act
        for ( int i = 0; i < 49; i++)
            queue.addLast(i);

        // Assert
        assertEquals(49, queue.size());
    }

    @Test
    public void addFirstWhenNullIsSuppliedExpectsIllegalArgumentException() {

        // Arrange
        Deque<Integer> queue = new Deque<>();

        // Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {

            // Act
            queue.addFirst(null);
        });
    }

    @Test
    public void addLastWhenNullIsSuppliedExpectsIllegalArgumentException() {

        // Arrange
        Deque<Integer> queue = new Deque<>();

        // Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {

            // Act
            queue.addLast(null);
        });
    }

    @Test
    public void removeFirstWhenQueueIsEmptyExpectsNoSuchElementException() {

        // Arrange
        Deque<Integer> queue = new Deque<>();

        // Assert
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {

            // Act
            queue.removeFirst();
        });
    }

    @Test
    public void removeFirstWhenOneDataIsSuppliedExpectsDataAndSizeZero() {

        // Arrange
        Deque<Integer> queue = new Deque<>();
        queue.addFirst(1);

        // Act
        int value = queue.removeFirst();


        // Assert
        assertEquals(1, value);
        assertEquals(0, queue.size());
    }

    @Test
    public void removeFirstWhenManyDataAreSuppliedExpectsDataAsInStack() {

        // Arrange
        Deque<Integer> queue = new Deque<>();
        queue.addFirst(1);
        queue.addFirst(2);
        queue.addFirst(3);
        queue.addFirst(4);
        queue.addFirst(5);

        // Act
        int value01 = queue.removeFirst();
        int value02 = queue.removeFirst();
        int value03 = queue.removeFirst();
        int value04 = queue.removeFirst();
        int value05 = queue.removeFirst();

        // Assert
        assertEquals(5, value01);
        assertEquals(4, value02);
        assertEquals(3, value03);
        assertEquals(2, value04);
        assertEquals(1, value05);
    }

    @Test
    public void removeFirstWhenManyDataAreSuppliedExpectsDataAsInQueue() {

        // Arrange
        Deque<Integer> queue = new Deque<>();
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        queue.addLast(4);
        queue.addLast(5);

        // Act
        int value01 = queue.removeFirst();
        int value02 = queue.removeFirst();
        int value03 = queue.removeFirst();
        int value04 = queue.removeFirst();
        int value05 = queue.removeFirst();

        // Assert
        assertEquals(1, value01);
        assertEquals(2, value02);
        assertEquals(3, value03);
        assertEquals(4, value04);
        assertEquals(5, value05);
    }

    @Test
    public void removeLastWhenQueueIsEmptyExpectsNoSuchElementException() {

        // Arrange
        Deque<Integer> queue = new Deque<>();

        // Assert
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {

            // Act
            queue.removeLast();
        });
    }

    @Test
    public void removeLastWhenOneDataIsSuppliedExpectsDataAndSizeZero() {

        // Arrange
        Deque<Integer> queue = new Deque<>();
        queue.addLast(1);

        // Act
        int value = queue.removeLast();

        // Assert
        assertEquals(1, value);
        assertEquals(0, queue.size());
    }

    @Test
    public void removeLastWhenManyDataAreSuppliedExpectsDataAsInStack() {

        // Arrange
        Deque<Integer> queue = new Deque<>();
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        queue.addLast(4);
        queue.addLast(5);

        // Act
        int value01 = queue.removeLast();
        int value02 = queue.removeLast();
        int value03 = queue.removeLast();
        int value04 = queue.removeLast();
        int value05 = queue.removeLast();

        // Assert
        assertEquals(5, value01);
        assertEquals(4, value02);
        assertEquals(3, value03);
        assertEquals(2, value04);
        assertEquals(1, value05);

    }

    @Test
    public void removeLastWhenManyDataAreSuppliedInFrontExpectsDataReturnedAsInQueue() {

        // Arrange
        Deque<Integer> queue = new Deque<>();
        queue.addFirst(1);
        queue.addFirst(2);
        queue.addFirst(3);
        queue.addFirst(4);
        queue.addFirst(5);

        // Act
        int value01 = queue.removeLast();
        int value02 = queue.removeLast();
        int value03 = queue.removeLast();
        int value04 = queue.removeLast();
        int value05 = queue.removeLast();

        // Assert
        assertEquals(1, value01);
        assertEquals(2, value02);
        assertEquals(3, value03);
        assertEquals(4, value04);
        assertEquals(5, value05);
    }

    @Test
    public void removeFirstAndLastWhenManyDataAreSuppliedInFrontAndBackExpectsDataInCorrectOrder() {

        final int SIZE = 25000;

        // Arrange
        Deque<Integer> queue = new Deque<>();
        for ( int i = 0 ; i < SIZE; i++) {
            queue.addFirst(i);
            queue.addLast(i * -1);
        }

        for ( int i = SIZE - 1 ; i >= 0; i--) {
            // Act
            int value01 = queue.removeFirst();
            int value02 = queue.removeLast();


            // Assert
            assertEquals(i, value01);
            assertEquals(i * -1, value02);
        }

    }

    @Test
    public void removeFirstAndLastWhenManyDataAreSuppliedInBackAndFromExpectsDataInCorrectOrder() {

        // Arrange
        Deque<Integer> queue = new Deque<>();
        for ( int i = 0 ; i < QUEUE_SIZE; i++) {
            queue.addLast(i * -1);
            queue.addFirst(i);
        }

        for ( int i = QUEUE_SIZE - 1 ; i >= 0; i--) {
            // Act
            int value01 = queue.removeLast();
            int value02 = queue.removeFirst();

            // Assert
            assertEquals(i * -1, value01);
            assertEquals(i, value02);
        }

    }

    @Test
    public void iteratorHasNextWhenQueueIsEmptyExpectsFalse() {

        // Arrange
        Deque<Integer> queue = new Deque<>();

        // Act
        boolean value = queue.iterator().hasNext();

        // Assert
        assertFalse(value);

    }

    @Test
    public void iteratorHasNextWhenQueueHasOneElementExpectsTrue() {

        // Arrange
        Deque<Integer> queue = new Deque<>();
        queue.addFirst(1);

        // Act
        boolean value = queue.iterator().hasNext();

        // Assert
        assertTrue(value);
    }

    @Test
    public void iteratorHasNextWhenQueueHasTwoElementExpectsTrue() {

        // Arrange
        Deque<Integer> queue = new Deque<>();
        queue.addFirst(1);
        queue.addFirst(2);

        // Act
        boolean value = queue.iterator().hasNext();

        // Assert
        assertTrue(value);
    }

    @Test
    public void iteratorNextWhenQueueIsEmptyExpectsNoSuchElementException() {

        // Arrange
        Deque<Integer> queue = new Deque<>();

        // Assert
        assertThrows(NoSuchElementException.class, () -> {

            // Act
            queue.iterator().next();
        });
    }

    @Test
    public void iteratorNextWhenQueueHasOneElementExpectsNoSuchElementException() {

        // Arrange
        Deque<Integer> queue = new Deque<>();
        queue.addFirst(1);
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

        // Using addFirst
        for (int i = 0; i < LOOP; i++) {
            int qty = StdRandom.uniform(QUEUE_SIZE);

            // Arrange
            Deque<Integer> queue = new Deque<>();
            for ( int j = 0; j < qty; j++) queue.addFirst(j);
            Iterator<Integer> it = queue.iterator();
            for ( int j = 0; j < qty; j++) it.next();

            // Assert
            assertThrows(NoSuchElementException.class, () -> {

                // Act
                it.next();
            });
        }

        // Using addLast
        for (int i = 0; i < LOOP; i++) {
            int qty = StdRandom.uniform(QUEUE_SIZE);

            // Arrange
            Deque<Integer> queue = new Deque<>();
            for ( int j = 0; j < qty; j++) queue.addLast(j);
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
            Deque<Integer> queue = new Deque<>();
            for ( int j = 0; j < qty; j++) queue.addFirst(j);
            Iterator<Integer> it = queue.iterator();

            for ( int j = qty - 1; j >= 0 ; j--) {

                // Act
                int value = it.next();

                // Assert
                assertEquals(j, value);
            }
        }
    }

    @Test
    public void iteratorRemoveExpectsUnsupportedOperationException() {

        // Arrange
        Deque<Integer> queue = new Deque<>();

        // Assert
        assertThrows(UnsupportedOperationException.class, () -> {

            // Act
            queue.iterator().remove();
        });
    }

}