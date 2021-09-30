package Controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Student;

public class StudentController extends ArrayList<Student> implements FileConnection<Student> {
  private final String FILENAME = "student.txt";
  private Scanner scanner = null;
  private FileWriter fileWriter = null;
  private BufferedWriter bufferedWriter = null;
  private PrintWriter printWriter = null;

  public StudentController() {
    getAll();
  }

  public Student get(String studentID) {
    for (Student student : this) if (student.getStudentID().equals(studentID)) return student;
    return null;
  }

  public void getAll() {
    boolean readMode = true;
    open(readMode);
    while (scanner.hasNextLine()) {
      String data = scanner.nextLine();
      Student student = create(data);
      this.add(student);
    }
    close();
  }

  @Override
  public void open(boolean readMode) {
    if (readMode) {
      try {
        scanner = new Scanner(Paths.get(FILENAME), "UTF-8");
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      try {
        fileWriter = new FileWriter(FILENAME, true);
        bufferedWriter = new BufferedWriter(fileWriter);
        printWriter = new PrintWriter(bufferedWriter);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public Student create(String data) {
    String split[] = data.split("\\;");
    return new Student(split[0], split[1]);
  }

  @Override
  public void close() {
    if (scanner != null) scanner.close();
    if (printWriter != null && bufferedWriter != null && fileWriter != null)
      try {
        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
  }

  public void printStudentList() {
    this.forEach(student -> {System.out.format("%15s|%25s\n", student.getStudentID(), student.getName());});
  }
}
