import java.io.*;
import java.net.*;

public class Server2 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started...");
            System.out.println("Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(), true);

        
            BufferedReader keyboard = new BufferedReader(
                    new InputStreamReader(System.in));

            String clientMsg, serverMsg;

            while (true) {

                clientMsg = input.readLine();
                if (clientMsg == null || clientMsg.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                System.out.println("Client says: " + clientMsg);
                System.out.print("Server says: ");
                serverMsg = keyboard.readLine();
                output.println(serverMsg);

                if (serverMsg.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            socket.close();
            serverSocket.close();
            System.out.println("Server closed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}