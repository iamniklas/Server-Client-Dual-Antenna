import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Server {
    ServerSocket serverSocket;

    static ArrayList<ClientService> clients = new ArrayList<ClientService>();

    public Server() {
        try {
            serverSocket = new ServerSocket(3333);
            System.out.println("Server started");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        while(true) {
            try {
                clients.add(new ClientService(serverSocket.accept()));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void makeRPCCallToAll(String _message) {
        for (ClientService c : clients) {
            c.sender.send(_message);
        }
    }

    public static void makeRPCCallNoHost(int _hostID, String _message) {
        ArrayList<ClientService> targets = new ArrayList<ClientService>();

        for(ClientService client : clients) {
            if (client.id != _hostID) {
                targets.add(client);
            }
        }

        for(ClientService client : targets) {
            client.sender.send(_message);
        }
    }
}