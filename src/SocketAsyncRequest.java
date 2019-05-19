import javax.net.SocketFactory;
import javax.net.ssl.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class SocketAsyncRequest {

    static void execute(String address, Integer port) {
        try {
            switch (port) {
                case 80:
                    performDefaultConnection(address, port);
                    break;
                case 443:
                    performSecureConnection(address, port);
                    break;
                default:
                    break;
            }
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static byte[] getRequestString(String address) throws UnsupportedEncodingException {
        String request = "GET / HTTP/1.1\r\nHost: $address\r\nConnection: Close\r\n\r\n";
        return request.getBytes(StandardCharsets.UTF_8);
    }

    private static String performDefaultConnection(String address, Integer port) throws IOException {
        Socket socket = new Socket(address, port);
        OutputStream os = socket.getOutputStream();
        os.write(getRequestString(address));
        os.flush();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String collect = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        os.close();
        socket.close();
        return collect;
    }

    private static String performSecureConnection(String address, Integer port) throws IOException {
        SocketFactory sf = SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) sf.createSocket(address, port);
        HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
        SSLSession s = socket.getSession();
        if (!hv.verify(address, s)) {
            throw new SSLHandshakeException("Expected " + address + ", " + "found " + s.getPeerPrincipal());
        }
        OutputStream os = socket.getOutputStream();
        os.write(getRequestString(address));
        os.flush();
        String output = new BufferedReader(new InputStreamReader(socket.getInputStream())).lines()
                .collect(Collectors.joining(System.lineSeparator()));
        os.close();
        socket.close();
        return output;
    }
}