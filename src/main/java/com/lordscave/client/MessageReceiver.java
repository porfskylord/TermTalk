package com.lordscave.client;

import java.io.BufferedReader;
import java.io.IOException;


public class MessageReceiver implements Runnable {

    private final BufferedReader reader;

    public MessageReceiver(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        try {
            String serverMessage;
            while ((serverMessage = reader.readLine()) != null) {
                System.out.println("\n📥 " + serverMessage);
                System.out.print(">> "); // Prompt hint
            }
        } catch (IOException e) {
            System.out.println("\n❌ Connection to server lost.");
        }
    }
}
