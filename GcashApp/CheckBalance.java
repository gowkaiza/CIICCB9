package GcashApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckBalance {

    // Method to check user balance using userId
    public static double checkBalance(int userId) {

        double balance = 0.0;

        String sql = "SELECT amount FROM balance WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                balance = rs.getDouble("amount");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return balance;
    }

    // Test the function
    public static void main(String[] args) {
        int userId = 1;
        double userBalance = checkBalance(userId);

        System.out.println("User ID: " + userId);
        System.out.println("Account Balance: ₱" + userBalance);
    }
}
