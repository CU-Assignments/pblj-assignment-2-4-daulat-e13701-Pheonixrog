import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/StudentDB";
        String user = "root";
        String password = ""; // Change if needed

        try {
            StudentController controller = new StudentController(url, user, password);
            Scanner scanner = new Scanner(System.in);

            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            int choice = scanner.nextInt();

            if (choice == 1) {
                scanner.nextLine();
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Department: ");
                String department = scanner.nextLine();
                System.out.print("Enter Marks: ");
                float marks = scanner.nextFloat();

                controller.addStudent(new Student(0, name, department, marks));
                System.out.println("Student Added!");
            } else if (choice == 2) {
                List<Student> students = controller.getStudents();
                students.forEach(s -> System.out.println(s.getStudentID() + " " + s.getName()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
