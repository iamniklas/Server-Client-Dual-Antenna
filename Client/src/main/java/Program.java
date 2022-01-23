import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    static Client client = null;

    static String username = "";

    static ArrayList<String> m = new ArrayList<String>();

    static int chatPort = 2400;
    static int stripPort = 3333;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Socket cSocket = null;
        try {
            System.out.print("Enter Username: ");
            username = scanner.nextLine();

            cSocket = new Socket("localhost", stripPort);

            client = new Client(cSocket);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        while (!cSocket.isClosed()) {
            String message = scanner.nextLine();
            //client.sender.send(username + ": " + message);

            client.sender.send(message);

            if(message.equals("quit"))
                try {
                    client.socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }
}