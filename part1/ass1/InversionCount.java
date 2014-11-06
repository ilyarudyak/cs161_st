import java.util.*;


public class InversionCount{

    
    public static long sort(Comparable[] a){
        return sort (a, 0, a.length-1);
    }

    private static long sort(Comparable[] a, int lo, int hi){

        long inversionCount = 0;

        Comparable[] aux = new Comparable[a.length];
        if (hi > lo) {
            int mid = lo + (hi-lo) / 2;
            inversionCount += sort(a, lo, mid);
            inversionCount += sort(a, mid+1, hi);
            inversionCount += merge(a, aux, lo, mid, hi);
        }
        return inversionCount;

    }


    private static long merge (Comparable[] a, Comparable[] aux, 
            int lo, int mid, int hi){

        long inversionCount = 0;

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }


        int i = lo, j = mid+1;
        for (int k=lo; k<=hi; k++){
            
            if      (i > mid)                    a[k] = aux[j++];
            else if (j > hi)                     a[k] = aux[i++];
            else if (aux[i].compareTo(aux[j]) < 0)   a[k] = aux[i++];
            else {                               a[k] = aux[j++];

                // key line 
                inversionCount += mid - i + 1;
            }
        }
        return inversionCount;
    }

} // end of class
