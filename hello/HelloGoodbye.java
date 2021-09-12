/* *****************************************************************************
 *  Name:              João Pedro Vasques da Conceição
 *  Last modified:     September 12, 2021
 *
 *  Description:
 *  Takes two names as command-line arguments and prints hello and goodbye
 *  messages, with the names for the hello message in the same order as the
 *  command-line arguments and with the names for the goodbye message in
 *  reverse order.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class HelloGoodbye {
    public static void main(String[] args) {
        String name1 = args[0];  // First command argument/name
        String name2 = args[1];  // Second command argument/name
        StdOut.printf("Hello %s and %s.\n", name1, name2);
        StdOut.printf("Goodbye %s and %s.\n", name2, name1);
    }
}
