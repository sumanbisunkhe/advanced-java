package unit4jdbc;

import java.sql.*;
import java.util.Scanner;

public class ContactDirectory {

    static final String URL = "jdbc:mysql://localhost:3306/contact_db";
    static final String USER = "suman";
    static final String PASSWORD = "#password123!";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n--- Contact Directory ---");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> addContact();
                case 2 -> viewContacts();
                case 3 -> updateContact();
                case 4 -> deleteContact();
                case 5 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // ---------------- ADD ----------------
    static void addContact() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Mobile: ");
        String mobile = sc.nextLine();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // Check duplicate
            String checkSql = "SELECT * FROM contacts WHERE email=? OR mobile=?";
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setString(1, email);
            checkStmt.setString(2, mobile);

            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("Error: Email or Mobile already exists!");
                return;
            }

            String sql = "INSERT INTO contacts(name, email, mobile) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, mobile);

            pst.executeUpdate();
            System.out.println("Contact added successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---------------- READ ----------------
    static void viewContacts() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM contacts");

            System.out.println("\nID | Name | Email | Mobile");
            System.out.println("--------------------------------------");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("email") + " | " +
                                rs.getString("mobile")
                );
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---------------- UPDATE ----------------
    static void updateContact() {
        System.out.print("Enter Contact ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Email: ");
        String email = sc.nextLine();

        System.out.print("Enter New Mobile: ");
        String mobile = sc.nextLine();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // Check duplicate except current ID
            String checkSql = "SELECT * FROM contacts WHERE (email=? OR mobile=?) AND id!=?";
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setString(1, email);
            checkStmt.setString(2, mobile);
            checkStmt.setInt(3, id);

            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("Error: Email or Mobile already exists!");
                return;
            }

            String sql = "UPDATE contacts SET name=?, email=?, mobile=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, mobile);
            pst.setInt(4, id);

            int rows = pst.executeUpdate();

            if (rows > 0)
                System.out.println("Contact updated successfully!");
            else
                System.out.println("Contact not found!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---------------- DELETE ----------------
    static void deleteContact() {
        System.out.print("Enter Contact ID to delete: ");
        int id = sc.nextInt();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String sql = "DELETE FROM contacts WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            int rows = pst.executeUpdate();

            if (rows > 0)
                System.out.println("Contact deleted successfully!");
            else
                System.out.println("Contact not found!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}