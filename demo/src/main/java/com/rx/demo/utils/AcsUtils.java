package com.rx.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.rx.demo.domain.Properties;
import com.rx.demo.service.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AcsUtils {

    private static IAcsClient client;
    private static CommonRequest request;

    private static PropertiesService propertiesService;

    @Autowired
    public void setPropertiesService(PropertiesService propertiesService) {
        AcsUtils.propertiesService = propertiesService;
    }

    @PostConstruct
    public static void init() {
        List<Properties> ps = propertiesService.findAll();
        Map<String, String> map = new HashMap<>();
        ps.forEach(p -> map.put(p.getKeey(), p.getValue()));

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", map.get("accessKeyId"), map.get("accessSecret"));
        client = new DefaultAcsClient(profile);

        request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("SignName", map.get("signName"));
        request.putQueryParameter("TemplateCode", map.get("templateCode"));
    }

    /**
     * 发送
     */
    public static String sendCode(String phoneNumber, String code) {
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);

        request.putQueryParameter("TemplateParam", jsonObject.toString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getData();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String test(String accessKeyId, String accessSecret, String templateCode, String signName, String phoneNumbers) {

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", Utils.getCode());
        request.putQueryParameter("TemplateParam", jsonObject.toString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getData();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return "发送失败";
    }


}
