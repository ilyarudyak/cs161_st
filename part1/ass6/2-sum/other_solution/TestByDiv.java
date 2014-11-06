import java.util.*;
import java.io.*;

// this solution uses the idea to divide by 10000
// and put them into respective bucket
// if a+b in [-t,t] than a/t = b/t +- 1
// so we have to check only 3 buckets
public class TestByDiv{

    private static final int SIZE = 100000;
    private static final int SIZE_LIST = 1000000;
    private static final int TBOUND = 10000; 

    public static void main (String[] args){

        List<List<Long>> list = readFileToHash("small_100K.txt");
        long[] a = readFileToArray("small_100K.txt");
        boolean[] t = new boolean[2*TBOUND+1];

        searchFor2SUM(t, a, list);
        printCount(t);


    }

    private static void printMarked(boolean[] t){
        for (int i=0; i<t.length; i++)
            if (t[i] == true)
                System.out.println(i-TBOUND);
    }

    private static void printCount(boolean[] t){
        int count = 0;
        for (int i=0; i<t.length; i++)
            if (t[i] == true) 
                count++;
        System.out.println(count);
    }




    
    private static void searchFor2SUM(boolean[] t, long[] a,
             List<List<Long>> list){
        
        long x;
        int index;
        for (int i=0; i<SIZE; i++){
            x = a[i];
            index = Math.abs((int) x / TBOUND);
            check(t, x, list, index);
        }
    }

    private static void check(boolean[] t, Long x,
            List<List<Long>> list, int index){
        
        int i;
        if (index > 1) i=index-1;
        else i = index;
        
        for (; i<=index+1; i++){
            for (Long y: list.get(i))
                if (-TBOUND <= x+y && x+y <= TBOUND)
                    t[(int)(x+y) + TBOUND] = true;
        }

    }
    
    
    private static List<List<Long>> readFileToHash(String filename){

        List<List<Long>> list = new ArrayList<>();
        for (int i=0; i<SIZE_LIST; i++)
            list.add(new ArrayList<Long>());

        int index = 0;
        long n;

        try(Scanner in = new Scanner(new File(filename))){
            while(in.hasNextLong()){
                n = in.nextLong();
                index = Math.abs( (int) n / TBOUND );

                //System.out.println(index);
                
                list.get(index).add(n) ;
            }
        }
        catch (IOException e){System.out.println("no file");}
        //Collections.sort(a);
        return list;
        
    }

    private static long[] readFileToArray(String filename){

        long[] a = new long[SIZE];
        int index = 0;

        try(Scanner in = new Scanner(new File(filename))){
            while(in.hasNextLong())
                a[index++] = in.nextLong();
        }
        catch (IOException e){}
        //Arrays.sort(a);
        return a;
        
    }









} // end of class

