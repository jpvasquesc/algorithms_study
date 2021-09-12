/* *****************************************************************************
 *  Name:              João Pedro Vasques da Conceição
 *  Last modified:     September 12, 2021
 *
 *  Description:
 *  A Deque implementation using linked lists.
 *  A Deque or double-ended queue is a data structure that supports
 *  adding and removing items from both ends (front and back).
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node front; // front/first element of the data structure
    private Node rear;  // rear/last element of the data structure
    private int total;  // total number of elements

    private class Node {
        // An element in the struct
        private Item item;  // the stored in this element
        private Node next = null;  // proceeding element
        private Node prev = null;  // preceding element
    }

    // construct an empty deque
    public Deque() {
        front = null;
        rear = null;
        total = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (total == 0);
    }

    // return the number of items on the deque
    public int size() {
        return total;
    }

    // add the item to the front
    public void addFirst(Item item) {

        if (item == null) throw new IllegalArgumentException("Invalid item");

        // If Deque is empty this will be the first element
        // Therefore it is both the front and rear elements
        if (isEmpty()) {
            front = new Node();
            rear = front;  // Assign element to the front and rear
            front.item = item;
        }

        // If Deque is not empty
        else {
            // Save reference to the old front element
            Node oldFront = front;

            // Create a new front element with the input item value
            front = new Node();
            front.item = item;

            // Link old front element and new front element
            front.next = oldFront;
            oldFront.prev = front;
        }

        total++; // Update total number of elements
    }

    // add the item to the back
    public void addLast(Item item) {

        if (item == null) throw new IllegalArgumentException("Invalid item");

        // If Deque is empty this will be the first element
        // Therefore it is both the front and rear node
        if (isEmpty()) {
            rear = new Node();
            front = rear;  // Assign element to the front and rear
            rear.item = item;
        }

        // If Deque is not empty
        else {
            // Save reference to the old rear element
            Node oldLast = rear;

            // Create a new element with the input item value
            rear = new Node();
            rear.item = item;

            // Link old rear element and new rear element
            rear.prev = oldLast;
            oldLast.next = rear;
        }

        total++;  // Update total number of elements
    }

    // remove and return the item from the front
    public Item removeFirst() {
        // If the Deque is empty return error, otherwise it will break the counter
        if (isEmpty()) throw new java.util.NoSuchElementException("Empty queue");

        // Get the item value from the front element
        Item item = front.item;

        // If the Deque has more than 1 element
        if (total > 1) {
            // Reference the new front element
            front = front.next;

            // Delete reference to old front element for garbage collection
            front.prev = null;
        }

        // Else, this element is the only one in the Deque,
        // therefore when we remove it the Deque is empty.
        else {
            front = null;
            rear = null;
        }

        total--;
        return item;
    }

    // remove and return the element from the back
    public Item removeLast() {
        // If the Deque is empty return error, otherwise it will break the counter
        if (isEmpty()) throw new java.util.NoSuchElementException("Empty queue");

        // Get the item value from the rear element
        Item item = rear.item;

        // If Deque has more than 1 element
        if (total > 1) {
            // Reference the new rear element
            rear = rear.prev;
            // Delete reference to old rear element for garbage collection
            rear.next = null;
        }

        // Else, this element is the only one in the Deque
        // Therefore when we remove it the Deque is empty.
        else {
            front = null;
            rear = null;
        }

        total--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current;

        public DequeIterator() {
            current = front;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Don't remove items from the Deque");
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("No more elements");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> x = new Deque<>();

        // Start with empty Deque, add to the front remove from the front
        StdOut.println("Start with empty Deque, add to the front remove from the front:");
        StdOut.println("Is empty? " + x.isEmpty());
        StdOut.println("Add '1' to the front");
        x.addFirst(1);
        StdOut.println("Is empty? " + x.isEmpty());
        StdOut.println("First element: " + x.removeFirst());
        StdOut.println("Is empty? " + x.isEmpty());


        // Start with empty Deque, add to the front remove from the back
        StdOut.println("\nStart with empty Deque, add to the front remove from the back:");
        StdOut.println("Is empty? " + x.isEmpty());
        StdOut.println("Add '1' to the front");
        x.addFirst(1);
        StdOut.println("Is empty? " + x.isEmpty());
        StdOut.println("Last element: " + x.removeLast());
        StdOut.println("Is empty? " + x.isEmpty());

        // Start with empty Deque, add to the back remove from the front
        StdOut.println("\nStart with empty Deque, add to the back remove from the front:");
        StdOut.println("Is empty? " + x.isEmpty());
        StdOut.println("Add '22' to the back");
        x.addLast(22);
        StdOut.println("Is empty? " + x.isEmpty());
        StdOut.println("First element: " + x.removeFirst());
        StdOut.println("Is empty? " + x.isEmpty());

        // Start with empty Deque, add to the back remove from the back
        StdOut.println("\nStart with empty Deque, add to the back remove from the back:");
        StdOut.println("Is empty? " + x.isEmpty());
        StdOut.println("Add '22' to the back");
        x.addLast(22);
        StdOut.println("Is empty? " + x.isEmpty());
        StdOut.println("Last element: " + x.removeLast());
        StdOut.println("Is empty? " + x.isEmpty());

        // In a loop, add even nums in the front and odd nums back
        StdOut.println("\nIn a loop, add even nums in the front and odd nums back:");
        StdOut.println("Is empty? " + x.isEmpty());
        StdOut.println("Size: " + x.size());
        StdOut.println("Add nums");

        for (int i = 0; i <= 10; i++) {
            if (i % 2 == 0) x.addFirst(i);
            else x.addLast(i);
        }
        StdOut.println("Is empty? " + x.isEmpty());
        StdOut.println("Size: " + x.size());

        // Generate an iterator to check loop additions
        StdOut.println("\nGenerate an iterator to check loop additions:");
        Iterator<Integer> it = x.iterator();
        while (it.hasNext()) {
            StdOut.println("Iterator: " + it.next());
            StdOut.println("Has next? " + it.hasNext());
        }
    }
}
