import java.util.Comparator;

public class StudentsNameComparator implements Comparator<Student> {

    public int compare(Student first, Student second) {
        return first.getName().compareTo(second.getName());
    }
}
