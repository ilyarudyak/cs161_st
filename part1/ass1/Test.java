import java.util.*;
import java.io.*;

public class Test{

    public static void main (String[] args) throws IOException{

        Integer[] a = new Integer[100000];
        int i = 0;

        Scanner in = new Scanner(new File("IntegerArray.txt"));
        while(in.hasNextInt()){
            a[i++] = in.nextInt();
        }
        
        System.out.println(InversionCount.sort(a));
    }







} // end of class
