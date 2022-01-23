import java.io.DataInputStream
import java.net.Socket
import java.lang.Thread
import java.io.IOException
import java.io.InputStream

class Receiver(var socket: Socket, _inStream: InputStream?, _callback: ReceiveCallback) : Thread() {
    var inStream: DataInputStream
    var callback: ReceiveCallback
    override fun run() {
        while (!socket.isClosed) {
            try {
                callback.onReceiveMessage(inStream.readUTF())
            } catch (e: IOException) {
                try {
                    socket.close()
                } catch (e1: IOException) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace()
                }
            }
        }
    }

    init {
        inStream = DataInputStream(_inStream)
        callback = _callback
    }
}