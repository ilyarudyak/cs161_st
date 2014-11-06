import java.util.*;
import java.io.*;

// test LinkedList and ArrayDeque implementations 
public class Medians{

    private java.util.Queue<Integer> heapLow;
    private java.util.Queue<Integer> heapHigh;

    private List<Integer> medians;
    private long mediansSum;
    
    private static final int CAPACITY = 10000;
    private static final String FILENAME = "data.txt";


    public static void main (String[] args) throws IOException{

        
        Medians m = new Medians();
        //m.printMedians();

        m.buildHeaps();




        //System.out.println(m.heapLow + " " + m.heapLow.size());
        //System.out.println();
        //System.out.println(m.heapHigh + " " + m.heapHigh.size());
        //System.out.println(m.medians) ;
        for (Integer n: m.medians)
            m.mediansSum += n;
        System.out.println(m.mediansSum);
    }

    public Medians(){
        
        heapLow = new PriorityQueue<Integer>(CAPACITY, 
                Collections.reverseOrder());

        heapHigh = new PriorityQueue<Integer>();

        medians = new ArrayList<Integer>();
        mediansSum = 0;


    }

    public void buildHeaps() throws IOException{
        
        Scanner in = new Scanner(new File(FILENAME));
        int tmp;

        heapHigh.add(in.nextInt());
        medians.add(heapHigh.element());
        //mediansSum += heapHigh.element();

        heapLow.add(in.nextInt());
        medians.add(heapLow.element());
        //mediansSum += heapHigh.element();

        while(in.hasNextInt()){
            tmp = in.nextInt();

            if (heapHigh.element() > tmp)
                heapLow.add(tmp);
            
            else
                heapHigh.add(tmp);

            balanceHeaps();
            addMedian();

            //System.out.print(heapLow + " ");
            //System.out.print(heapHigh);
            //System.out.println();
        }
        
    }

    private void balanceHeaps(){
        
        if (heapLow.size() + 1 < heapHigh.size())
            heapLow.add(heapHigh.remove());
        else if (heapLow.size() > heapHigh.size() + 1)
            heapHigh.add(heapLow.remove());
    }

    private void addMedian(){
        
        if (heapLow.size() >= heapHigh.size()){
            medians.add(heapLow.element());
            //mediansSum += heapLow.element();
        }
        else{
            medians.add(heapHigh.element());
            //mediansSum += heapHigh.element();
        }
    }


    private void printMedians() throws IOException{

        List<Integer> list = new ArrayList<>();
        Scanner in = new Scanner(new File(FILENAME));

        while(in.hasNextInt()){
            
            list.add(in.nextInt());
            Collections.sort(list);
            System.out.print(list + " ");

            int k = list.size();
            //System.out.print(k + " ");
            if (k == 1)
                System.out.print(list.get(0) + " ");
            else if (k % 2 == 0)
                System.out.print(list.get(k / 2 - 1) + " ");
            else if (k % 2 != 0)
                System.out.print(list.get(k / 2) + " ");

            System.out.println();
        }
        
    }

} // end of class


