import java.io.IOException;
import java.net.Socket;

public class Client implements ReceiveCallback {
    Socket socket;
    Sender sender;
    Receiver receiver;

    public Client(Socket _socket) {
        socket = _socket;

        try {
            sender = new Sender(socket.getOutputStream());
            receiver = new Receiver(socket, socket.getInputStream(), this);

            //sender.start();
            receiver.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onReceiveMessage(String _message) {
        System.out.println(_message);
    }
}