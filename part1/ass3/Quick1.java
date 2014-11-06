/*************************************************************************
 *  Compilation:  javac Quick.java
 *  Execution:    java Quick < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   http://algs4.cs.princeton.edu/23quicksort/tiny.txt
 *                http://algs4.cs.princeton.edu/23quicksort/words3.txt
 *
 *  Sorts a sequence of strings from standard input using quicksort.
 *   
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Quick < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *       
 *  % java Quick < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *
 *
 *  Remark: For a type-safe version that uses static generics, see
 *
 *    http://algs4.cs.princeton.edu/23quicksort/QuickPedantic.java
 *
 *************************************************************************/

/**
 *  The <tt>Quick</tt> class provides static methods for sorting an
 *  array and selecting the ith smallest element in an array using quicksort.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/21elementary">Section 2.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
import java.util.*;
public class Quick1 {

    private static int count;
    // This class should not be instantiated.
    public Quick1() { count = 0;}

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public void sort(Comparable[] a) {
        //StdRandom.shuffle(a);
        
        sort(a, 0, a.length - 1);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private void sort(Comparable[] a, int lo, int hi) { 
        if (hi <= lo) return;

        int j = partition3(a, lo, hi); // partition 1

        sort(a, lo, j-1);
        count += (j-1 - lo) + 1;

        sort(a, j+1, hi);
        count += (hi - (j + 1)) + 1;

        assert isSorted(a, lo, hi);
        
    }

    public int partition1(Comparable[] a, int l, int r) {
    
        Comparable p = a[l];
        int i = l + 1;
        for (int j=l+1; j<=r; j++){
            if (less(a[j], p)){
                exch(a, i, j);
                i++;
            }
        }// end of for

        //StdOut.println(Arrays.toString(a) + " l=" + l + " i=" + i);
        exch(a, l, i-1);
        //StdOut.println(Arrays.toString(a));
        //StdOut.println();
        
        return i-1;
    }

    public int partition2(Comparable[] a, int l, int r) {
    
        exch(a, l, r);
        Comparable p = a[l];
        int i = l + 1;
        for (int j=l+1; j<=r; j++){
            if (less(a[j], p)){
                exch(a, i, j);
                i++;
            }
        }// end of for

        //StdOut.println(Arrays.toString(a) + " l=" + l + " i=" + i);
        exch(a, l, i-1);
        //StdOut.println(Arrays.toString(a));
        //StdOut.println();
        
        return i-1;
    }

    public int partition3(Comparable[] a, int l, int r) {


        int N = r - l + 1;
        int m;
        if (N % 2 == 0)
            m = median3(a, l, l + N/2-1, r);
        else 
            m = median3(a, l, l + N/2, r);
        
        //StdOut.println("a[m]=" + a[m]);
        exch(a, m, l);
    
        Comparable p = a[l];

        
        int i = l + 1;
        for (int j=l+1; j<=r; j++){
            if (less(a[j], p)){
                exch(a, i, j);
                i++;
            }
        }// end of for

        //StdOut.println(Arrays.toString(a) + " l=" + l + " i=" + i);
        exch(a, l, i-1);
        //StdOut.println(Arrays.toString(a));
        //StdOut.println();
        
        return i-1;
    }

    
    
    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) { 

            // find item on lo to swap
            while (less(a[++i], v))
                if (i == hi) break;

            // find item on hi to swap
            while (less(v, a[--j]))
                if (j == lo) break;      // redundant since a[lo] acts as sentinel

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }




   /***********************************************************************
    *  Helper sorting functions
    ***********************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }
        
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // return the index of the median element among a[i], a[j], and a[k]
    private static int median3(Comparable[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
               (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
               (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }


   /***********************************************************************
    *  Check if array is sorted - useful for debugging
    ***********************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }


    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    /**
     * Reads in a sequence of strings from standard input; quicksorts them; 
     * and prints them to standard output in ascending order. 
     * Shuffles the array and then prints the strings again to
     * standard output, but this time, using the select method.
     */
    public static void main(String[] args) {

        int[] aux = StdIn.readAllInts();
        Integer[] a = new Integer[aux.length];
        for (int i=0; i<aux.length; i++)
            a[i] = aux[i];
        
        //Integer[] a = {3,8,2,5,1,4,7,0};
        //Integer[] a = {8,2,4,5,7,1};
        
        Quick1 quick = new Quick1();
        //quick.partition3(a, 0, a.length-1);
        quick.sort(a);

        //show(a);
        StdOut.println("count = " + count);

        
    }

}
