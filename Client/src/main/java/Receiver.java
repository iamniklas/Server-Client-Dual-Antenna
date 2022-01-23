import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class Receiver extends Thread {

    Socket socket = null;

    DataInputStream inStream = null;

    ReceiveCallback callback = null;

    public Receiver(Socket _socket, InputStream _inStream, ReceiveCallback _callback) {
        socket = _socket;
        inStream = new DataInputStream(_inStream);
        callback = _callback;
    }

    public void run() {
        while(!socket.isClosed()) {
            callback.onReceiveMessage(receive());
        }
    }

    public String receive() {
        try {
            return inStream.readUTF();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
}