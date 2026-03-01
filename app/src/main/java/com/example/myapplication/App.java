import android.app.Application;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class App extends Application {
    AppService appService;
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        appService = retrofit.create(AppService.class);
    }
    public AppService getAPI() {
        return appService;
    }
