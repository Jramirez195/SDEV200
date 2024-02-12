/* Program Name: TwoWayLinkedList.java
   Author: Jose Ramirez
   Last Updated: 2/11/2024
   Summary: This class implement a doubly linked list data structure, providing methods to manipulate and traverse the list in both forward and backwards directions.
 */
package MyLinkedList;

public class TwoWayLinkedList<E> implements MyList<E> {
    private Node<E> head, tail;
    private int size = 0; // Number of elements in the list

    // Constructors and other methods from MyLinkedList class remain the same

    @Override
    public java.util.ListIterator<E> listIterator() {
        return new TwoWayLinkedListIterator(0);
    }

    @Override
    public java.util.ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return new TwoWayLinkedListIterator(index);
    }

    private class TwoWayLinkedListIterator implements java.util.ListIterator<E> {
        private Node<E> current;
        private int currentIndex;

        public TwoWayLinkedListIterator(int index) {
            if (index == size) {
                current = null;
                currentIndex = size;
            } else {
                current = getNode(index);
                currentIndex = index;
            }
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = current.element;
            current = current.next;
            currentIndex++;
            return result;
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (current == null) {
                current = tail;
            } else {
                current = current.previous;
            }
            currentIndex--;
            return current.element;
        }

        @Override
        public int nextIndex() {
            return currentIndex;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }
    }
}
