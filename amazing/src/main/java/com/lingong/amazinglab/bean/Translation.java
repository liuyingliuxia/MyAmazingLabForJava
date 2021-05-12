package com.lingong.amazinglab.bean;

import com.lingong.amazinglab.utils.LogUtil;

/**
 * Author:Miracle.lin
 * Date:2021/5/10 15:15
 * Descri:
 * <p>
 * {"from":"zh","to":"en","trans_result":[{"src":"\u4f60\u597d\uff0c\u4e16\u754c","dst":"Hello, world"}]}
 */
public class Translation {


    int status;

    private Content content;
    private static class Content{
        String from;
        String to;
        TransResult transResult;
    }

    public void show(){
        LogUtil.d("status = "  + status);
        LogUtil.d("from = "  + content.from);
        LogUtil.d("to = "  + content.to);
        LogUtil.d("transResult = "  + content.transResult);


    }

}
