package com.lingong.amazinglab.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.lingong.amazinglab.R;
import com.lingong.amazinglab.bean.Cert;
import com.lingong.amazinglab.bean.HttpResult;
import com.lingong.amazinglab.inter.HttpService;
import com.lingong.amazinglab.utils.AuthUtil;
import com.lingong.amazinglab.utils.LogUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {
    Button btnOne;
    private static final String TAG = "MainActivity";

    public static final String BASE_DOWNLOAD_URL = "https://saas.zeasn.tv/auth-api/";
    public static final String REQUEST_DOWNLOAD_URL = "api/v1/cert/download/demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOne = findViewById(R.id.btn1);
        btnOne.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = v -> {
        switch (v.getId()) {
            case R.id.btn1:
                getClick();
                break;
        }
    };

    void getClick() {
        getDownloadCert();
    }

    private void getDownloadCert() {
        try {
            String authStr = AuthUtil.getAuth();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(10, TimeUnit.SECONDS);
            Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(BASE_DOWNLOAD_URL)
                    .build();

            HttpService apiService = retrofit.create(HttpService.class);
            Observable<HttpResult<Cert>> observable = apiService.downloadCert();
            observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new Subscriber<HttpResult<Cert>>() {

                                @Override
                                public void onNext(HttpResult<Cert> certHttpResult) {
                                    if (certHttpResult != null)
                                        LogUtil.d("onNext = data= " + certHttpResult.getData().getData());
                                }

                                @Override
                                public void onCompleted() {
                                    LogUtil.d("onCompleted ... ");
                                }

                                @Override
                                public void onError(Throwable t) {
                                    LogUtil.e("onError = " + t.getMessage());
                                }

                            }
                    );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}