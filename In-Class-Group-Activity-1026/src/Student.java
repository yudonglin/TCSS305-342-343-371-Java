import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class Student implements Comparable<Student> {
    //static int student_num = 0;
    String name;
    int sId;
    double gpa;

    public Student(String name, int sId, double gpa) {
        this.name = name;
        //this.sId = student_num++;
        this.sId = sId;
        this.gpa = gpa;
    }

    public static void main(String[] args) {

        /* 1) Define an array of 10 integers, and sort them. */
        int[] s1 = {10, 12, 32, 1, 131, 12123, 11, 22, 112, -21};

        // print out original array
        System.out.print("before: {");
        for (int num : s1) {
            System.out.print(num);
            System.out.print(", ");
        }
        System.out.print("}");
        System.out.println();

        // sort array
        Arrays.sort(s1);

        // print out sorted array
        System.out.print("after: {");
        for (int num : s1) {
            System.out.print(num);
            System.out.print(", ");
        }
        System.out.print("}");
        System.out.println();

        /* 2) Assign random values to the elements of this array; and sort them in ascending order. */
        Random rd = new Random();

        for (int i = 0; i < s1.length; i++) {
            s1[i] = rd.nextInt();
        }

        // print out original array
        System.out.print("before: {");
        for (int num : s1) {
            System.out.print(num);
            System.out.print(", ");
        }
        System.out.print("}");
        System.out.println();

        // sort array
        Arrays.sort(s1);

        // print out sorted array
        System.out.print("after: {");
        for (int num : s1) {
            System.out.print(num);
            System.out.print(", ");
        }
        System.out.print("}");
        System.out.println();

        /* Define an array of 10 student objects; assign random values to objects' fields; and sort them in descending order of gpa. */
        Student[] stu_array = new Student[10];

        final int NAME_LEN = 10;

        for (int i = 0; i < stu_array.length; i++) {
            StringBuilder random_name_generator = new StringBuilder(NAME_LEN);
            for (int j = 0; j < NAME_LEN; j++) {
                random_name_generator.append((char) ThreadLocalRandom.current().nextInt(97, 123));
            }
            stu_array[i] = new Student(random_name_generator.toString(), rd.nextInt(10000), rd.nextDouble());
        }

        System.out.println("Before:");

        for (Student stu : stu_array) {
            System.out.println(stu);
        }

        Arrays.sort(stu_array, Collections.reverseOrder());

        System.out.println("After:");

        for (Student stu : stu_array) {
            System.out.println(stu);
        }
    }

    @Override
    public int compareTo(Student o) {
        return Double.compare(this.gpa, o.gpa);
    }

    @Override
    public String toString() {
        return ""
                .concat("name: ").concat(this.name).concat("; ")
                .concat("sId: ").concat(String.valueOf(this.sId)).concat("; ")
                .concat("gap: ").concat(String.valueOf(this.gpa));
    }
}
