import retrofit.WeatherRestAdapter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Base64;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        /** HttpURLConnection test **/
        /*URLConnection urlConnection =
                new URLConnection("https://github.com/SargisMSU", "GET", null);
        int code = urlConnection.sendRequest();
        if (code == HttpURLConnection.HTTP_OK){
            System.out.println("urlConnection.getContent() = " + urlConnection.getContent());
        }else if (code == HttpURLConnection.HTTP_MOVED_PERM || code == HttpURLConnection.HTTP_MOVED_TEMP){
            System.out.println("urlConnection.getRedirectURL() = " + urlConnection.getRedirectURL());
        }*/

        /** okhttp test **/
        /*String url = "https://reqres.in/api/users/2";
        String postUrl = "https://reqres.in/api/users/";
        String postBody = "{\n" +
                "    \"name\": \"Sargis\",\n" +
                "    \"job\": \"programmer\"\n" +
                "}";

        OkHttpClient client = OkHttpTest.buildClient(10, "", "");
        System.out.println(OkHttpTest.getRequest(client, url));
        OkHttpTest.postRequest(client, postUrl, postBody);*/

        /** retrofit test **/

        System.out.println(new WeatherRestAdapter().testWeatherApiSync("Yerevan"));

    }
}
