import java.util.Comparator;

public class StudentsGPAComparator implements Comparator<Student> {

    public int compare(Student first, Student second) {
        return Double.compare(first.getGpa(), second.getGpa());
    }
}
