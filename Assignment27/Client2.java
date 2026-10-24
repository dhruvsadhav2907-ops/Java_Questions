import java.io.*;
import java.net.*;
public class Client2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server! (Type 'exit' to end)");
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(), true);
            BufferedReader keyboard = new BufferedReader(
                    new InputStreamReader(System.in));
            String clientMsg, serverMsg;
            while (true) {

                System.out.print("Client says: ");
                clientMsg = keyboard.readLine();
                output.println(clientMsg);
                if (clientMsg.equalsIgnoreCase("exit")) {
                    break;
                }

                serverMsg = input.readLine();
                if (serverMsg == null || serverMsg.equalsIgnoreCase("exit")) {
                    System.out.println("Server ended the chat.");
                    break;
                }
                System.out.println("Server says: " + serverMsg);
            }

            socket.close();
            System.out.println("Client disconnected.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}