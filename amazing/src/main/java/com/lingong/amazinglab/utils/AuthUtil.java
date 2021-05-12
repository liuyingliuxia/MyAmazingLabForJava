package com.lingong.amazinglab.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.lingong.amazinglab.BuildConfig;
import com.lingong.amazinglab.ui.MainActivity;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Author:Miracle.lin
 * Date:2021/5/12 15:29
 * Descri:
 */
public class AuthUtil {

    /**
     * 获取授权的方法：
     * ts = String(getCuurentTimestampInMilliseconds());
     * encryptStr = RequestURL + ts;       //RequestURL sample: /kg/v1/content
     * signature = Base64(HMAC_SHA1(SecretKey, encryptStr));
     * authorizationValue = AccessKey + ":" + signature + ":" + ts;
     * add a request header named "Authorization" with a value of authorizationValue
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getAuth() throws Exception {
        long timeStamp = System.currentTimeMillis();
        String ts = timeStamp + "";
        String encryptStr = MainActivity.REQUEST_DOWNLOAD_URL + ts;
        byte[] bHmacSha1 = HmacSHA1Encrypt(BuildConfig.SECRET_KEY, encryptStr);
        String signature = Base64.getEncoder().encodeToString(bHmacSha1);
        String authorizationValue = BuildConfig.ACCESS_KEY + ":" + signature + ":" + ts;
        LogUtil.d("timeStamp = " + ts + "\n signature = " + signature + "\n" +
                "authorizationValue = \n" + authorizationValue);

        return authorizationValue;
    }



    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

	/*
	 * 展示了一个生成指定算法密钥的过程 初始化HMAC密钥
	 * @return
	 * @throws Exception
	 *
	  public static String initMacKey() throws Exception {
	  //得到一个 指定算法密钥的密钥生成器
	  KeyGenerator KeyGenerator keyGenerator =KeyGenerator.getInstance(MAC_NAME);
	  //生成一个密钥
	  SecretKey secretKey =keyGenerator.generateKey();
	  return null;
	  }
	 */

    /**
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     *
     * @param encryptText 被签名的字符串
     * @param encryptKey  密钥
     * @return
     * @throws Exception
     */
    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        //生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        //用给定密钥初始化 Mac 对象
        mac.init(secretKey);

        byte[] text = encryptText.getBytes(ENCODING);
        //完成 Mac 操作
        return mac.doFinal(text);
    }

}
