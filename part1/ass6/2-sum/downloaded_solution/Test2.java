import java.util.*;
import java.io.*;

// here we use TwoSumOptimizedSolver
public class Test2{

    private static final int SIZE = 100000;

    public static void main (String[] args){

        long[] a = readFileToArray("small_100K.txt");

        TwoSumOptimizedSolver solver = 
            new TwoSumOptimizedSolver(a, -10000, 10000);
        System.out.println(solver.solve());

    }


    
    
    private static long[] readFileToArray(String filename){

        long[] a = new long[SIZE];
        int index = 0;

        try(Scanner in = new Scanner(new File(filename))){
            while(in.hasNextLong())
                a[index++] = in.nextLong();
        }
        catch (IOException e){}
        Arrays.sort(a);
        return a;
        
    }

 







} // end of class


