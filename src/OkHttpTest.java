/**
 * com.squareup.okhttp:okhttp:2.7.0
 * */

import okhttp3.*;

import java.io.File;
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
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }    // Asynchronous
        });
        //client.newCall(request).execute();            Synchronous
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
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder().
                readTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .connectTimeout(timeOut, TimeUnit.SECONDS);
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(new File("cacheFileName"), cacheSize);
        clientBuilder.cache(cache);
        clientBuilder.authenticator(new Authenticator(){
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                String credentials = Credentials.basic(userName, password);
                return response.request().newBuilder()
                        .header("Authorization", credentials)
                        .build();
            }
        });
        return clientBuilder.build();
    }
}

























