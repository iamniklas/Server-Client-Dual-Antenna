import java.net.Socket
import java.io.IOException
import java.util.*

object Program {
    var client: Client? = null
    var username = ""
    var m = ArrayList<String>()
    var chatPort = 2400
    var stripPort = 3333

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        var cSocket: Socket? = null
        try {
            print("Enter Username: ")
            username = scanner.nextLine()
            cSocket = Socket("localhost", stripPort)
            client = Client(cSocket)
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        while (!cSocket!!.isClosed) {
            val message = scanner.nextLine()
            //client.sender.send(username + ": " + message);
            client!!.sender!!.send(message)
            if (message == "quit") try {
                client!!.socket.close()
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }
        }
    }
}