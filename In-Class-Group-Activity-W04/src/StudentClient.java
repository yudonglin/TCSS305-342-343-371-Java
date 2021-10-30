import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class StudentClient {

    public static void sortIntArray() {
        /* 1) Define an array of 10 integers, and sort them. */
        int[] s1 = {10, 12, 32, 1, 131, 12123, 11, 22, 112, -21};

        // print out original array
        System.out.println("before:");
        printIntArray(s1);

        // sort array
        Arrays.sort(s1);

        // print out sorted array
        System.out.println("after:");
        printIntArray(s1);

        /* 2) Assign random values to the elements of this array; and sort them in ascending order. */

        for (int i = 0; i < s1.length; i++) {
            s1[i] = ThreadLocalRandom.current().nextInt();
        }

        // print out original array
        System.out.println("before:");
        printIntArray(s1);

        // sort array
        Arrays.sort(s1);

        // print out sorted array
        System.out.println("after:");
        printIntArray(s1);
    }

    public static void studentComparisonExp() {
        Student[] stuArray = new Student[4];
        stuArray[3] = new Student("Sam", 1, 4.0);
        stuArray[0] = new Student("Alice", 2, 3.0);
        stuArray[2] = new Student("Alice", 3, 3.5);
        stuArray[1] = new Student("Sam", 4, 3.5);

        System.out.println("Original student array:");
        printStudentArray(stuArray);
        Arrays.sort(stuArray, new Students());
        System.out.println("Student array after sorting based on both gpa (first-order) and name (second-order):");
        printStudentArray(stuArray);
        Arrays.sort(stuArray, new StudentsNameComparator());
        System.out.println("Student array after sorting based name:");
        printStudentArray(stuArray);
        Arrays.sort(stuArray, new StudentsGPAComparator());
        System.out.println("Student array after sorting based on gpa:");
        printStudentArray(stuArray);


    }

    public static void sortStudentArray() {
        /* Define an array of 10 student objects; assign random values to objects' fields; and sort them in descending order of gpa. */
        Student[] stuArray = new Student[5];

        final int NAME_LEN = 10;

        for (int i = 0; i < stuArray.length; i++) {
            StringBuilder random_name_generator = new StringBuilder(NAME_LEN);
            for (int j = 0; j < NAME_LEN; j++) {
                random_name_generator.append((char) ThreadLocalRandom.current().nextInt(97, 123));
            }
            stuArray[i] = new Student(
                    random_name_generator.toString(),
                    ThreadLocalRandom.current().nextInt(1000),
                    ThreadLocalRandom.current().nextDouble(0.00, 4.00)
            );
        }

        System.out.println("Original student array:");
        for (Student stu : stuArray) {
            System.out.println(stu);
        }

        Arrays.sort(stuArray, new Students());
        System.out.println("Student array after sorting based on both gpa (first-order) and name (second-order):");
        for (Student stu : stuArray) {
            System.out.println(stu);
        }

        Arrays.sort(stuArray, new StudentsNameComparator());
        System.out.println("Student array after sorting based name:");
        for (Student stu : stuArray) {
            System.out.println(stu);
        }

        Arrays.sort(stuArray, new StudentsGPAComparator());
        System.out.println("Student array after sorting based on gpa:");
        for (Student stu : stuArray) {
            System.out.println(stu);
        }
    }

    private static void printIntArray(int[] intArray) {
        System.out.print("{ ");
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(intArray[i]);
            if (i < intArray.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print(" }");
        System.out.println();
    }

    private static void printStudentArray(Student[] stuArray) {
        System.out.print("{ ");
        for (int i = 0; i < stuArray.length; i++) {
            System.out.print(stuArray[i]);
            if (i < stuArray.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print(" }");
        System.out.println();
    }

    public static void main(String[] args) {
        //sortIntArray();
        studentComparisonExp();

        System.out.println();
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println();

        sortStudentArray();
    }
}
