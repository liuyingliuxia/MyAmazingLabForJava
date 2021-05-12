package com.lingong.amazinglab.request;

/**
 * Author:Miracle.lin
 * Date:2021/5/12 17:02
 * Descri:
 */
public interface ResponseListener {
    void onResponseResult(String result);
    void onResponseError(String result);
}
