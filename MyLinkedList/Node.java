/* Program Name: Node.java
   Author: Jose Ramirez
   Last Updated: 2/11/2024
   Summary: This short program represents a node in a doubly linked list with an element which references the next node and references the previous node.

 */

package MyLinkedList;

public class Node<E> {
    E element;
    Node<E> next;
    Node<E> previous;

    public Node(E e){
    element = e;
    }
}