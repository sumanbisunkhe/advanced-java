package unit1corejava.qn2serializedeserialize;

import java.io.*;

public class StudentSerialization {
    public static void main(String[] args) {

        // Create student object
        Student s1 = new Student(101, "Suman", 88.5);

        // File name
        String fileName = "student.ser";

        // ---------------- SERIALIZATION ----------------
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(s1); // Write object to file

            oos.close();
            fos.close();

            System.out.println("Student object serialized successfully.");
        } catch (IOException e) {
            System.out.println("Serialization Error: " + e.getMessage());
        }

        // ---------------- DESERIALIZATION ----------------
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Student s2 = (Student) ois.readObject(); // Read object from file

            ois.close();
            fis.close();

            System.out.println("\nStudent object deserialized successfully.");
            System.out.println("Student Details:");
            s2.displayStudent();

        } catch (IOException e) {
            System.out.println("Deserialization Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Error: " + e.getMessage());
        }
    }
}