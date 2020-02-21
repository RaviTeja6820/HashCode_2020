import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Tester
 */
public class Tester {
    long D;
    BookDataBase bdb = new BookDataBase();
    LibraryDataBase ldb = new LibraryDataBase();
    long scoring = 0;
    int A;
    LinkedHashMap<String, ArrayList<Book>> submissions = new LinkedHashMap<String, ArrayList<Book>>();
    String fileContent = "";
    StringBuilder filecontent = new StringBuilder();

    Tester(String D, String[] scores, ArrayList<String[]> books, String T[], String M[], long bookCount) {
        this.D = Long.parseLong(D);
        this.bdb.initialize(scores);
        this.ldb.initialize(T, M, books, bookCount);
    }   

    public void printfLibraries(){
        ArrayList<Library> l = ldb.getSortedLibraries();
        for(int i = 0; i < l.size(); i++){
            System.out.println(l.get(i).getId() + " " + l.get(i).getTime() + " " + l.get(i).getBpD() + " " + l.get(i).bookCount);
            for (long b : l.get(i).getBooks()) {
                // System.out.print(bdb.getBook(b).getId() + "-" + bdb.getBook(b).getScore() + " ");
            }
            System.out.println("\n");
        }
    }

    public void calculate(){
        ArrayList<Library> l = ldb.getSortedLibraries();
        int[] status = new int[l.size()];
        int[] bookstatus = new int[bdb.booksCount()];
        long scoring = 0, signup = 0;
        int lib = 0;
        for(int i = 0; i < (int) D; i++){
            if(status[lib] == 0 && i == signup){
                Library library = l.get(lib);
                submissions.put(String.valueOf(library.getId()), new ArrayList<Book>());
                long time = library.getTime();
                ArrayList<Book> sortedBooks = bdb.getBooks(library.getBooks());
                for (Book book : sortedBooks) {
                    if(bookstatus[(int) book.getId()] == 0){
                        submissions.get(String.valueOf(library.getId())).add(book);
                        scoring += book.getScore();
                        bookstatus[(int) book.getId()] = 1;
                    }
                    // System.out.println(book.getId() + " "  + book.getScore());
                }
                signup = i + time;
                status[lib] = 1;
                if(lib+1 < l.size()) lib++;
            }
        }
        A = lib + 1;
        System.out.println(scoring);
    }

    public void display(){
        System.out.println(A);
        filecontent.append(String.valueOf(A) + "\n");
        for (String key : submissions.keySet()) {
            System.out.println(key + " " + submissions.get(key).size());
            filecontent.append(key + " " + submissions.get(key).size() + "\n");
            for (Book book : submissions.get(key)) {
                System.out.print(book.getId() + " ");
                filecontent.append(book.getId() + " ");
            }
            System.out.print("\n");
            filecontent.append("\n");
        }
    }

    public void addToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("f_out.txt"));
        writer.write(String.valueOf(filecontent));
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        Tester t;
        try {
            // File myObj = new File("a_example.txt");
            // File myObj = new File("b_read_on.txt");
            // File myObj = new File("c_incunabula.txt");
            // File myObj = new File("d_tough_choices.txt");
            // File myObj = new File("e_so_many_books.txt");
            File myObj = new File("f_libraries_of_the_world.txt");
            BufferedReader myReader = new BufferedReader(new FileReader(myObj));
            String st; 
            int i = 1, libs = 0;
            String B = "0", L = "0", D = "0", 
                    scores[] = new String[0],
                    T[] = new String[0], 
                    M[] = new String[0],
                    N[] = new String[0];
            ArrayList<String[]> books = new ArrayList<String[]>();
            while ((st = myReader.readLine()) != null) {
                String[] split = st.split(" ");
                if(i == 1){
                    B = split[0];
                    L = split[1];
                    D = split[2];
                    N = new String[Integer.parseInt(L)];
                    T = new String[Integer.parseInt(L)];
                    M = new String[Integer.parseInt(L)];
                    } else if(i == 2) {
                    scores = split;
                } else {
                    if(i % 2 == 1){
                        if(libs == (Integer.parseInt(L))) break;
                        N[libs] = split[0];
                        T[libs] = split[1];
                        M[libs] = split[2];
                        // System.out.println(libs + " " + N[libs] + " " + T[libs] + " " + M[libs]);
                    } else {
                        books.add(split);
                        libs++;
                    }   
                }
                i++;
            } 
            t = new Tester(D, scores, books, T, M, Long.parseLong(B));
            t.calculate();
            t.display();
            t.addToFile();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}