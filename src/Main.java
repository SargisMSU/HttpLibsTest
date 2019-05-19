import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        URLConnection urlConnection =
                new URLConnection("https://github.com/SargisMSU", null);
        int code = urlConnection.sendRequest();
        if (code == HttpURLConnection.HTTP_OK){
            System.out.println("urlConnection.getContent() = " + urlConnection.getContent());
        }else if (code == HttpURLConnection.HTTP_MOVED_PERM || code == HttpURLConnection.HTTP_MOVED_TEMP){
            System.out.println("urlConnection.getRedirectURL() = " + urlConnection.getRedirectURL());
        }
    }
}
