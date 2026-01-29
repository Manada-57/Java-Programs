import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Student extends JFrame {
    private static  String URL = "jdbc:mysql://localhost:3306/java";
    
    private static  String USER = "root";
    private static  String PASS = "manada5707#";
    private JTextField tfSno, tfName, tfRollNo, tfAge;
    private JButton btnInsert, btnDelete, btnUpdate, btnShowAll;
    private JTextArea taOutput;
    public Student() {
        setTitle("Student Management");
        setLayout(new FlowLayout());
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new JLabel("Sno:"));
        tfSno = new JTextField(10);
        add(tfSno);
        add(new JLabel("Name:"));
        tfName = new JTextField(20);
        add(tfName);
        add(new JLabel("Roll No:"));
        tfRollNo = new JTextField(20);
        add(tfRollNo);
        add(new JLabel("Age:"));
        tfAge = new JTextField(5);
        add(tfAge);
        btnInsert = new JButton("Insert");
        btnDelete = new JButton("Delete");
        btnUpdate = new JButton("Update");
        btnShowAll = new JButton("Show All");
        add(btnInsert);
        add(btnDelete);
        add(btnUpdate);
        add(btnShowAll);
        taOutput = new JTextArea(10, 30);
        taOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taOutput);
        add(scrollPane);
        btnInsert.addActionListener(e->insertStudent());
        btnDelete.addActionListener(e->deleteStudent());
        btnUpdate.addActionListener(e->updateStudent());
        btnShowAll.addActionListener(e->showAllStudents());
    }
    private void insertStudent() {
        String sno = tfSno.getText();
        String name = tfName.getText();
        String rollNo = tfRollNo.getText();
        String ageStr = tfAge.getText();
        if (name.isEmpty() || rollNo.isEmpty() || ageStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields");
            return;
        }
        try {
            int age = Integer.parseInt(ageStr);
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            String query = "INSERT INTO student (sno,name, rollno, age) VALUES (?,?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1,sno);
            pst.setString(2, name);
            pst.setString(3, rollNo);
            pst.setInt(4, age);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student inserted successfully!");
            conn.close();
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    private void deleteStudent() {
        String snoStr = tfSno.getText();
        if (snoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the sno to delete");
            return;
        }
        try {
            String rollno = tfRollNo.getText();
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            String query = "DELETE FROM student WHERE rollno = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, rollno);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Student not found");
            }
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    private void updateStudent() {
        String snoStr = tfSno.getText();
        String name = tfName.getText();
        String rollNo = tfRollNo.getText();
        String ageStr = tfAge.getText();
        if (snoStr.isEmpty() || name.isEmpty() || rollNo.isEmpty() || ageStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields");
            return;
        }
        try {
            int sno = Integer.parseInt(snoStr);
            int age = Integer.parseInt(ageStr);
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            String query = "UPDATE student SET name = ?, rollno = ?, age = ? WHERE sno = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, rollNo);
            pst.setInt(3, age);
            pst.setInt(4, sno);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Student not found");
            }
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    private void showAllStudents() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            String query = "SELECT * FROM student";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            taOutput.setText("");
            while (rs.next()) {
                taOutput.append("Sno: " + rs.getInt("sno") + ", ");
                taOutput.append("Name: " + rs.getString("name") + ", ");
                taOutput.append("Roll No: " + rs.getString("rollno") + ", ");
                taOutput.append("Age: " + rs.getInt("age") + "\n");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    public static void main(String[] args){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            new Student().setVisible(true);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}