package com.lingong.amazinglab.inter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.lingong.amazinglab.bean.Cert;
import com.lingong.amazinglab.bean.HttpResult;
import com.lingong.amazinglab.ui.MainActivity;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;

/**
 * Author:Miracle.lin
 * Date:2021/5/12 15:32
 * Descri:
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public interface HttpService {


    @Headers("header_extend:Authorization")
    @GET(MainActivity.BASE_DOWNLOAD_URL)
    Observable<HttpResult<Cert>> downloadCert();


}
