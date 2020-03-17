import java.util.*;

public class Library implements Comparable
{
    // instance variables - replace the example below with your own
    public int id;
    public int sign_up;
    public int daily_books;
    public List<Integer> to_scan;
    public int[] scores;
    
    public int sign_up_count;

    public Library(int iid, int ssign, int db, int[] books, int[] sscores)
    {
        this.id = iid;
        this.sign_up = ssign;
        this.daily_books = db;
        this.to_scan = new LinkedList<Integer>();
        for(Integer i : books){
            to_scan.add(i);
        }
        this.scores = sscores;
    }
    
    public List<Integer> max_throughput(){
        return new LinkedList<Integer>();
    }
    
    public List<Integer> restricted_throughput(List<Integer> restricted) {
        return new LinkedList<Integer>(); 
    }
    
    public void start_signup(){
        this.sign_up_count = sign_up;
    }
    
    public boolean signup_ready(){
        return  sign_up_count <= 0;
    }
    
    public void pass_day_signup(){
        sign_up_count--;
    }
    
    @Override
    public int compareTo(Object lib) {  
        
        Library other = (Library)lib;
    
        int libreria0 = 0;
        int libreria1 = 0;
        
        int pLibros0 = 0;
        int pLibros1 = 0;
    
        int i;            
        
                
        libreria0 = this.sign_up;
        libreria1 = other.sign_up;
        
        if (libreria0 > libreria1) {return 1;}
        
        else if (libreria0 == libreria1) {return 0;}
        
        else {return -1;}
        
    }
}
