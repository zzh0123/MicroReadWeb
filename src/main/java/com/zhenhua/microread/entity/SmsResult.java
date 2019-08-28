package com.zhenhua.microread.entity;

import java.io.Serializable;

/**
 * @author zzh
 * @create 2019-08-21 23:01
 * @desc 阿里云短信返回数据实体
 */
public class SmsResult implements Serializable {
    private String BizId;
    private String Code;
    private String Message;
    private String RequestId;

    public String getBizId() {
        return BizId;
    }

    public void setBizId(String bizId) {
        BizId = bizId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }
}
