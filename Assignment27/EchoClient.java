import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try {
            Socket socket = new Socket(host, port);
            System.out.println("Connected to Echo Server at " + host + ":" + port);

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(), true
            );

            BufferedReader keyboard = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            System.out.print("Enter a message to send: ");
            String messageToSend = keyboard.readLine();

            output.println(messageToSend);

            String echoedMessage = input.readLine();
            System.out.println("Echo from server: " + echoedMessage);

            socket.close();
            System.out.println("Client connection closed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}