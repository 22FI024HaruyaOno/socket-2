import java.net.InetAddress;
import java.net.UnknownHostException;

public class CheckLocalAddress {
    public static void main(String[] args) {
        try {
            /*
            // IP Address
            InetAddress addr = InetAddress.getByName("8.8.8.8");
            */
            // Local Address
            InetAddress addr = InetAddress.getLocalHost();

            // Host name
            System.out.println("Host name is: " + addr.getHostName());
            // Host Address
            System.out.println("Ip address is: " + addr.getHostAddress());
        } catch (UnknownHostException e) {

        }
    }
}
