import java.util.Comparator;

public class Students implements Comparator<Student> {

    public int compare(Student first, Student second) {
        return first.compareTo(second);
    }
}
