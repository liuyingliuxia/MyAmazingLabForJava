package com.lingong.amazinglab.bean;

import java.util.Base64;

/**
 * {
 * 	errorCode: "0",
 * 	errorMsg: null,
 * 	timestamp: "1595494183332",
 * 	data: "……"
 * }
 * data字段是Base64编码的p12证书文件数据
 *
 */
public class Cert {


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Base64 getData() {
        return data;
    }

    public void setData(Base64 data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "errorCode=" + errorCode +
                ", timestamp=" + timestamp +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }

    int errorCode;
    long timestamp;
    String errorMsg;
    Base64 data;
}
