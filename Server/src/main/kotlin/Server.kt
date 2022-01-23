import java.net.ServerSocket
import java.io.IOException
import java.util.ArrayList

class Server {
    var serverSocket: ServerSocket? = null

    companion object {
        var clients = ArrayList<ClientService>()
        fun makeRPCCallToAll(_message: String?) {
            for (c in clients) {
                c.sender!!.send(_message)
            }
        }

        fun makeRPCCallNoHost(_hostID: Int, _message: String?) {
            val targets = ArrayList<ClientService>()
            for (client in clients) {
                if (client.id != _hostID) {
                    targets.add(client)
                }
            }
            for (client in targets) {
                client.sender!!.send(_message)
            }
        }
    }

    init {
        try {
            serverSocket = ServerSocket(3333)
            println("Server started")
        } catch (e1: IOException) {
            // TODO Auto-generated catch block
            e1.printStackTrace()
        }
        while (true) {
            try {
                clients.add(ClientService(serverSocket!!.accept()))
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }
        }
    }
}