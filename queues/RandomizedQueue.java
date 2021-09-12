/* *****************************************************************************
 *  Name:              João Pedro Vasques da Conceição
 *  Last modified:     September 12, 2021
 *
 *  Description:
 *  A Randomized Queue implementation using resizing arrays.
 *  A Randomized Queue is a data structure that removes elements
 *  uniformly at random.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INIT_SIZE = 1; // Initial size of the array
    private Item[] itemsArray;  // Array
    private int numberItems;  // Counter of elements in the struct

    // construct an empty randomized queue
    public RandomizedQueue() {

        itemsArray = (Item[]) new Object[INIT_SIZE]; // Initialize array
        numberItems = 0;  // Initialize counter
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (numberItems == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return numberItems;
    }

    // add an item
    public void enqueue(Item item) {
        // if item is invalid, throw an error
        if (item == null) throw new IllegalArgumentException("Invalid item");

        // Update counter of elements
        numberItems++;

        // If array too small to add a new element,
        if (numberItems >= itemsArray.length) {
            // create new array with double the size
            Item[] copy = (Item[]) new Object[2 * itemsArray.length];

            // copy elements into new array
            for (int i = 0; i < numberItems; i++) {
                copy[i] = itemsArray[i];
            }
            itemsArray = copy;
        }

        // Add new element to array
        itemsArray[numberItems - 1] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        // if item is rQueue is empty, throw an error
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");

        // Get a random uniform number,between 0 and
        // the total number of elements in the data structure
        int randomNum = StdRandom.uniform(numberItems);

        // Get the element in array at the index
        // that equals to the random number
        Item item = itemsArray[randomNum];

        // If it is not the last element in the array
        if (randomNum != numberItems - 1) {
            // Copy the last element into the removed element's index
            itemsArray[randomNum] = itemsArray[numberItems - 1];
        }

        // Make the last element's index equal to null
        itemsArray[numberItems - 1] = null;

        // Update counter of elements
        numberItems--;

        // If the array is too big for the amount
        // of elements in the data structure
        if (numberItems > 0 && numberItems <= itemsArray.length / 4) {
            // create a new array smaller array with half of the length
            Item[] copy = (Item[]) new Object[itemsArray.length / 2];

            // copy elements into new array
            for (int i = 0; i <= numberItems; i++) {
                copy[i] = itemsArray[i];
            }

            itemsArray = copy;
        }

        // Return element's value
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        // if item is rQueue is empty, throw an error
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");

        // Get a random uniform number,
        // between 0 and the total number of elements
        int randomNum = StdRandom.uniform(numberItems);

        // Get the element in thea array at
        // index equal to the random number
        Item item = itemsArray[randomNum];

        // Return element's value
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private final Item[] iteratorArray;
        private int nextIndex;

        public RandomizedQueueIterator() {
            // Create an array for the iterator
            iteratorArray = (Item[]) new Object[numberItems];

            // Copy values from itemsArray into iteratorArray
            for (int i = 0; i < numberItems; i++) {
                iteratorArray[i] = itemsArray[i];
            }

            // Randomize the order of iteratorArray
            StdRandom.shuffle(iteratorArray);

            // Start iteration at index 0
            nextIndex = 0;
        }

        public boolean hasNext() {
            return !(nextIndex >= iteratorArray.length);
        }

        public void remove() {
            throw new UnsupportedOperationException("Don't remove items from the iterator");
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No more elements");

            // Get next item
            Item item = iteratorArray[nextIndex];

            // Update nextIndex
            nextIndex++;

            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> x = new RandomizedQueue<>();

        // Empty rQueue
        StdOut.println("Is empty? " + x.isEmpty());
        StdOut.println("Size: " + x.size());
        StdOut.println("");

        // Fill rQueue
        int n = 100;
        StdOut.println("Add " + n + " strings, while removing half of them randomly:");
        for (int i = 0; i < n; i++) {
            // Add item
            x.enqueue("" + i);

            // Remove half of the items while adding
            if (i % 2 == 0) StdOut.println("Random item " + i + ": " + x.dequeue());
        }
        StdOut.println("Is empty? " + x.isEmpty());
        StdOut.println("Size: " + x.size());
        StdOut.println("");


        // Remove n/4 random items;
        StdOut.println("Remove " + n / 4 + " random items:");
        for (int i = 0; i < (n / 4); i++) {
            StdOut.println("Random item " + i + ": " + x.dequeue());
        }
        StdOut.println("Size: " + x.size());
        StdOut.println("");

        // Create an iterator of the remaining items
        StdOut.println("Generate an iterator of remaining items:");
        Iterator<String> it = x.iterator();
        // Loop through iterator
        while (it.hasNext()) {
            StdOut.println(it.next());
        }
        StdOut.println("");

        // Get n/4 values without removing them, using '.sample()'
        StdOut.println("Get " + n / 4 + " values without removing them");
        for (int i = 0; i < (n / 4); i++) {
            StdOut.println("Random item " + i + ": " + x.sample());
        }
        StdOut.println("Size: " + x.size());
        StdOut.println("");
    }
}

