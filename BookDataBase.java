import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * BookDataBase
 */
public class BookDataBase {
    HashMap<String, Book> books = new HashMap<String, Book>();
    int count;
    public void initialize(String[] scores){
        //loaddata into books;
        for(int i = 0; i < scores.length; i++){
            books.put(String.valueOf(i), new Book(String.valueOf(i), scores[i]));
        }
    }
    public int booksCount() {
        return books.size();
    }
    public HashMap<String, Book> getBooks(){
        return books;
    }
    public static Comparator<Book> score = new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            if(o1.getScore() < o2.getScore())
            return 1;
            else if(o1.getScore() > o2.getScore())
            return -1;
            else return 0;
        }
    };
    public ArrayList<Book> getBooks(long[] books){
        ArrayList<Book> list = new ArrayList<Book>();
        for(int i = 0; i < books.length; i++){
            list.add(getBook(books[i]));
        }
        Collections.sort(list, score);
        return list;
    }
    public Book getBook(long id){
        return books.get(String.valueOf(id));
    }
}