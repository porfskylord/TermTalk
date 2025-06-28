package com.lordscave.client;

import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);

    public void showBanner() {
        System.out.printf("""
                ▄▄▄█████▓▓█████  ██▀███   ███▄ ▄███▓▄▄▄█████▓ ▄▄▄       ██▓     ██ ▄█▀  
                ▓  ██▒ ▓▒▓█   ▀ ▓██ ▒ ██▒▓██▒▀█▀ ██▒▓  ██▒ ▓▒▒████▄    ▓██▒     ██▄█▒   
                ▒ ▓██░ ▒░▒███   ▓██ ░▄█ ▒▓██    ▓██░▒ ▓██░ ▒░▒██  ▀█▄  ▒██░    ▓███▄░   
                ░ ▓██▓ ░ ▒▓█  ▄ ▒██▀▀█▄  ▒██    ▒██ ░ ▓██▓ ░ ░██▄▄▄▄██ ▒██░    ▓██ █▄   
                  ▒██▒ ░ ░▒████▒░██▓ ▒██▒▒██▒   ░██▒  ▒██▒ ░  ▓█   ▓██▒░██████▒▒██▒ █▄  
                  ▒ ░░   ░░ ▒░ ░░ ▒▓ ░▒▓░░ ▒░   ░  ░  ▒ ░░    ▒▒   ▓▒█░░ ▒░▓  ░▒ ▒▒ ▓▒  
                    ░     ░ ░  ░  ░▒ ░ ▒░░  ░      ░    ░      ▒   ▒▒ ░░ ░ ▒  ░░ ░▒ ▒░  
                  ░         ░     ░░   ░ ░      ░     ░        ░   ▒     ░ ░   ░ ░░ ░   
                            ░  ░   ░            ░                  ░  ░    ░  ░░  ░     
                -<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>--<>-
                """);
    }

    public void showWelcome() {
        System.out.println("""
                
                🎙️ Welcome to TermTalk
                ========================
                [1] Login
                [2] Register
                [3] Exit
                """);
    }

    public void showMainMenu() {
        System.out.println("""
                
                🔐 Logged In - Choose Action
                =============================
                [1] View Inbox
                [2] Send Message
                [3] Logout
                """);
    }

    public int getMenuChoice(int max) {
        System.out.print("Choose: ");
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= max) return choice;
            } catch (NumberFormatException ignored) {}
            System.out.print("Invalid. Enter 1-" + max + ": ");
        }
    }

    public String prompt(String label) {
        System.out.print(label + ": ");
        return scanner.nextLine().trim();
    }

    public String[] promptLogin() {
        System.out.println("\n🔐 Login to your TermTalk account");
        String userId = prompt("User ID");
        String password = prompt("Password");
        return new String[]{userId, password};
    }

    public String[] promptRegister() {
        System.out.println("\n📝 Create a new TermTalk account");
        String username = prompt("Username");
        String password = prompt("Password");
        return new String[]{username, password};
    }

    public String[] promptSendMessage() {
        System.out.println("\n✉️ Send a Message");
        String receiverId = prompt("Receiver ID");
        String message = prompt("Message");
        return new String[]{receiverId, message};
    }
}
