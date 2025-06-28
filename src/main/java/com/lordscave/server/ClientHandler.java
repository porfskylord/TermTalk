package com.lordscave.server;

import com.lordscave.shared.Utils;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    private String userId;
    private String username;
    private boolean loggedIn = false;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            setupStreams();

            send("Welcome to TermTalk! Please REGISTER or LOGIN");

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                if (inputLine.trim().isEmpty()) continue;
                System.out.println("📥 [" + socket.getInetAddress() + "]: " + inputLine);
                handleCommand(inputLine.trim());
            }

        } catch (IOException e) {
            System.out.println("⚠️ Connection lost: " + socket);
        } finally {
            cleanup();
        }
    }

    private void setupStreams() throws IOException {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    private void handleCommand(String input) throws IOException {
        String[] parts = input.split("\\|");
        String command = parts[0].toUpperCase();

        switch (command) {
            case "REGISTER" -> handleRegister(parts);
            case "LOGIN" -> handleLogin(parts);
            case "SEND" -> handleSend(parts);
            case "INBOX" -> handleInbox();
            case "LOGOUT" -> handleLogout();
            default -> send("❓ Unknown command: " + command);
        }
    }

    private void handleRegister(String[] parts) throws IOException {
        if (parts.length < 3) {
            send("Usage: REGISTER|username|password");
            return;
        }

        String uname = parts[1];
        String pass = parts[2];
        String hashed = Utils.hashPassword(pass);
        String generatedUserId = uname.substring(0, 3).toLowerCase() + (int)(Math.random() * 9000 + 1000);

        if (DBManager.registerUser(generatedUserId, uname, hashed)) {
            send("✅ Registered! Your ID is: " + generatedUserId);
        } else {
            send("❌ Registration failed. Username may already exist.");
        }
    }

    private void handleLogin(String[] parts) throws IOException {
        if (parts.length < 3) {
            send("Usage: LOGIN|userId|password");
            return;
        }

        String userIdInput = parts[1];
        String passwordInput = parts[2];
        String passwordHash = Utils.hashPassword(passwordInput);

        boolean success = DBManager.loginUser(userIdInput, passwordHash);
        if (success) {
            this.userId = userIdInput;
            this.username = DBManager.getUsername(userId);
            this.loggedIn = true;

            DBManager.updateOnlineStatus(userId, true);
            send("✅ Login successful as: " + username + " (ID: " + userId + ")");
        } else {
            send("❌ Login failed.");
        }
    }


    private void handleSend(String[] parts) throws IOException {
        if (!loggedIn) {
            send("❌ Login required.");
            return;
        }

        if (parts.length < 3) {
            send("Usage: SEND|receiverId|message");
            return;
        }

        String receiver = parts[1];
        String message = parts[2];

        DBManager.saveMessage(userId, receiver, message);
        send("✅ Message sent to " + receiver);
    }

    private void handleInbox() throws IOException {
        if (!loggedIn) {
            send("❌ Login required.");
            return;
        }

        List<String> messages = DBManager.getInbox(userId);
        if (messages.isEmpty()) {
            send("📭 Inbox empty.");
        } else {
            send("📬 Inbox:");
            for (String msg : messages) {
                String[] parts = msg.split(":", 2); // split metadata from content
                String header = parts[0].trim();
                String content = (parts.length > 1) ? parts[1].trim() : "";
                String formatted =
                        "──────────────────────────────\n" +
                                "📨 " + header + "\n" +
                                "💬 " + content + "\n";
                send(formatted);
            }
            DBManager.markMessagesAsDelivered(userId);
        }
    }



    private void handleLogout() throws IOException {
        if (loggedIn) {
            send("👋 Logged out.");
            DBManager.updateOnlineStatus(userId, false);
            DBManager.updateLastSeen(userId);
            loggedIn = false;
        } else {
            send("You are not logged in.");
        }
    }

    private void send(String msg) throws IOException {
        writer.write(msg);
        writer.newLine();
        writer.flush();
    }

    private void cleanup() {
        if (loggedIn && userId != null) {
            DBManager.updateOnlineStatus(userId, false);
            DBManager.updateLastSeen(userId);
        }

        try {
            socket.close();
        } catch (IOException ignored) {}
    }
}
