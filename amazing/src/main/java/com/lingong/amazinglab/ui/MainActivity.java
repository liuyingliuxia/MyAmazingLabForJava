package com.lingong.amazinglab.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.lingong.amazinglab.BuildConfig;
import com.lingong.amazinglab.R;
import com.lingong.amazinglab.request.InternetRequest;
import com.lingong.amazinglab.utils.AuthUtil;
import com.lingong.amazinglab.utils.LogUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {
    Button btnOne;
    private static final String TAG = "MainActivity";
    //这两个拼接成完整的url
    public static final String BASE_DOWNLOAD_URL = "https://saas.zeasn.tv";
    public static final String REQUEST_DOWNLOAD_URL = "/auth-api/api/v1/cert/download/demo";
    private InternetRequest IR;

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
            String authSign = AuthUtil.getAuth();
            OkHttpClient client = new OkHttpClient.Builder()
                    .followRedirects(false) //关闭重定向  Too many follow-up requests: 21
                    .followSslRedirects(false)
                    .authenticator((route, response) ->
                            response.request().newBuilder().addHeader("Authorization", authSign).build())
                    .build();
            Request request = new Request.Builder().url(BuildConfig.DOWNLOAD_CERT_URL).get().build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    LogUtil.e("onFailure =" + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    LogUtil.d("success onResponse = \n" + response.body().string());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}