/* *****************************************************************************
 *  Name:              João Pedro Vasques da Conceição
 *  Last modified:     September 12, 2021
 *
 *  Description:
 *  A client program for the RandomizedQueue data structure.
 *  Takes an integer 'k' as a command-line argument;
 *  Reads a sequence of strings from standard input;
 *  Prints exactly 'k' of these strings.
 **************************************************************************** */


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        // Numb of strings to be printed
        int k = Integer.parseInt(args[0]);

        // Create a randomized queue
        RandomizedQueue<String> rQueue = new RandomizedQueue<>();

        // Adding all strings from standard input to the randomized queue
        while (!StdIn.isEmpty()) {
            rQueue.enqueue(StdIn.readString());
        }

        // Remove and print k random strings from the randomized queue
        for (
                int i = 0;
                i < k; i++) {
            StdOut.println(rQueue.dequeue());
        }
    }
}

