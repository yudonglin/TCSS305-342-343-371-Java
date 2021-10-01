public class Main {

    public static void main(String[] args) {
	// write your code here
        new Demo().visualize();
    }
}

class Demo {

    public void visualize() {
        // Array of chars
        char[] charArray = new char[2];
        System.out.println(charArray);
        charArray[0] = 'C';
        charArray[1] = 83;
        System.out.println(charArray);

        char c = 'A';

        String s = "Hello";
        String s2 = new String("Hello on the heap");
        String[] stringArray = new String[2];
        System.out.println(stringArray);

        stringArray[0] = "CS" ;
        stringArray[1] = " 305";
        System.out.println(stringArray);

        Student[] studArray = new Student[2];
        studArray[0] = new Student("Jasper", 21_000_001, new Address(98401, "WA"));
        studArray[1] = new Student("Luke" , 21_000_002, new Address(98004, "WA"));

        Student[] studArray2 = studArray;

        studArray2 = studArray.clone();

    }

}

class Student implements Cloneable {
    String name;
    int sID;
    Address address;


    public Student(String name, int sID, Address address) {
        this.name = name;
        this.sID = sID;
        this.address = address;
    }

    public Student clone() {
        return new Student(this.name, this.sID, this.address);
    }

}

class Address implements Cloneable{
    int zipCode;
    String state;

    public Address(int zipCode, String state) {
        this.zipCode = zipCode;
        this.state = state;
    }

    public Address clone() {
        return new Address(this.zipCode, this.state);
    }
}
