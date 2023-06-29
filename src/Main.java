import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static ArrayList<Student> Students = new ArrayList<>();
    public static void main(String[] args) {

        int Opt = 0;
        String sort, options = "start";

        while (!options.equalsIgnoreCase("exit")) {
            if (options.equalsIgnoreCase("start")) {
                AdminDisplay();
                System.out.print("Enter : ");
                Opt = scan.nextInt();
            }

            switch (Opt) {
                case 1 -> {
                    options = "back";
                    if (!Students.isEmpty()) {
                        displayStudents(Students);
                        System.out.println("\nType 'sort' to sort students");
                    } else {
                        System.out.println("\nList is empty, type 'add' to add a new student\n");
                    }
                    System.out.println("Type 'start' to start menu");
                    System.out.println("Type 'add' to add another student");
                    System.out.print("Enter : ");
                    sort = scan.next();
                    if (sort.equalsIgnoreCase("sort")) {
                        if (!Students.isEmpty()) {
                            sortStudents(Students);
                        } else {
                            System.out.println("\nList is empty, type 'add' to add a new student\n");
                        }
                    } else if (sort.equalsIgnoreCase("start")) {
                        options = "start";
                        Opt = 0;
                    } else if (sort.equalsIgnoreCase("add")) {
                        options = "add";
                        Opt = 2;
                    }
                }
                case 2 -> {
                    System.out.println("\n===================================================");
                    System.out.println("\t\t\t\tAdd New Student");
                    System.out.println("===================================================");
                    addStudent();
                    System.out.println("\n===================================================");
                    System.out.println("\t\t\t\tStudent has been added!");
                    System.out.println("===================================================");
                    System.out.println("\nType 'start' to start menu");
                    System.out.println("Type 'add' to add another student");
                    System.out.print("Enter : ");
                    options = scan.next();

                }
                case 3 -> System.exit(0);
            }
            if (!options.equalsIgnoreCase("start") && !options.equalsIgnoreCase("add")) {
                System.out.println("\nType 'start' to start menu");
                System.out.println("Type 'add' to add another student");
                System.out.print("Enter : ");
                options = scan.next();
                if (options.equalsIgnoreCase("add")) {
                    Opt = 2;
                }
            }
        }
        
    }

    public static void AdminDisplay() {
        System.out.println("===================================================\n\n");
        System.out.println("\t\t\t[1] Display All Student \n\t\t\t[2] Add New Student \n\t\t\t[3] Exit");
        System.out.println("\n\n===================================================");
    }

    public static void sortStudents(ArrayList<Student> Students) {
        ArrayList<Student> sortedStudents = new ArrayList<>();

        for (Student student : Students) {
            if (student.getAverage() >= 85) {
                sortedStudents.add(student);
            }
        }

        for (Student student : Students) {
            if (student.getAverage() < 85) {
                sortedStudents.add(student);
            }
        }

        for (Student student : sortedStudents) {
            System.out.println("\nName : " + student.getName() + "\t\t\t" + "Student ID : " + student.getStudentID());
            System.out.println("\t" + "English : " + student.getEnglish() + ", Math : " + student.getMath() + ", Science : " + student.getScience() + ", Filipino : " + student.getFilipino());
            System.out.println("\tAverage : " + student.getAverage() + "\t" + student.getStudentPassed());
            System.out.println("----------------------------------------------------------------");
        }

    }

    public static void displayStudents(ArrayList<Student> students) {
        for (Student student : students) {
            System.out.println("\nName : " + student.getName() + "\t\t\t" + "Student ID : " + student.getStudentID());
            System.out.println("\t" + "English : " + student.getEnglish() + ", Math : " + student.getMath() + ", Science : " + student.getScience() + ", Filipino : " + student.getFilipino());
            System.out.println("\tAverage : " + student.getAverage() + "\t" + student.getStudentPassed());
            System.out.println("----------------------------------------------------------------");
        }
    }

    public static void addStudent() {
        Student newstudent = new Student();
        String Name, StudentID;
        int English, Math, Science, Filipino;
        boolean studentexist;
        scan.nextLine();
        do {
            System.out.print("Enter Student ID : ");
            StudentID = scan.nextLine();
            if (checkifstudentexist(StudentID, Students)) {
                studentexist = true;
                System.out.println("Error: Student ID already exists!");
            } else {
                studentexist = false;
            }
        } while (studentexist);
        newstudent.setStudentID(StudentID);

        do {
            System.out.print("Enter Student Name : ");
            Name = scan.nextLine();
            if (checkifstudentexist(Name, Students)) {
                studentexist = true;
                System.out.println("Error: Name already exists!");
            } else {
                studentexist = false;
            }
        } while (studentexist);
        newstudent.setName(Name);

        System.out.print("English : ");
        English = scan.nextInt();
        System.out.print("Math : ");
        Math = scan.nextInt();
        System.out.print("Science : ");
        Science = scan.nextInt();
        System.out.print("Filipino : ");
        Filipino = scan.nextInt();

        newstudent.setEnglish(English);
        newstudent.setMath(Math);
        newstudent.setScience(Science);
        newstudent.setFilipino(Filipino);

        newstudent.setAverage();

        Students.add(newstudent);
    }

    public static boolean checkifstudentexist(String studentinfo, ArrayList<Student> students) {
        boolean studentexist = false;

        for (Student student : students) {
            studentexist = (student.getStudentID().equalsIgnoreCase(studentinfo) || student.getName().equalsIgnoreCase(studentinfo));
            if (studentexist)
                break;
        }

        return studentexist;
    }
}

class Student {
    private String Name = "No Name", StudentID;
    private int English = 0, Math = 0, Science = 0, Filipino = 0, Average = 0;

    public Student() { }

    public void setAverage() {
        Average = (English + Math + Science + Filipino) / 4;

    }

    public int getAverage() {
        return Average;
    }

    public String getStudentPassed() {
        String pass;
        if (getAverage() >= 75) {
            pass = "Passed";
        } else {
            pass = "Failed";
        }

        return pass;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setEnglish(int English) {
        this.English = English;
    }

    public int getEnglish() {
        return English;
    }

    public void setMath(int Math) {
        this.Math = Math;
    }

    public int getMath() {
        return Math;
    }

    public void setScience(int Science) {
        this.Science = Science;
    }

    public int getScience() {
        return Science;
    }

    public void setFilipino(int Filipino) {
        this.Filipino = Filipino;
    }

    public int getFilipino() {
        return Filipino;
    }
}