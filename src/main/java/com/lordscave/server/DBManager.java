package com.lordscave.server;

import com.lordscave.shared.Alert;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBManager {
    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASS;

    static {
        try (InputStream input = DBManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties props = new Properties();
            props.load(input);
            DB_URL = props.getProperty("db.url");
            DB_USER = props.getProperty("db.user");
            DB_PASS = props.getProperty("db.password");
        }catch (IOException e){
            System.out.println(Alert.getServerConfigFileException()  + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public static boolean registerUser(String userId, String userName, String passwordHash){
        String sql = "INSERT INTO users (user_id, username, password_hash) VALUES (?, ?, ?)";

        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,userId);
            stmt.setString(2,userName);
            stmt.setString(3,passwordHash);
            stmt.executeUpdate();
            return true;
        }catch (SQLException e){
            return false;
        }

    }

    public static boolean loginUser(String userId, String passwordHash){
        String sql = "SELECT * FROM users WHERE user_id = ? AND password_hash = ?";

        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,userId);
            stmt.setString(2,passwordHash);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }catch (SQLException e){
            return false;
        }
    }

    public static String getUserId(String userName){
        String sql = "SELECt * FROM users WHERE username = ?";

        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,userName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return rs.getString("user_id");
            }

        }catch (SQLException ignored){}
        return null;
    }

    public static String getUsername(String userId) {
        String sql = "SELECT username FROM users WHERE user_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getString("username");
        } catch (SQLException ignored) {}
        return null;
    }



    public static void saveMessage(String senderId, String receiverId, String content){
        String sql = "INSERT INTO messages (sender_id, receiver_id, content) VALUES (?, ?, ?)";

        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,senderId);
            stmt.setString(2,receiverId);
            stmt.setString(3,content);
            stmt.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(Alert.getServerMessageNotSaved() + e.getMessage());
        }
    }

    public static List<String> getInbox(String userId) {
        List<String> inbox = new ArrayList<>();
        String sql = "SELECT sender_id, content, timestamp FROM messages WHERE receiver_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                inbox.add("From " + rs.getString("sender_id") + " at " + rs.getTimestamp("timestamp") +
                        ": " + rs.getString("content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inbox;
    }

    public static void updateOnlineStatus(String userId, boolean isOnline) {
        String sql = "UPDATE users SET is_online = ? WHERE user_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, isOnline);
            stmt.setString(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateLastSeen(String userId) {
        String sql = "UPDATE users SET last_seen = CURRENT_TIMESTAMP WHERE user_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void markMessagesAsDelivered(String receiverId) {
        String sql = "UPDATE messages SET delivered = true WHERE receiver_id = ? AND delivered = false";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, receiverId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
