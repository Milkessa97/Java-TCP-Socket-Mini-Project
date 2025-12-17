import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name (Client side): ");
        String clientName = scanner.nextLine();

        try (Socket socket = new Socket("localhost", 5000)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(clientName);
            String serverName = in.readLine();
            
            System.out.println("System: Connected to " + serverName);

            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println(serverName + ": " + msg);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            }).start();

            while (true) {
                String myMsg = scanner.nextLine();
                out.println(myMsg);
            }

        } catch (IOException e) {
            System.err.println("Could not connect: " + e.getMessage());
        }
    }
}