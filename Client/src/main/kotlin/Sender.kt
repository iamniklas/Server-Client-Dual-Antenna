import java.io.DataOutputStream
import java.lang.Thread
import java.io.IOException
import java.io.OutputStream

class Sender(_outStream: OutputStream?) : Thread() {

    var outStream: DataOutputStream? = null

    fun send(_message: String?) {
        try {
            outStream!!.writeUTF(_message)
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }

    init {
        outStream = DataOutputStream(_outStream)
    }
}