import java.io.*;
import java.util.*;

public class StudentManagementSystem {
    private List<Student> students;
    private File file;

    public StudentManagementSystem(String filename) {
        students = new ArrayList<>();
        file = new File(filename);
        loadStudents();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudents();
    }

    public void removeStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                students.remove(student);
                saveStudents();
                return;
            }
        }
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void loadStudents() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                Student student = new Student(fields[0], Integer.parseInt(fields[1]), fields[2]);
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  public void saveStudents() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Student student : students) {
                bw.write(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}