import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        System.out.println("Server startup...");
        int port = 8090;

        try {
            while (true) {
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                out.println("Write your name");

                System.out.printf("New connection accepted Port: %d\n", clientSocket.getPort());
                final String name = in.readLine();

                out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));

                out.println("Are you child ? (yes/no)");

                final String answer = in.readLine();

                if (answer.equals("yes")) {
                    out.println("Welcome to the kids area, " + name + "! Let's play!");
                } else if (answer.equals("no")) {
                    out.println("Welcome to the adult zone, " + name + "! Have a good rest, or a good working day!");
                }

                serverSocket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
