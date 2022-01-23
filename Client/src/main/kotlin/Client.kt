import java.net.Socket
import java.io.IOException

class Client(var socket: Socket) : ReceiveCallback {

    var sender: Sender? = null
    var receiver: Receiver? = null

    override fun onReceiveMessage(_message: String?) {
        println(_message)
    }

    init {
        try {
            sender = Sender(socket.getOutputStream())
            receiver = Receiver(socket, socket.getInputStream(), this)

            //sender.start();
            receiver!!.start()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }
}