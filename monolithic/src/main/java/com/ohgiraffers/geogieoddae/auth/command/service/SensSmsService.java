package com.ohgiraffers.geogieoddae.auth.command.service;

import com.ohgiraffers.geogieoddae.global.config.SensConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class SensSmsService {
    
    @Autowired
    private SensConfig sensConfig;
    
    public boolean sendSms(String phoneNumber, String message) {
        try {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String signature = makeSignature(timestamp);
            
            Map<String, Object> body = new HashMap<>();
            body.put("type", "SMS");
            body.put("contentType", "COMM");
            body.put("countryCode", "82");
            body.put("from", sensConfig.getSenderPhone());
            body.put("content", message);
            
            Map<String, String> to = new HashMap<>();
            to.put("to", phoneNumber);
            body.put("messages", new Map[]{to});
            
            WebClient webClient = WebClient.builder()
                .baseUrl("https://sens.apigw.ntruss.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
            
            String response = webClient.post()
                .uri("/sms/v2/services/" + sensConfig.getServiceId() + "/messages")
                .header("x-ncp-apigw-timestamp", timestamp)
                .header("x-ncp-iam-access-key", sensConfig.getAccessKey())
                .header("x-ncp-apigw-signature-v2", signature)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();
            
            System.out.println("SENS SMS Response: " + response);
            return true;
            
        } catch (Exception e) {
            System.err.println("SMS 발송 실패: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    private String makeSignature(String timestamp) throws Exception {
        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/" + sensConfig.getServiceId() + "/messages";
        
        String message = method + space + url + newLine + timestamp + newLine + sensConfig.getAccessKey();
        
        SecretKeySpec signingKey = new SecretKeySpec(sensConfig.getSecretKey().getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        
        byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(rawHmac);
    }
}
