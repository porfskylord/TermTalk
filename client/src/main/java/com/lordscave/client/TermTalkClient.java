package com.lordscave.client;

import java.io.*;
import java.net.Socket;

public class TermTalkClient {

    private static final String SERVER_IP = "localhost"; // or public IP
    private static final int SERVER_PORT = 5656;

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private ConsoleUI ui;

    public static void main(String[] args) {
        new TermTalkClient().start();
    }

    public void start() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ui = new ConsoleUI();

            ui.showBanner();
            System.out.println("✅ Connected to TermTalk Server at " + SERVER_IP + ":" + SERVER_PORT);

            new Thread(new MessageReceiver(reader)).start();

            showWelcomeMenu();

        } catch (IOException e) {
            System.err.println("❌ Could not connect to server: " + e.getMessage());
        }
    }

    private void showWelcomeMenu() throws IOException {
        while (true) {
            ui.showWelcome();
            int choice = ui.getMenuChoice(3);

            switch (choice) {
                case 1 -> {
                    String[] login = ui.promptLogin();
                    send("LOGIN|" + login[0] + "|" + login[1]);
                    showUserMenu();
                }
                case 2 -> {
                    String[] reg = ui.promptRegister();
                    send("REGISTER|" + reg[0] + "|" + reg[1]);
                }
                case 3 -> {
                    exitClient();
                    return;
                }
            }
        }
    }

    private void showUserMenu() throws IOException {
        while (true) {
            ui.showMainMenu();
            int option = ui.getMenuChoice(3);

            switch (option) {
                case 1 -> send("INBOX");
                case 2 -> {
                    String[] msg = ui.promptSendMessage();
                    send("SEND|" + msg[0] + "|" + msg[1]);
                }
                case 3 -> {
                    send("LOGOUT");
                    return;
                }
            }
        }
    }

    private void send(String message) throws IOException {
        writer.write(message);
        writer.newLine();
        writer.flush();
    }

    private void exitClient() throws IOException {
        try {
            send("LOGOUT");
        } catch (Exception ignored) {}
        socket.close();
        System.out.println("👋 You have exited TermTalk. Goodbye!");
    }
}
