package com.lingong.amazinglab.inter;

import com.lingong.amazinglab.bean.Translation;
import com.lingong.amazinglab.nochange.UrlConstString;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

/**
 * Author:Miracle.lin
 * Date:2021/5/10 15:19
 * Descri:
 */
public interface GetRequest {
    @GET(UrlConstString.MY_URL)
    Observable<Translation> getCall();
}
