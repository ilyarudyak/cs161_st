

public class Test{

    public static void main (String[] args){
        System.out.println(reverse("abcde"));
    }

    private static String reverse(String s){
        if (s.length() == 0)
            return "";
        else 
            return reverse(s.substring(1)) + s.substring(0,1);
    }






} // end of class
