package com.lordscave.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TermTalkServer {

    private static final int PORT = 5656;
    private static final int MAX_CLIENTS = 100;

    public static void main(String[] args) {
        System.out.println("🟢 TermTalk Server starting on port " + PORT + "...");

        ExecutorService threadPool = Executors.newFixedThreadPool(MAX_CLIENTS);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("✅ Server is ready. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("🔌 Connected: " + clientSocket.getInetAddress());

                threadPool.submit(new ClientHandler(clientSocket));
            }

        } catch (IOException e) {
            System.err.println("❌ Server error: " + e.getMessage());
        } finally {
            threadPool.shutdown();
        }
    }
}
