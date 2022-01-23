import java.io.DataInputStream
import java.io.InputStream
import java.net.Socket
import java.lang.Thread
import java.lang.Exception

class Receiver(_socket: Socket?, _inStream: InputStream?, _callback: ReceiveCallback?) : Thread() {

    var socket: Socket? = null
    var inStream: DataInputStream? = null
    var callback: ReceiveCallback? = null

    override fun run() {
        while (!socket!!.isClosed) {
            callback!!.onReceiveMessage(receive())
        }
    }

    fun receive(): String {
        return try {
            inStream!!.readUTF()
        } catch (e: Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace()
            ""
        }
    }

    init {
        socket = _socket
        inStream = DataInputStream(_inStream)
        callback = _callback
    }
}