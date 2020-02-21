import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Library
 */
public class Library {
    private String id, T, M, bookIds[];
    public long bookCount;
    public boolean isSignedUp = false;
    Library(String id, String T, String M, String[] books, long bookCount){
        this.id = id;
        this.T = T;
        this.M = M;
        this.bookIds = books;
        this.bookCount = bookCount;
    }
    long getId(){
        return Long.parseLong(this.id);
    }
    long getTime(){
        return Long.parseLong(this.T);
    }
    long getBpD(){
        return Long.parseLong(this.M);
    }
    long[] getBooks(){
        return Stream.of(this.bookIds).mapToLong(Long::parseLong).toArray();
    }
    boolean containsBook(long bookId){
        for(int i = 0; i < this.bookIds.length; i++){
            if(this.bookIds[i].equals(String.valueOf(bookId))){
                return true;
            }
        }
        return false;
    }
}