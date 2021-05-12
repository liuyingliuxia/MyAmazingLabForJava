package com.lingong.amazinglab.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lingong.amazinglab.R;
import com.lingong.amazinglab.bean.Translation;
import com.lingong.amazinglab.inter.GetRequest;
import com.lingong.amazinglab.nochange.UrlConstString;
import com.lingong.amazinglab.utils.LogUtil;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button btnOne;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOne = findViewById(R.id.btn1);
        btnOne.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                    getClick();
                    break;
            }
        }
    };

    void getClick() {
        getRequest();
    }

    private void getRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstString.BAIDU_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GetRequest request = retrofit.create(GetRequest.class);

        Observable<Translation> observable = request.getCall();

        observable.subscribeOn(Schedulers.io()) //io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) //回到主线程 处理请求结果
                .subscribe(new Observer<Translation>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        LogUtil.d("Begin onSubscribe 连接");
                    }

                    @Override
                    public void onNext(@NonNull Translation translation) {
                        LogUtil.d(translation.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.e("onError 。。。 ");
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.e("onComplete 。。。 ");
                    }
                });
    }
}