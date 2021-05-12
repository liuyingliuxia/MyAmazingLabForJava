package com.lingong.amazinglab.bean;

import java.io.Serializable;

/**
 * Author:Miracle.lin
 * Date:2021/5/10 15:17
 * Descri:[{"src":"\u4f60\u597d\uff0c\u4e16\u754c","dst":"Hello, world"}]
 *
 */
public class TransResult implements Serializable {
    @Override
    public String toString() {
        return "TransResult{" +
                "src='" + src + '\'' +
                ", dst='" + dst + '\'' +
                '}';
    }

    String src; //编码
    String dst;  // 编码后的结果

}
