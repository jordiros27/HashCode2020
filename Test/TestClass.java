import java.io.*;
import java.util.*; 

public class TestClass
{
    //Se debe modificar la ruta de acceso del documento para que pueda acceder a él.
    public static void main(String[] args) throws Exception {
      TestClass.mainCaller("/Users/jordiros27/MEGAsync/HashCode2020/Inputs/a.txt","/Users/jordiros27/MEGAsync/HashCode2020/Outputs/a.txt");
      TestClass.mainCaller("/Users/jordiros27/MEGAsync/HashCode2020/Inputs/b.txt","/Users/jordiros27/MEGAsync/HashCode2020/Outputs/b.txt");
      TestClass.mainCaller("/Users/jordiros27/MEGAsync/HashCode2020/Inputs/c.txt","/Users/jordiros27/MEGAsync/HashCode2020/Outputs/c.txt");
      TestClass.mainCaller("/Users/jordiros27/MEGAsync/HashCode2020/Inputs/d.txt","/Users/jordiros27/MEGAsync/HashCode2020/Outputs/d.txt");
      TestClass.mainCaller("/Users/jordiros27/MEGAsync/HashCode2020/Inputs/e.txt","/Users/jordiros27/MEGAsync/HashCode2020/Outputs/e.txt");
      TestClass.mainCaller("/Users/jordiros27/MEGAsync/HashCode2020/Inputs/f.txt","/Users/jordiros27/MEGAsync/HashCode2020/Outputs/f.txt");
      
    }
    
    public static void mainCaller(String input,String output) throws Exception { 
            File file = new File(input); 
      
          BufferedReader br = new BufferedReader(new FileReader(file)); 
          
          // Line 1
          String st1 = br.readLine();
          String[] st11 = st1.split(" ");
          
          int n_books = Integer.parseInt(st11[0]);
          int n_libraries = Integer.parseInt(st11[1]);
          int n_days = Integer.parseInt(st11[2]);
          
          // Books
          
          String st2 = br.readLine();
          int[] scores = string_to_int_arr(st2);
          String[] st22 = st2.split(" ");
          List<Book> books = new LinkedList<Book>();
          
          for(int i = 0; i < n_books; i++){
              Book nb = new Book(i, Integer.parseInt(st22[i]));
              books.add(nb);
          }
          
          //Libraries
          List<Library> libraries = new LinkedList<Library>();
          for(int i = 0; i < n_libraries; i++){
              st1 = br.readLine();
              int[] bs1 = string_to_int_arr(st1);
              st2 = br.readLine();
              int[] bz = string_to_int_arr(st2);
              Library lb = new Library(i, bs1[1], bs1[2], bz, scores);
              libraries.add(lb);
          }      
          
          
          //Collections.sort(studentList, Comparator.comparing(s -> s.getBirthday()));
          
          List<Library> to_signup = new LinkedList<Library>(libraries);
          List<Library> produce_pile = new LinkedList<Library>();
          List<Library> already_produced = new LinkedList<Library>();
          List<Library> signup_pile = new LinkedList<Library>();
          Library signing_up = null;
          
          Collections.sort(libraries);
          
          for(int i = 0; i < n_days; i++){
              //signup
              if(signup_pile.size() == 0){
                  if(to_signup.size() != 0) {
                      signing_up = to_signup.remove(0);
                      signing_up.start_signup();
                  }
              }
              //produce
              List<Book> scanned_books = new LinkedList<Book>();
              //pass day
              if(signing_up != null){
                  signing_up.pass_day_signup();
                  if(signing_up.signup_ready()) { signing_up = null; }
              }
              
          }
          
          
          //Nuevo Documento
          FileWriter fichero = null;
          PrintWriter pw = null;
          try
          {
            fichero = new FileWriter(output);
            pw = new PrintWriter(fichero);
            List<Library> ll = queue(libraries, n_days);
            pw.println(ll.size());
            for(int i = 0; i < ll.size(); i++){
                pw.println(ll.get(i).id + " " + libraries.get(i).to_scan.size());
                for(int j = 0; j < ll.get(i).to_scan.size(); j++){
                    pw.print(ll.get(i).to_scan.get(j) +  " ");
                }
                pw.println();
            }
    
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
               try {
               // Nuevamente aprovechamos el finally para 
               // asegurarnos que se cierra el fichero.
               if (null != fichero)
                  fichero.close();
               } catch (Exception e2) {
                  e2.printStackTrace();
               }
            }
    }
    
    public static int[] string_to_int_arr(String ss){
        String[] sb = ss.split(" ");
        int[] res = new int[sb.length];
        for(int i = 0; i < sb.length; i++){
            res[i] = Integer.parseInt(sb[i]);
        }
        return res;
    } 
    
    public static List<Library> queue(List<Library> libraries, int days){
        List<Library> ordered = new LinkedList<Library>(libraries);
        List<Library> out = new LinkedList<Library>();
        Collections.sort(ordered);
        int day_count = 0;
        for(int i = 0; i < ordered.size() && days <= days ; i++){
            out.add(ordered.get(i));
            day_count += ordered.get(i).sign_up;
        }
        
        return out;
    }
}
