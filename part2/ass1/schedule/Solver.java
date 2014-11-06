import java.util.*;
import java.io.*;

public class Solver{

    private static final int SIZE = 10000;
    private static final String FILENAME = "jobs.txt";

    private Job[] jobs;
    private long objFun;

    public Solver(){

        jobs = new Job[SIZE];
        objFun = 0;
        
        try (Scanner in = new Scanner(new File(FILENAME))){

            int n = 0;
            while(in.hasNextDouble()){

                jobs[n] = new Job(n, in.nextDouble(), in.nextDouble());
                n++;
            }
        }catch (IOException e) {}
    }

    public void buildCompTime(){

        Arrays.sort(jobs);
        
        int i = 0;
        double sum = 0.0;
        for(Job job: jobs){
            sum +=  job.l;
            job.c += sum;
        }

        for(i=0; i<jobs.length; i++){
            objFun += jobs[i].w * jobs[i].c;
        }
    }

    public void buildCompTime2(){

        Arrays.sort(jobs, new Job.ByRatio());
        
        int i = 0;
        double sum = 0.0;
        for(Job job: jobs){
            sum +=  job.l;
            job.c += sum;
        }

        for(i=0; i<jobs.length; i++){
            objFun += jobs[i].w * jobs[i].c;
        }
    }


    public static void main (String[] args){

        Solver solver = new Solver();        
        solver.buildCompTime();
        System.out.println(solver.objFun);


        Solver solver2 = new Solver();
        solver2.buildCompTime2();
        /*for(int i=0; i < solver2.jobs.length; i++)
            System.out.println(solver2.jobs[i]);*/

        System.out.println(solver2.objFun);
    }

 














    private static class Job 
        implements Comparable<Job>{

        private int n;
        
        private double w;
        private double l;
        private double c;

        private double diff;
        private double ratio;

        public Job(int number, double weight, double length){
            n = number;
            w = weight;
            l = length;
            c = 0.0;
            diff = w - l;
            ratio = w/l;
        }

        public int compareTo(Job other){
            
            if (this.diff - other.diff !=0)
                return (int) -(this.diff - other.diff);
            else return (this.w < other.w) ? 1 : -1;
        }

        public String toString(){
            return "job #" + n + " w=" + w + " l=" + l +
                " diff=" + diff + " ratio=" + ratio + " c=" + c; 
        }

        public static class ByRatio implements Comparator<Job> {
            public int compare(Job v, Job w) {

                if (w.ratio - v.ratio < 0)
                    return -1;
                else if (w.ratio - v.ratio == 0)
                    return 0;
                else return 1;
               
           }
    	}
            


    }

} // end of class
