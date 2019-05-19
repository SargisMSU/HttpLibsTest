import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class URLConnection {
    private final URL url;
    private Map<String, String> headers;
    private String redirectURL;
    private HttpURLConnection urlConnection;
    private String content;
    private String method;

    public URLConnection(String url, String method, Map<String, String> headers) throws MalformedURLException {
        this.url = new URL(url);
        this.method = method;
        this.headers = headers == null ? new HashMap<>() : headers;
    }

    public int sendRequest(){
        int responseCode = 0;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(method);
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            urlConnection.setInstanceFollowRedirects(true);
            for (Map.Entry<String, String> entry: headers.entrySet()){
                urlConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                setContent();
            }else if (responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP){
                setRedirectURL();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseCode;
    }

    private void setContent() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        content = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
    }

    private void setRedirectURL(){
        redirectURL = urlConnection.getHeaderField("Location");
    }

    public String getRedirectURL() {
        return redirectURL;
    }

    public String getContent(){
        return content;
    }
}
