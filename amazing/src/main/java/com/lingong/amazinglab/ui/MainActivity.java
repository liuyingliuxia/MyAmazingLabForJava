package com.lingong.amazinglab.ui;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.lingong.amazinglab.BuildConfig;
import com.lingong.amazinglab.R;
import com.lingong.amazinglab.request.InternetRequest;
import com.lingong.amazinglab.request.ResponseListener;
import com.lingong.amazinglab.utils.AuthUtil;
import com.lingong.amazinglab.utils.LogUtil;

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


    //新线程进行网络请求
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
//            Message msg = new Message();
//            Bundle data = new Bundle();
//            data.putString("value", "请求结果");
//            msg.setData(data);
//            handler.sendMessage(msg);

            String authSign = null;
            try {
                authSign = AuthUtil.getAuth();

                IR = new InternetRequest();
                IR.requestGet(authSign, BuildConfig.DOWNLOAD_CERT_URL, new ResponseListener() {
                    @Override
                    public void onResponseResult(String result) {
                        LogUtil.d("success onResponse = " + result);
                    }

                    @Override
                    public void onResponseError(String result) {
                        LogUtil.e("onFailure =" + result);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };
    //handler 处理返回的请求结果
    Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            //
            // TODO: 更新界面
            //
        }
    };



    private void getDownloadCert() {
        try {new Thread(runnable).start();
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .followRedirects(false) //关闭重定向  Too many follow-up requests: 21
//                    .followSslRedirects(false)
//                    .authenticator((route, response) ->
//                            response.request().newBuilder().addHeader("Authorization", authSign).build())
//                    .build();
//            Request request = new Request.Builder().url(BuildConfig.DOWNLOAD_CERT_URL).build();
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    LogUtil.e("onFailure =" + e.getMessage());
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    LogUtil.d("success onResponse = \n" + response.body().string());
//                }
//            });

//            client.connectTimeout(10, TimeUnit.SECONDS);
//            Retrofit retrofit = new Retrofit.Builder()
//                    .client(client.build())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .baseUrl(BASE_DOWNLOAD_URL)
//                    .build();
//
//            HttpService apiService = retrofit.create(HttpService.class);
//            Observable<HttpResult<Cert>> observable = apiService.downloadCert();
//            observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(
//                            new Subscriber<HttpResult<Cert>>() {
//
//                                @Override
//                                public void onNext(HttpResult<Cert> certHttpResult) {
//                                    if (certHttpResult != null)
//                                        LogUtil.d("onNext = data= " + certHttpResult.getData().getData());
//                                }
//
//                                @Override
//                                public void onCompleted() {
//                                    LogUtil.d("onCompleted ... ");
//                                }
//
//                                @Override
//                                public void onError(Throwable t) {
//                                    LogUtil.e("onError = " + t.getMessage());
//                                }
//
//                            }
//                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}