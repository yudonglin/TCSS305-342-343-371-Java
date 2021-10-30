class Student implements Comparable<Student>, Cloneable {

    //static int student_num = 0;
    private final String name;
    private final int sId;
    private final double gpa;

    Student(String name, int sId, double gpa) {
        this.name = name;
        //this.sId = student_num++;
        this.sId = sId;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public int getsId() {
        return sId;
    }

    public double getGpa() {
        return gpa;
    }

    public boolean equals(Student o) {
        return compareTo(o) == 0 && this.sId == o.getsId();
    }

    @Override
    public int compareTo(Student o) {
        int result = Double.compare(this.gpa, o.getGpa());
        if (result == 0) {
            return this.name.compareTo(o.getName());
        } else {
            return result;
        }
    }

    @Override
    public String toString() {
        return ""
                .concat("Name: ").concat(this.name).concat(", ")
                .concat("Student ID: ").concat(String.valueOf(this.sId)).concat(", ")
                .concat("GPA: ").concat(String.valueOf(this.gpa));
    }

    @Override
    public Student clone() {
        return new Student(this.name, this.sId, this.gpa);
    }
}
