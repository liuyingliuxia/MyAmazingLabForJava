package com.lingong.amazinglab.bean;

/**
 * description :
 * author : yexifeng
 * email : ye_xi_feng@163.com
 * date : 2019/5/22 10:41
 */
public class HttpResult<T> {
    /**
     * 请求成功
     */
    public static final int HTTP_RESULT_CODE_SUCCESS = 200;
    /**
     * productId找不到、mac没备案、DP不可用
     */
    public static final int HTTP_RESULT_CODE_REGISTER_FAIL = 600102;
    /**
     * 服务器错误
     */
    public static final int HTTP_RESULT_CODE_SERVER_ERROR = 600501;

    /**
     * TERMINAL_ID错误，可能是环境切换导致
     */
    public static final int HTTP_RESULT_UNKNOWN_TERMINAL_ID = 600101;

    /**
     * 设备被拉黑
     */
    public static final int HTTP_BLACK_LIST = 500104;
    /**
     * 无token
     */
    public static final int HTTP_RESULT_CODE_TOKEN_INVALID = 1000;
    /**
     * 令牌无效或非法，需要重新调用REGISTER接口请求Token
     */
    public static final int HTTP_RESULT_CODE_TOKEN_INVALID_1 = 1002;
    private int errorCode;
    private String timestamp;
    private String errorMsg;

    private T data;


    public T getData() {
        return data;
    }



    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public boolean isSuccess() {
        return errorCode == 0;
    }

    public String getMessage() {
        return errorMsg;
    }


    @Override
    public String toString() {
        return "{" +
                "\"errorCode\":\"" + errorCode + "\"" +
                ", \"errorMsg\":\"" + errorMsg + "\"" +
                ", \"data\":" + data +
                '}';
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
