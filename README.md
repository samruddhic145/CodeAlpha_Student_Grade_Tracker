# CodeAlpha Student Grade Tracker

A console-based Java application that stores student records and marks, calculates averages and grades, and produces student/class reports.

## Features

- Add students and assign automatic IDs
- Add multiple marks per student
- Validate names, menu choices, numeric input, and marks (0–100)
- View an individual student report
- Display all students in a summary table
- Search by student ID
- Calculate class summary, highest and lowest student averages
- Assign a grade and pass/fail result
- Uses OOP, `ArrayList`, loops, methods, encapsulation, and exception handling

## Requirements

- Java JDK 8 or later
- Terminal / Command Prompt, or an IDE such as Eclipse or IntelliJ IDEA

## Run from a terminal

From the project folder:

```bash
javac -d out src/Student.java src/StudentGradeTracker.java
java -cp out StudentGradeTracker
```

On Windows, the same commands work in Command Prompt or PowerShell if Java is on PATH.

## Grade scale used

| Average | Grade |
|---:|:---|
| 90–100 | A+ |
| 80–89.99 | A |
| 70–79.99 | B |
| 60–69.99 | C |
| 50–59.99 | D |
| 35–49.99 | E |
| Below 35 | F |

The pass mark is 35. This is an implementation choice for this project, not a grading scale supplied by CodeAlpha. Adjust it if your mentor gives a different scale.

## Test checklist

1. Add a student with a valid name.
2. Try an empty name; it should be rejected.
3. Add marks 80, 90, and 70; expected average 80.00, highest 90.00, lowest 70.00, grade A, result PASS.
4. Try marks -1 and 101; both should be rejected.
5. Search for an existing and a nonexistent ID.
6. Display reports before entering marks.
7. Try letters at the menu and marks prompts; the program should ask again.
8. Run the class summary when there are no students and when students have no marks.

## Project structure

```text
CodeAlpha_Student_Grade_Tracker/
├── src/
│   ├── Student.java
│   └── StudentGradeTracker.java
└── README.md
```

## GitHub submission

Create a public repository named `CodeAlpha_Student_Grade_Tracker`, then run:

```bash
git init
git add .
git commit -m "Add Student Grade Tracker Java project"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/CodeAlpha_Student_Grade_Tracker.git
git push -u origin main
```

Replace `YOUR-USERNAME` with your GitHub username. Do not upload compiled output or private credentials.

## Demo video outline

1. Introduce the project and its purpose.
2. Show the two Java classes and explain their responsibilities.
3. Run the application and add a student.
4. Add marks and show the individual report.
5. Show the all-student table and class summary.
6. Demonstrate invalid mark/input validation.
7. Share the GitHub repository link in the LinkedIn post.
