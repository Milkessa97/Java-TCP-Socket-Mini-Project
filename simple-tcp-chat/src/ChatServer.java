import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatServer {
    public static void main(String[] args) {
        String serverName = "ServerAdmin";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name (Server side): ");
        serverName = scanner.nextLine();

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server started. Waiting for a single client to connect...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client detected! Establishing secure 1-to-1 link...");

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String clientName = in.readLine();
            out.println(serverName);
            
            System.out.println("System: Connected to " + clientName);

            Thread receiveThread = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println(clientName + ": " + msg);
                    }
                } catch (IOException e) {
                    System.out.println("Connection lost.");
                }
            });
            receiveThread.start();
            while (true) {
                String myMsg = scanner.nextLine();
                out.println(myMsg);
            }

        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}