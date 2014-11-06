import java.util.*;
import java.io.*;


public class Test{

    public static void main (String[] args){

        Set<Long> set = readFileToHash("small.txt");
        //System.out.println(set);

        int count = 0;
        for (long t= -10000L; t<=10000L; t++){
            if (searchFor2SUM(t, set)){
                System.out.println(t);
                count++;
            }
        }
        System.out.println(count);
    }

    
    private static boolean searchFor2SUM(Long t, Set<Long> set){

        
        for (Long x: set){
            if (set.contains(t-x)){
                System.out.print(x/10000 + " " + (t-x)/10000 + " --> ");
                return true;
            }
        }
        return false;
    }
    
    
    private static Set<Long> readFileToHash(String filename){

        Set<Long> set = new HashSet<>();

        try(Scanner in = new Scanner(new File(filename))){
            while(in.hasNextLong()){
                set.add(in.nextLong());
            }
        }
        catch (IOException e){}
        return set;
        
    }






} // end of class
