import java.net.Socket
import java.lang.Thread
import java.io.IOException

class ClientService(_socket: Socket) : Thread(), ReceiveCallback {

    var id = 0
    var socket: Socket

    var sender: Sender? = null
    var receiver: Receiver? = null

    override fun onReceiveMessage(_message: String?) {
        println("Client #" + nextId + ": " + _message)

        //TODO Re-enable so not only the server receives the messages
        //Server.makeRPCCallToAll(_message);

        Server.makeRPCCallNoHost(id, _message);
    }

    companion object {
        var nextId = 0
    }

    init {
        id = nextId
        nextId++
        socket = _socket
        println("New client connected")
        try {
            sender = Sender(socket.getOutputStream())
            receiver = Receiver(socket, socket.getInputStream(), this)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        receiver!!.start()
    }
}