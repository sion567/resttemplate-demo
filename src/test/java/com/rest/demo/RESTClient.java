package com.rest.demo;

import cc.sion567.rest.sample.vo.Rate;
import com.google.common.collect.Maps;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by hgg on 2016/10/18.
 */
public class RESTClient {

    final static String _url_ = "http://127.0.0.1:8080/";


    public static void main(String[] args) {
        RestTemplate restTemplate =new RestTemplate();
        System.out.println("[foo1]>>" + (restTemplate.getForObject(_url_ + "foo1", String.class)));
        System.out.println("[foo2]>>" + (restTemplate.getForObject(_url_ + "foo2?name=huahua", String.class)));
        System.out.println("[foo3]>>" + (restTemplate.getForObject(_url_ + "foo3/caocao", String.class)));
        Map<String, String> vars = Collections.singletonMap("name", "caocao");
        System.out.println("[foo4]>>" + (restTemplate.getForObject(_url_ + "foo3/{name}", String.class, vars)));

//http://blog.csdn.net/luccs624061082/article/details/40893963
        System.out.println("===========");
        Map<String,String> map = Maps.newHashMap();
        map.put("name", "abc");
        System.out.println("[do1]>>" + (restTemplate.postForObject(_url_ + "do1", null, String.class, map)));
        System.out.println("[do1]>>" + (restTemplate.postForObject(_url_ + "do1?name=abc", null, String.class)));
//        System.out.println("[do2]>>" + (restTemplate.postForObject(_url_ + "do2", null, String.class, map)));

        System.out.println("[do1]>>" + (restTemplate.exchange(_url_ + "do1", HttpMethod.POST, new HttpEntity<String>(new HttpHeaders()), String.class, "AAAA")).getBody());

        HttpHeaders headers_json = new HttpHeaders();
        headers_json.setAccept(MediaType.parseMediaTypes(MediaType.APPLICATION_JSON_VALUE));
        headers_json.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity_json = new HttpEntity<Object>("{\"name\":\"ABCDEFG\"}", headers_json);

        System.out.println("[do3]>>" + (restTemplate.exchange(_url_ + "do3", HttpMethod.POST, new HttpEntity<Object>("ABCDEFG", new HttpHeaders()), String.class)).getBody());
        System.out.println("[do4]>>" + (restTemplate.exchange(_url_ + "do4", HttpMethod.POST, requestEntity_json, String.class)).getBody());


//        RequestEntity<String> requestEntity = new RequestEntity<String>(headers, HttpMethod.POST, URI.create("/proto/read"));
        System.out.println("test object.....");
        //step1 error
//        ResponseEntity<? extends ArrayList<Rate>> responseEntity = restTemplate.getForEntity(_url_ + "testObject", (Class<? extends ArrayList<Rate>>) ArrayList.class);
//        for(Rate r : responseEntity.getBody()){
//            System.out.println(r.getCode());
//        }
        //step2
        ResponseEntity<List<Rate>> rateResponse =
                restTemplate.exchange(_url_+"testObject",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Rate>>() {
                        });
        List<Rate> list2 = rateResponse.getBody();
        for(Rate r : list2){
            System.out.println(r.getRate());
        }
    }



    public void demo(){
        // Resource resource = new ClassPathResource("appcontext.xml");
// BeanFactory factory = new XmlBeanFactory(resource);

//用classpath路径也可以
// ApplicationContext factory=new ClassPathXmlApplicationContext("classpath:appcontext.xml");
// ApplicationContext factory=new ClassPathXmlApplicationContext("appcontext.xml");

// ClassPathXmlApplicationContext 使用了file前缀也是可以使用绝对路径的
// ApplicationContext factory=new ClassPathXmlApplicationContext("file:G:/1Java实用项目资源/2Spring/1精通Spring全 Jar代码/workspace/workspace/example6/src/appcontext.xml");

//用文件系统的路径 默认是指项目的根路径
// ApplicationContext factory=new FileSystemXmlApplicationContext("src/appcontext.xml");

//使用了 classpath: 前缀,作为标志, 这样,FileSystemXmlApplicationContext 也能够读入classpath下的相对路径
// ApplicationContext factory=new FileSystemXmlApplicationContext("classpath:appcontext.xml");
// ApplicationContext factory=new FileSystemXmlApplicationContext("file:G:/1Java实用项目资源/2Spring/1精通Spring全 Jar代码/workspace/workspace/example6/src/appcontext.xml");

//也可以不加file前缀
//        ApplicationContext factory=new FileSystemXmlApplicationContext("G:/1Java实用项目资源/2Spring/1精通Spring全Jar代码 /workspace/workspace/example6/src/appcontext.xml");
//
//        IHelloWorld hw = (IHelloWorld) factory.getBean("helloworldbean");
//        log.info(hw.getContent("luoshifei"));
    }

}
