package unit5networkingprogramming;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server!");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);
            String clientMsg, serverMsg;

            while (true) {
                // Send message to server
                System.out.print("You: ");
                clientMsg = sc.nextLine();
                output.println(clientMsg);

                if (clientMsg.equalsIgnoreCase("bye")) {
                    break;
                }

                // Receive message from server
                serverMsg = input.readLine();
                System.out.println("Server: " + serverMsg);

                if (serverMsg.equalsIgnoreCase("bye")) {
                    break;
                }
            }

            socket.close();
            System.out.println("Chat ended.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}