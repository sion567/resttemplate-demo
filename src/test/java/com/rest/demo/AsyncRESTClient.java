package com.rest.demo;

import cc.sion567.rest.sample.vo.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by h2g on 2016/11/5.
 */
public class AsyncRESTClient {
    final static String _url_ = "http://localhost:8080/aysnc/";


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
        HttpEntity<User> entity = new HttpEntity<User>(headers);

//        Map<String,String> vars = Collections.singletonMap("user.name","huahua");
        User u = new User();
        u.setName("huahua");
        ListenableFuture<ResponseEntity<String>> future3 = restTemplate.postForEntity(_url_ + "doo1", entity, String.class, u);
        future3.addCallback(callBack);
        ListenableFuture<ResponseEntity<String>> future4 = restTemplate.postForEntity(_url_ + "doo2", entity, String.class, u);
        future4.addCallback(callBack);



        ListenableFuture<ResponseEntity<String>> future5 = restTemplate.getForEntity(_url_ + "too1/caocao", String.class);
        future5.addCallback(callBack);
        ListenableFuture<ResponseEntity<String>> future6 = restTemplate.getForEntity(_url_ + "too2/caocaocao", String.class);
        future6.addCallback(callBack);




    }
}
