package com.lingong.javalib.demo;


import com.lingong.javalib.demo.com.baidu.translate.demo.TransApi;
// 纯JAVA 版本处理网络请求
public class Main {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20210110000668359";
    private static final String SECURITY_KEY = "rX_4tltNBEZMl5PsxW79";

    public static void main(String[] args) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "你好，世界";
        System.out.println(api.getTransResult(query, "auto", "en"));
    }

}
