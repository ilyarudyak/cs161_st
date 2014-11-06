import java.util.*;
import java.io.*;

// this is final solution
public class TestFinal{

    private static final int SIZE = 1000000;
    private static final int TBOUND = 10000; 

    public static void main (String[] args){

        long[] a = readFileToHash("big.txt");
        boolean[] t = new boolean[2*TBOUND+1];

        //for (int j=a.length-1; j>=a.length-20; j--)
        //    System.out.println(a[0]+a[j]);
        //createSortedFile();
        searchFor2SUM(t, a);
        //System.out.println(Arrays.toString(t));
        //System.out.println(t[0]);
        //printMarked(t);
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




    
    private static void searchFor2SUM(boolean[] t, long[] a){
        
        long sum;
        int right = SIZE - 1;
        boolean flag;
        for(int i=0; i<SIZE/2; i++){
            
            flag = true;
            //System.out.println(right);
            
            for(int j=right; j>=SIZE/2; j--){
                sum = a[i] + a[j];
                
                if (sum < -TBOUND)
                    break;

                else if(-TBOUND <= sum && sum <= TBOUND){
                    if (flag){
                        right = j;
                        flag = false;
                    }
                    t[(int)sum + TBOUND] = true;
                }
            }
        }// end for
        
    }
    
    
    private static long[] readFileToHash(String filename){

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

    private static void createSortedFile(){

        long[] a = new long[SIZE];
        int index = 0;

        try(Scanner in = new Scanner(new File("small_100K.txt"))){
            while(in.hasNextLong())
                a[index++] = in.nextLong();
        }
        catch (IOException e){}
        Arrays.sort(a);

        try(PrintWriter out = new PrintWriter("sorted_100K.txt")){
            for(long n: a)
                out.println(n);
        }
        catch (IOException e){}
        
        
    }







} // end of class

