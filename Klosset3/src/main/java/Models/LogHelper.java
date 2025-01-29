package models;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LogHelper {
    public static void logAction(int userId, String action, int assetId) {
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "INSERT INTO activity_logs (user_id, action, asset_id, timestamp) VALUES (?, ?, ?, NOW())";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setString(2, action);
            statement.setInt(3, assetId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
