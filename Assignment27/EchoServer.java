import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        int port = 5000;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Echo Server started on port " + port);
            System.out.println("Waiting for a client to connect...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());


            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(), true
            );

            String receivedMessage = input.readLine();
            System.out.println("Received from client: " + receivedMessage);

            output.println(receivedMessage);
            System.out.println("Echoed message back to client.");

            socket.close();
            serverSocket.close();
            System.out.println("Server connection closed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}