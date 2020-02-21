import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * LibraryDataBase
 */
class Sortbybtm implements Comparator<Library> { 
    // Used for sorting in ascending order of 
    @Override
    public int compare(Library o1, Library o2) {
        if(o1.getTime() > o2.getTime() || o1.getTime() == o2.getTime() && o1.bookCount > o2.bookCount && o1.getBpD() > o2.getBpD()){
            return 1;
        } else if (o1.bookCount == o2.bookCount && o1.getBpD() == o2.getBpD() || o1.getTime() == o2.getTime()) {
            return 0;
        } else return -1;
    }
} 
public class LibraryDataBase {
    ArrayList<Library> libraries = new ArrayList<Library>();
    public void initialize(String[] T, String[] M, ArrayList<String[]> books, long bookCount){
        // initialize libraries
        for(int i = 0; i < T.length; i++){
            libraries.add(new Library(String.valueOf(i), T[i], M[i], books.get(i), books.get(i).length));
        }
    }
    
    public ArrayList<Library> getLibraries(){
        return libraries;
    }

    public ArrayList<Library> getSortedLibraries(){
        Collections.sort(libraries, new Sortbybtm());
        return libraries;
    }
    public Library getLibrary(String id){
        return libraries.get(Integer.parseInt(id));
    }
}