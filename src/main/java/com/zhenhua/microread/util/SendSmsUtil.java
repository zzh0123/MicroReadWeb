package com.zhenhua.microread.util;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.zhenhua.microread.entity.SmsResult;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author zzh
 * @create 2019-08-21 22:08
 * @desc 发送短信工具类
 */
public class SendSmsUtil {
//    public static DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
//    public static IAcsClient client = new DefaultAcsClient(profile);

    public static String SendSms(String vcode, String phoneNum){
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
//        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = "yourAccessKeyId";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "yourAccessKeySecret";//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIoDJiQEgd0Ugj",
                "SNrP5H703uZ5ZE1nHkyl2ZLS0K3x95");
//        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient client = new DefaultAcsClient(profile);

        String result = null;
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(domain);
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
//        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNum); // "18311004536"
        request.putQueryParameter("SignName", "微读app");
        request.putQueryParameter("TemplateCode", "SMS_169903140");
        request.putQueryParameter("TemplateParam", " {\"code\":"+ vcode + "}");

        try {
            CommonResponse response = client.getCommonResponse(request);
            if (response.getData() != null){
//                System.out.println(response.getData());
                result = response.getData();
                SmsResult smsResult = JSON.parseObject(result, SmsResult.class);
                if (smsResult.getCode().equals("OK")){
                    return result;
                }

            }

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return result;
    }

}
