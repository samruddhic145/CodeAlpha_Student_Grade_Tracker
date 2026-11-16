import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Console-based Student Grade Tracker for the CodeAlpha Java task.
 * Compatible with Java 8 and later.
 */
public class StudentGradeTracker {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Student> STUDENTS = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("====================================");
        System.out.println("       STUDENT GRADE TRACKER");
        System.out.println("====================================");

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addMarksToStudent();
                    break;
                case 3:
                    viewStudentReport();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    displayClassSummary();
                    break;
                case 6:
                    searchStudent();
                    break;
                case 7:
                    running = false;
                    System.out.println("Thank you for using Student Grade Tracker.");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1 to 7.");
            }
        }
        SCANNER.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Add student");
        System.out.println("2. Add marks to a student");
        System.out.println("3. View one student's report");
        System.out.println("4. Display all students");
        System.out.println("5. Display class summary");
        System.out.println("6. Search student by ID");
        System.out.println("7. Exit");
    }

    private static void addStudent() {
        String name;
        while (true) {
            name = readLine("Enter student name: ");
            if (!name.trim().isEmpty()) {
                break;
            }
            System.out.println("Name cannot be empty. Try again.");
        }

        Student student = new Student(nextId++, name);
        STUDENTS.add(student);
        System.out.println("Student added successfully. Student ID: " + student.getId());
    }

    private static void addMarksToStudent() {
        Student student = findStudentById(readInt("Enter student ID: "));
        if (student == null) {
            System.out.println("Student not found. Add the student first or check the ID.");
            return;
        }

        int count;
        do {
            count = readInt("How many marks do you want to add? ");
            if (count <= 0) {
                System.out.println("Enter a number greater than zero.");
            }
        } while (count <= 0);

        for (int i = 1; i <= count; i++) {
            while (true) {
                double mark = readDouble("Enter mark " + i + " (0-100): ");
                try {
                    student.addMark(mark);
                    break;
                } catch (IllegalArgumentException exception) {
                    System.out.println(exception.getMessage() + " Try again.");
                }
            }
        }
        System.out.println("Marks added successfully for " + student.getName() + ".");
    }

    private static void viewStudentReport() {
        Student student = findStudentById(readInt("Enter student ID: "));
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        printStudentReport(student);
    }

    private static void displayAllStudents() {
        if (STUDENTS.isEmpty()) {
            System.out.println("No student records yet.");
            return;
        }

        System.out.printf("%-6s %-24s %-10s %-10s %-10s %-8s %-8s%n",
                "ID", "Name", "Average", "Highest", "Lowest", "Grade", "Result");
        System.out.println("----------------------------------------------------------------------------");
        for (Student student : STUDENTS) {
            System.out.printf("%-6d %-24s %-10s %-10s %-10s %-8s %-8s%n",
                    student.getId(), shorten(student.getName(), 23),
                    format(student.getAverage()), format(student.getHighestMark()),
                    format(student.getLowestMark()), student.getGrade(), student.getResult());
        }
    }

    private static void displayClassSummary() {
        if (STUDENTS.isEmpty()) {
            System.out.println("No student records available to summarize.");
            return;
        }

        double totalAverages = 0.0;
        double highest = -1.0;
        double lowest = 101.0;
        Student topStudent = null;
        Student lowStudent = null;
        int studentsWithMarks = 0;

        for (Student student : STUDENTS) {
            if (student.getMarks().isEmpty()) {
                continue;
            }
            double average = student.getAverage();
            totalAverages += average;
            studentsWithMarks++;
            if (average > highest) {
                highest = average;
                topStudent = student;
            }
            if (average < lowest) {
                lowest = average;
                lowStudent = student;
            }
        }

        System.out.println();
        System.out.println("========== CLASS SUMMARY ==========");
        System.out.println("Total registered students: " + STUDENTS.size());
        System.out.println("Students with marks: " + studentsWithMarks);
        if (studentsWithMarks == 0) {
            System.out.println("No marks have been entered yet.");
        } else {
            System.out.println("Class average (of student averages): "
                    + format(totalAverages / studentsWithMarks));
            System.out.println("Highest student average: " + format(highest)
                    + " (" + topStudent.getName() + ")");
            System.out.println("Lowest student average: " + format(lowest)
                    + " (" + lowStudent.getName() + ")");
        }
        System.out.println("====================================");
    }

    private static void searchStudent() {
        Student student = findStudentById(readInt("Enter student ID to search: "));
        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Student found:");
            printStudentReport(student);
        }
    }

    private static void printStudentReport(Student student) {
        System.out.println();
        System.out.println("========== STUDENT REPORT ==========");
        System.out.println("Student ID : " + student.getId());
        System.out.println("Name       : " + student.getName());
        if (student.getMarks().isEmpty()) {
            System.out.println("Marks      : No marks entered yet.");
        } else {
            System.out.println("Marks      : " + student.getMarks());
            System.out.println("Average    : " + format(student.getAverage()));
            System.out.println("Highest    : " + format(student.getHighestMark()));
            System.out.println("Lowest     : " + format(student.getLowestMark()));
            System.out.println("Grade      : " + student.getGrade());
            System.out.println("Result     : " + student.getResult());
        }
        System.out.println("====================================");
    }

    private static Student findStudentById(int id) {
        for (Student student : STUDENTS) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = SCANNER.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = SCANNER.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid numeric mark.");
            }
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine();
    }

    private static String format(double value) {
        return String.format("%.2f", value);
    }

    private static String shorten(String value, int maxLength) {
        if (value.length() <= maxLength) {
            return value;
        }
        return value.substring(0, maxLength - 3) + "...";
    }
}
