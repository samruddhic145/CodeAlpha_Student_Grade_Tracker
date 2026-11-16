import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents one student and the marks they have received.
 */
public class Student {
    private final int id;
    private final String name;
    private final List<Double> marks;

    public Student(int id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException("Student ID must be positive.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }
        this.id = id;
        this.name = name.trim();
        this.marks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Double> getMarks() {
        return Collections.unmodifiableList(marks);
    }

    public void addMark(double mark) {
        if (Double.isNaN(mark) || Double.isInfinite(mark) || mark < 0 || mark > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
        marks.add(mark);
    }

    public double getAverage() {
        if (marks.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (double mark : marks) {
            total += mark;
        }
        return total / marks.size();
    }

    public double getHighestMark() {
        if (marks.isEmpty()) {
            return 0.0;
        }
        double highest = marks.get(0);
        for (double mark : marks) {
            if (mark > highest) {
                highest = mark;
            }
        }
        return highest;
    }

    public double getLowestMark() {
        if (marks.isEmpty()) {
            return 0.0;
        }
        double lowest = marks.get(0);
        for (double mark : marks) {
            if (mark < lowest) {
                lowest = mark;
            }
        }
        return lowest;
    }

    public String getGrade() {
        double average = getAverage();
        if (average >= 90) return "A+";
        if (average >= 80) return "A";
        if (average >= 70) return "B";
        if (average >= 60) return "C";
        if (average >= 50) return "D";
        if (average >= 35) return "E";
        return "F";
    }

    public String getResult() {
        return getAverage() >= 35 ? "PASS" : "FAIL";
    }
}
