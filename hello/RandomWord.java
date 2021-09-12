/* *****************************************************************************
 *  Name:              João Pedro Vasques da Conceição
 *  Last modified:     September 12, 2021
 *
 *  Description:
 *  Reads a sequence of words from standard input
 *  and prints one of those words uniformly at random
 *  using Knuth's method (probability = 1/i for ith word)
 **************************************************************************** */

// Import Princeton's algs4 libraries, as required by the course

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        double wordCounter = 0.0; // Number of words
        String selected = "";  // Randomly selected word

        // While you have words in the standard input
        while (!StdIn.isEmpty()) {

            String s = StdIn.readString(); // Read word
            wordCounter++; // Update counter

            // Calculate probability
            if (StdRandom.bernoulli(1 / wordCounter)) {
                selected = s;
            }
        }
        StdOut.println(selected);
    }
}
