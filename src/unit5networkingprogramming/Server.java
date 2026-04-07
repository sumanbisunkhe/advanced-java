package unit5networkingprogramming;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);
            String clientMsg, serverMsg;

            while (true) {
                // Receive message from client
                clientMsg = input.readLine();
                System.out.println("Client: " + clientMsg);

                if (clientMsg.equalsIgnoreCase("bye")) {
                    break;
                }

                // Send reply to client
                System.out.print("You: ");
                serverMsg = sc.nextLine();
                output.println(serverMsg);

                if (serverMsg.equalsIgnoreCase("bye")) {
                    break;
                }
            }

            socket.close();
            serverSocket.close();
            System.out.println("Chat ended.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}