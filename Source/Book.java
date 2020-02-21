/**
 * Book
 */
public class Book {
    private String id, score;
    Book(String id, String score){
        this.id = id;
        this.score = score;
    }
    long getId(){
        return Long.parseLong(id);
    }
    long getScore(){
        return Long.parseLong(score);
    }
}