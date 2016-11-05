package com.rest.demo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by h2g on 2016/11/5.
 */
public class AsyncRESTClient {
    final static String _url_ = "http://127.0.0.1:8080/aysnc/";


    public static void main(String[] args) {
        AsyncRestTemplate restTemplate = new AsyncRestTemplate();

        ListenableFutureCallback callBack = new ListenableFutureCallback<ResponseEntity<String>>() {
            @Override
            public void onSuccess(ResponseEntity<String> result) {
                System.out.println("success");
                System.out.println("    statusCode:"+result.getStatusCode());
                System.out.println("    result:" + result.getBody());
            }
            @Override
            public void onFailure(Throwable e) {
                System.out.println("error!!!");
            }
        };

        for(int i=0;i<5;i++)
            System.out.println("I'm bee..."+i);
        ListenableFuture<ResponseEntity<String>> future1 = restTemplate.getForEntity(_url_ + "foo1", String.class);
        ListenableFuture<ResponseEntity<String>> future2 = restTemplate.getForEntity(_url_ + "foo2", String.class);
        future1.addCallback(callBack);
        future2.addCallback(callBack);
        for(int j=0;j<50;j++)
            System.out.println("I'm bee..."+j);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        Map<String,String> vars = new HashMap<String,String>();
        vars.put("name","huahua");
        ListenableFuture<ResponseEntity<String>> future3 = restTemplate.postForEntity(_url_ + "doo", entity, String.class, vars);
        future3.addCallback(callBack);


    }
}
