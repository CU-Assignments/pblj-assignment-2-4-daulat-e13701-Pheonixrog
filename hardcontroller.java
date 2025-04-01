import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private final Connection conn;

    public StudentController(String url, String user, String password) throws SQLException {
        this.conn = DriverManager.getConnection(url, user, password);
    }

    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO Student (Name, Department, Marks) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getDepartment());
            stmt.setFloat(3, student.getMarks());
            stmt.executeUpdate();
        }
    }

    public List<Student> getStudents() throws SQLException {
        String sql = "SELECT * FROM Student";
        List<Student> students = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(rs.getInt("StudentID"), rs.getString("Name"),
                        rs.getString("Department"), rs.getFloat("Marks")));
            }
        }
        return students;
    }
}
