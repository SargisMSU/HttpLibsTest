import com.squareup.okhttp.*;
import java.io.IOException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

public class OkHttpTest {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void postRequest(OkHttpClient client, String postUrl, String postBody) throws IOException {
        RequestBody body = RequestBody.create(JSON, postBody);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }


    public static String getRequest(OkHttpClient client, String url){
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        Request request = builder.build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static OkHttpClient buildClient(int timeOut, String userName, String password){
        OkHttpClient client = new OkHttpClient();
        client.setWriteTimeout(timeOut, TimeUnit.SECONDS);
        client.setReadTimeout(timeOut, TimeUnit.SECONDS);
        client.setConnectTimeout(timeOut, TimeUnit.SECONDS);
        client.setAuthenticator(new Authenticator(){
            @Override
            public Request authenticate(Proxy proxy, Response response) throws IOException {
                String credentials = Credentials.basic(userName, password);
                return response.request().newBuilder()
                        .header("Authorization", credentials)
                        .build();
            }
            @Override
            public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
                return null;
            }
        });
        return client;
    }
}

























